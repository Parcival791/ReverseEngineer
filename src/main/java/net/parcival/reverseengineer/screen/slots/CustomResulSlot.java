package net.parcival.reverseengineer.screen.slots;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CustomResulSlot extends Slot {
    public CustomResulSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }
}
