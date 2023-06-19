package com.benbenlaw.essence.events;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.particles.ModParticles;
import com.benbenlaw.essence.particles.custom.EssenceOreParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Essence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventBusEvents {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.BASIC_ESSENCE_ORE_PARTICLES.get(),
                EssenceOreParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.ADVANCED_ESSENCE_ORE_PARTICLES.get(),
                EssenceOreParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.ELITE_ESSENCE_ORE_PARTICLES.get(),
                EssenceOreParticles.Provider::new);

    }
}












