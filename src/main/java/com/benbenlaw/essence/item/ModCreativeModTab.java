package com.benbenlaw.essence.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTab {
    public static final CreativeModeTab ESSENCE = new CreativeModeTab("essence") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BASIC_MOB_ESSENCE.get());
        }
    };
}
