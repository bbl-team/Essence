package com.benbenlaw.essence.data.custom;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.recipe.ResourceDuplicatorRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Consumer;

public class ResourceDuplicatorRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final int essenceInCount;
    private final int resourceOutCount;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public ResourceDuplicatorRecipeBuilder(ItemLike ingredient1, ItemLike ingredient2, int essenceInCount, int resourceOutCount) {
        this.ingredient = Ingredient.of (ingredient1, ingredient2);
        this.essenceInCount = essenceInCount;
        this.resourceOutCount = resourceOutCount;

    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return ItemStack.EMPTY.getItem().asItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new Result(pRecipeId, this.resourceOutCount,
                this.essenceInCount, this.ingredient, this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/"
                + pRecipeId.getPath())));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredients;
        private final int essenceInCount;
        private final int resourceOutCount;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, int essenceInCount, int resourceOutCount, Ingredient ingredients, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.essenceInCount = essenceInCount;
            this.resourceOutCount = resourceOutCount;
            this.ingredients = ingredients;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();

            for (ItemStack itemStack : ingredients.getItems()) {
                JsonObject ingredientObject = new JsonObject();
                ingredientObject.addProperty("item", ForgeRegistries.ITEMS.getKey(itemStack.getItem()).toString());
                jsonarray.add(ingredientObject);
            }

            pJson.add("ingredients", jsonarray);

            pJson.addProperty("essenceInCount", this.essenceInCount);
            pJson.addProperty("outCount", this.resourceOutCount);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(Essence.MOD_ID,
                    ForgeRegistries.ITEMS.getKey(this.ingredients.getItems()[1].getItem()).getPath());
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ResourceDuplicatorRecipe.Serializer.INSTANCE;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}