package net.parcival.reverseengineer.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.parcival.reverseengineer.ReverseEngineer;
import net.parcival.reverseengineer.block.ModBlocks;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ReverseEngineer.MODID);


    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_BE = BLOCK_ENTITIES.register("workbench_be", () -> BlockEntityType.Builder.of(WorkbenchBlockEntity::new, ModBlocks.BLUEPRINT_WORKBENCH.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
