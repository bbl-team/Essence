package com.benbenlaw.essence.screen.slot;

import com.benbenlaw.essence.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class EssenceStationCatalystSlot extends SlotItemHandler {
    public EssenceStationCatalystSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getCount() <= 1 && stack.is(ModTags.Items.ESSENCE_STATION_CATALYSTS);
    }


}