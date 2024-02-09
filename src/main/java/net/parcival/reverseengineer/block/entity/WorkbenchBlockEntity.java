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
import net.parcival.reverseengineer.ReverseEngineer;
import net.parcival.reverseengineer.item.ModItems;
import net.parcival.reverseengineer.screen.WorkbenchMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorkbenchBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);


    private static int BLANK_BLUEPRINT_SLOT = 0;

    private static int ITEM_SLOT = 1;
    private static int OUTPUT_SLOT = 2;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();


    public WorkbenchBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.WORKBENCH_BE.get(), pPos, pBlockState);
    }
/*
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }*/

/*
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }*/
/*
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }*/
/*
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }*/

    @Override
    public Component getDisplayName() {
        return Component.literal("Blueprint Workbench displayName");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new WorkbenchMenu(pContainerId, pPlayerInventory, this, new SimpleContainer(3));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe()) craftItem();

    }

    private void craftItem() {

    }

    private boolean hasRecipe() {
        //if

    }
}
