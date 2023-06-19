package com.benbenlaw.essence.particles;

import com.benbenlaw.essence.Essence;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Essence.MOD_ID);

    public static final RegistryObject<SimpleParticleType> BASIC_ESSENCE_ORE_PARTICLES =
            PARTICLE_TYPES.register("basic_essence_ore_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> ADVANCED_ESSENCE_ORE_PARTICLES =
            PARTICLE_TYPES.register("advanced_essence_ore_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> ELITE_ESSENCE_ORE_PARTICLES =
            PARTICLE_TYPES.register("elite_essence_ore_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}