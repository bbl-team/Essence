package com.benbenlaw.essence.integration;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.item.ModItems;
import com.benbenlaw.essence.recipe.EssenceStationConvertingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class EssenceStationConvertingRecipeCategory implements IRecipeCategory<EssenceStationConvertingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Essence.MOD_ID, "essence_station_converting");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Essence.MOD_ID, "textures/gui/essence_station_gui_jei.png");

    static final RecipeType<EssenceStationConvertingRecipe> RECIPE_TYPE = RecipeType.create(Essence.MOD_ID, "essence_station_converting",
            EssenceStationConvertingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EssenceStationConvertingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ESSENCE_STATION.get()));
    }

    @Override
    public RecipeType<EssenceStationConvertingRecipe> getRecipeType() {
        return JEIEssencePlugin.CONVERTING;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Essence (Converting)");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EssenceStationConvertingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 12, 16).addItemStack(new ItemStack(ModItems.ESSENCE_CONVERTER.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 16).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}