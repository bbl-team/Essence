package com.benbenlaw.essence.client;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.entity.ModBlockEntities;
import com.benbenlaw.essence.block.entity.client.ResourceDuplicatorBlockEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Essence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.RESOURCE_DUPLICATOR.get(),
                ResourceDuplicatorBlockEntityRenderer::new);

    }


}
