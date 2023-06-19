package com.benbenlaw.essence.fluid;

import com.benbenlaw.essence.Essence;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation LIGHTNING_OVERLAY_RL = new ResourceLocation(Essence.MOD_ID, "misc/in_lightning_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Essence.MOD_ID);


    public static final RegistryObject<FluidType> LIGHTNING_WATER_FLUID_TYPE = register("lightning_water_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, LIGHTNING_OVERLAY_RL,
                0XBFFED0D0, new Vector3f(254f / 255f, 208f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }



}
