package com.benbenlaw.essence.integration;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.recipe.EssenceStationRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EssenceStationRecipeCategory implements IRecipeCategory<EssenceStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Essence.MOD_ID, "essence_station");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Essence.MOD_ID, "textures/gui/essence_station_gui_jei.png");

    static final RecipeType<EssenceStationRecipe> RECIPE_TYPE = RecipeType.create(Essence.MOD_ID, "essence_station",
            EssenceStationRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EssenceStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ESSENCE_STATION.get()));
    }

    @Override
    public @NotNull RecipeType<EssenceStationRecipe> getRecipeType() {
        return JEIEssencePlugin.ESSENCE_STATION;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Essence Station");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EssenceStationRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 12, 16).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 16).addItemStack(new ItemStack(recipe.getIngredients().get(1).getItems()[0].getItem(), recipe.getItemInCount()));

        assert Minecraft.getInstance().level != null;
        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60)//.addIngredients(recipe.getIngredients().get(1));

                .addItemStack(new ItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getItem(), recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getCount()));

    }
}