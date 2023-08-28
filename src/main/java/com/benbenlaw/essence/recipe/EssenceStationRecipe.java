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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

public class EssenceStationRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int itemInCount;

    public EssenceStationRecipe(ResourceLocation id, ItemStack output,
                                NonNullList<Ingredient> recipeItems, int itemInCount) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.itemInCount = itemInCount;

    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        if (pContainer.getItem(0).is(recipeItems.get(0).getItems()[0].getItem())) {
            return pContainer.getItem(1).is(recipeItems.get(1).getItems()[0].getItem());
        }

        return false;


    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(@NotNull SimpleContainer pContainer, @NotNull RegistryAccess p_267165_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(@NotNull RegistryAccess p_267052_) {
        return output.copy();
    }

    public int getItemInCount() {
        return itemInCount;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Type implements RecipeType<EssenceStationRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "essence_station";
    }

    public static class Serializer implements RecipeSerializer<EssenceStationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Essence.MOD_ID, "essence_station");

        @Override
        public @NotNull EssenceStationRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            int itemInCount = GsonHelper.getAsInt(pSerializedRecipe, "itemInCount", 1);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new EssenceStationRecipe(pRecipeId, output, inputs, itemInCount);
        }

        @Override
        public @Nullable EssenceStationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int itemInCount = buf.readInt();


            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new EssenceStationRecipe(id, output, inputs, itemInCount);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, EssenceStationRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeInt(recipe.getItemInCount());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.output, false);
        }
    }
}