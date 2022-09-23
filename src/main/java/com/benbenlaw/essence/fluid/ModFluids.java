package com.benbenlaw.essence.fluid;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Essence.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_LIGHTNING_WATER = FLUIDS.register("lightning_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.LIGHTING_WATER_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_LIGHTNING_WATER = FLUIDS.register("flowing_lightning_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.LIGHTING_WATER_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties LIGHTING_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.LIGHTNING_WATER_FLUID_TYPE, SOURCE_LIGHTNING_WATER, FLOWING_LIGHTNING_WATER)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(ModBlocks.LIGHTNING_WATER_BLOCK)
            .bucket(ModItems.LIGHTNING_WATER_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}