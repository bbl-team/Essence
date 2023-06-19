package com.benbenlaw.essence.recipe;

import com.benbenlaw.essence.Essence;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Essence.MOD_ID);

    public static final RegistryObject<RecipeSerializer<EssenceStationRecipe>> ESSENCE_STATION_SERIALIZER =
            SERIALIZERS.register("essence_station", () -> EssenceStationRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ResourceDuplicatorRecipe>> RESOURCE_DUPLICATOR_SERIALIZER =
            SERIALIZERS.register("resource_duplicator", () -> ResourceDuplicatorRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
