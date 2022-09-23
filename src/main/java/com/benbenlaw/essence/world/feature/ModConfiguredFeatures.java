package com.benbenlaw.essence.world.feature;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Essence.MOD_ID);


    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ESSENCE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ESSENCE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ESSENCE_ORE.get().defaultBlockState())));
    /*
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.ENDSTONE_ZIRCON_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHERRACK_ZIRCON_ORE.get().defaultBlockState())));

     */


    public static final RegistryObject<ConfiguredFeature<?, ?>> ESSENCE_ORE = CONFIGURED_FEATURES.register("essence_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ESSENCE_ORE.get(),8)));
    /*
    public static final RegistryObject<ConfiguredFeature<?, ?>> END_ZIRCON_ORE = CONFIGURED_FEATURES.register("end_zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_ZIRCON_ORES.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_ZIRCON_ORE = CONFIGURED_FEATURES.register("nether_zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_ZIRCON_ORES.get(), 9)));

     */



    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
