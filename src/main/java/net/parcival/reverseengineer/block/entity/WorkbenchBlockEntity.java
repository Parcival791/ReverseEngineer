package net.parcival.reverseengineer.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.parcival.reverseengineer.item.ModItems;
import net.parcival.reverseengineer.screen.WorkbenchMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorkbenchBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);


    private static int BLANK_BLUEPRINT_SLOT = 0;

    private static int ITEM_SLOT = 1;
    private static int OUTPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;


    public WorkbenchBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.WORKBENCH_BE.get(), pPos, pBlockState);
        this.data = new SimpleContainerData(0);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Blueprint Workbench");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new WorkbenchMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe()) craftItem();

    }

    private void craftItem() {
        if (itemHandler.getStackInSlot(ITEM_SLOT).is(Items.TRIDENT)) {
            itemHandler.getStackInSlot(ITEM_SLOT).split(1);
            itemHandler.getStackInSlot(BLANK_BLUEPRINT_SLOT).split(1);
            if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
                itemHandler.insertItem(OUTPUT_SLOT, ModItems.TRIDENT_BLUEPRINT.get().getDefaultInstance(), false);
            } else {
                itemHandler.getStackInSlot(OUTPUT_SLOT).grow(1);
            }
        }
    }

    private boolean hasRecipe() {
        boolean hasCraftingItems = (this.itemHandler.getStackInSlot(BLANK_BLUEPRINT_SLOT).getItem() == ModItems.BLANK_BLUEPRINT.get()) && (this.itemHandler.getStackInSlot(ITEM_SLOT).getItem() == Items.TRIDENT);
        ItemStack result = new ItemStack(ModItems.TRIDENT_BLUEPRINT.get());

        return hasCraftingItems && canInsertAmountIntoOutput(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutput(int count) {
        ItemStack outputItem = itemHandler.getStackInSlot(OUTPUT_SLOT);
        return ( itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count ) <= outputItem.getMaxStackSize();
    }
}
