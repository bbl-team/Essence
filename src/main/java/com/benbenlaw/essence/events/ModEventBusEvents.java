package com.benbenlaw.essence.events;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.particles.ModParticles;
import com.benbenlaw.essence.particles.custom.EssenceOreParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Essence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.ESSENCE_ORE_PARTICLES.get(),
                EssenceOreParticles.Provider::new);
    }


}
/*


    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {

            helper.register(new ResourceLocation(Essence.MOD_ID, "spawner"),
                    (Codec<? extends IGlobalLootModifier>) new SpawnerAdditionModifier.Serializer());

        });

        event.register((ForgeRegistries.Keys.RECIPE_TYPES, helper -> {
            helper.register(new ResourceLocation(Essence.MOD_ID, EssenceConverterRecipe.Type.ID),
                    EssenceConverterRecipe.Type.INSTANCE);
        });
    }

}

 */














