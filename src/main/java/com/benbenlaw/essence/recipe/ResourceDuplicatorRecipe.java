package com.benbenlaw.essence.recipe;

import com.benbenlaw.essence.Essence;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

public class ResourceDuplicatorRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final NonNullList<Ingredient> recipeItems;
    private final int essenceInCount;
    private final int outCount;

    public ResourceDuplicatorRecipe(ResourceLocation id,
                                    NonNullList<Ingredient> recipeItems, int essenceInCount, int outCount) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.essenceInCount = essenceInCount;
        this.outCount = outCount;

    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        if (recipeItems.get(0).test(pContainer.getItem(0))) {
            if (recipeItems.get(1).test(pContainer.getItem(1)))
                return true;
        }

        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess p_267052_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return ItemStack.EMPTY;
    }

    public int getEssenceInCount() {
        return this.essenceInCount;
    }

    public int getOutCount() {
        return this.outCount;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Type implements RecipeType<ResourceDuplicatorRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "resource_duplicator";
    }

    public static class Serializer implements RecipeSerializer<ResourceDuplicatorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Essence.MOD_ID, "resource_duplicator");

        @Override
        public ResourceDuplicatorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            int essenceInCount = GsonHelper.getAsInt(pSerializedRecipe, "essenceInCount");
            int outCount = GsonHelper.getAsInt(pSerializedRecipe, "outCount");

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ResourceDuplicatorRecipe(pRecipeId, inputs, essenceInCount, outCount);
        }

        @Override
        public @Nullable ResourceDuplicatorRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int essenceInCount = buf.readInt();
            int outCount = buf.readInt();

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            return new ResourceDuplicatorRecipe(id, inputs, essenceInCount, outCount);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ResourceDuplicatorRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeInt(recipe.getEssenceInCount());
            buf.writeInt(recipe.getOutCount());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
        }
    }
}