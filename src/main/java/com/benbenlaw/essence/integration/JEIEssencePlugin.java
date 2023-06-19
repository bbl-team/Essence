package com.benbenlaw.essence.integration;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.recipe.EssenceStationRecipe;
import com.benbenlaw.essence.recipe.ResourceDuplicatorRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIEssencePlugin implements IModPlugin {

    public static RecipeType<EssenceStationRecipe> ESSENCE_STATION =
            new RecipeType<>(EssenceStationRecipeCategory.UID, EssenceStationRecipe.class);

    public static RecipeType<ResourceDuplicatorRecipe> DUPLICATING =
            new RecipeType<>(ResourceDuplicatorRecipeCategory.UID, ResourceDuplicatorRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Essence.MOD_ID, "jei_plugin");
    }


    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ESSENCE_STATION.get()), EssenceStationRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.RESOURCE_DUPLICATOR.get()), ResourceDuplicatorRecipeCategory.RECIPE_TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                EssenceStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new
                ResourceDuplicatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<EssenceStationRecipe> essenceStationRecipes = rm.getAllRecipesFor(EssenceStationRecipe.Type.INSTANCE);
        registration.addRecipes(ESSENCE_STATION, essenceStationRecipes);

        List<ResourceDuplicatorRecipe> duplicatingRecipes = rm.getAllRecipesFor(ResourceDuplicatorRecipe.Type.INSTANCE);
        registration.addRecipes(DUPLICATING, duplicatingRecipes);
    }
}