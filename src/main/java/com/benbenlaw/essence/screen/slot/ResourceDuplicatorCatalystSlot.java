package com.benbenlaw.essence.screen.slot;

import com.benbenlaw.essence.util.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ResourceDuplicatorCatalystSlot extends SlotItemHandler {
    public ResourceDuplicatorCatalystSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModTags.Items.ESSENCES);
    }


}