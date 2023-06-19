package com.benbenlaw.essence.integration;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.recipe.ResourceDuplicatorRecipe;
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

public class ResourceDuplicatorRecipeCategory implements IRecipeCategory<ResourceDuplicatorRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Essence.MOD_ID, "resource_duplicator");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Essence.MOD_ID, "textures/gui/resource_duplicator_gui_jei.png");

    static final RecipeType<ResourceDuplicatorRecipe> RECIPE_TYPE = RecipeType.create(Essence.MOD_ID, "resource_duplicator",
            ResourceDuplicatorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public ResourceDuplicatorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.RESOURCE_DUPLICATOR.get()));
    }

    @Override
    public RecipeType<ResourceDuplicatorRecipe> getRecipeType() {
        return JEIEssencePlugin.DUPLICATING;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Resource Duplicator");
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
    public void setRecipe(IRecipeLayoutBuilder builder, ResourceDuplicatorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 12, 16)
                .addItemStack(new ItemStack(recipe.getIngredients().get(0).getItems()[0].getItem(), recipe.getEssenceInCount()));

        builder.addSlot(RecipeIngredientRole.CATALYST, 86, 16)
                .addItemStack(new ItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getItem()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60) //.addItemStack(recipe.getResultItem());
                .addItemStack(new ItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getItem(), recipe.getOutCount()));
    }
}