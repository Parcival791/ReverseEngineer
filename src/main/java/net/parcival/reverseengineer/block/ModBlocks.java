package net.parcival.reverseengineer.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.parcival.reverseengineer.ReverseEngineer;
import net.parcival.reverseengineer.block.custom.WorkbenchBlock;
import net.parcival.reverseengineer.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ReverseEngineer.MODID);

    public static final RegistryObject<Block> BLUEPRINT_WORKBENCH = registerBlock("blueprint_workbench", () -> new WorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));



    private static <T extends Block> RegistryObject<T> registerBlock (String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, (RegistryObject<Block>) toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<Block> block) {
        return ModItems.ITEMS.register("name", () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerModBlocks(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
