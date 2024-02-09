package net.parcival.reverseengineer.screen.slots;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.parcival.reverseengineer.item.ModItems;

public class WorkbenchBlueprintSlot extends Slot {
    public WorkbenchBlueprintSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(ItemStack pStack) {
        return pStack.is(ModItems.BLANK_BLUEPRINT.get());
    }
}
