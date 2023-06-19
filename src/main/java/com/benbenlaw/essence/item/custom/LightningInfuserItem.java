package com.benbenlaw.essence.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.item.context.UseOnContext;

public class LightningInfuserItem extends SimpleFoiledItem {
    public LightningInfuserItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return InteractionResult.SUCCESS;
    }
}
