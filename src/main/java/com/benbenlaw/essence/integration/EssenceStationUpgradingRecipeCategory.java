package com.benbenlaw.essence.integration;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.item.ModItems;
import com.benbenlaw.essence.recipe.EssenceStationConvertingRecipe;
import com.benbenlaw.essence.recipe.EssenceStationUpgradingRecipe;
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

public class EssenceStationUpgradingRecipeCategory implements IRecipeCategory<EssenceStationUpgradingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Essence.MOD_ID, "essence_station_upgrading");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Essence.MOD_ID, "textures/gui/essence_station_gui_jei.png");

    static final RecipeType<EssenceStationUpgradingRecipe> RECIPE_TYPE = RecipeType.create(Essence.MOD_ID, "essence_station_upgrading",
            EssenceStationUpgradingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EssenceStationUpgradingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ESSENCE_STATION.get()));
    }

    @Override
    public RecipeType<EssenceStationUpgradingRecipe> getRecipeType() {
        return JEIEssencePlugin.UPGRADING;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Essence (Upgrading)");
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
    public void setRecipe(IRecipeLayoutBuilder builder, EssenceStationUpgradingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 12, 16).addItemStack(new ItemStack(ModItems.ESSENCE_UPGRADER.get()));
        int inCount = recipe.getInCount();
        int outCount = recipe.getOutCount();

        builder.addSlot(RecipeIngredientRole.INPUT, 86, 16)
                .addItemStack(new ItemStack(recipe.getIngredients().get(0).getItems()[0].getItem(), inCount));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60) //.addItemStack(recipe.getResultItem());
                .addItemStack(new ItemStack(recipe.getResultItem().getItem(), outCount));
    }
}