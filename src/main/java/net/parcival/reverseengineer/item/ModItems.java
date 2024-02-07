package net.parcival.reverseengineer.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.parcival.reverseengineer.ReverseEngineer.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TRIDENT_BLUEPRINT = ITEMS.register("trident_blueprint", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16)));
    public static final RegistryObject<Item> BLANK_BLUEPRINT = ITEMS.register("blank_blueprint", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(16)));







    public static void registerModItems (IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
