package com.benbenlaw.essence.world.feature;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.config.ConfigFile;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Essence.MOD_ID);


    //Spawn Essence Ore in Overworld
    //TODO: work out how to get config values correct instead of .getDefault() using .get() //

    private static int minHeight = ConfigFile.minEssenceOreSpawnHeight.getDefault();
    private static int maxHeight = ConfigFile.maxEssenceOreSpawnHeight.getDefault();

    public static final RegistryObject<PlacedFeature> ESSENCE_ORE_PLACED = PLACED_FEATURES.register("essence_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ESSENCE_ORE.getHolder().get(),
                    commonOrePlacement(28, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight),
                    VerticalAnchor.aboveBottom(maxHeight)))));


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
