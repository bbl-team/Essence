package com.benbenlaw.essence.recipe;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.item.ModItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.nullness.qual.Nullable;

public class EssenceStationUpgradingRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int inCount;
    private final int outCount;

    public EssenceStationUpgradingRecipe(ResourceLocation id, ItemStack output,
                                         NonNullList<Ingredient> recipeItems, int inCount, int outCount) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.inCount = inCount;
        this.outCount = outCount;

    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(pContainer.getItem(1));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public int getInCount() {
        return this.inCount;
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

    public static class Type implements RecipeType<EssenceStationUpgradingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "essence_station_upgrading";
    }

    public static class Serializer implements RecipeSerializer<EssenceStationUpgradingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Essence.MOD_ID, "essence_station_upgrading");

        @Override
        public EssenceStationUpgradingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            int inCount = GsonHelper.getAsInt(pSerializedRecipe, "inCount");
            int outCount = GsonHelper.getAsInt(pSerializedRecipe, "outCount");

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new EssenceStationUpgradingRecipe(pRecipeId, output, inputs, inCount, outCount);
        }

        @Override
        public @Nullable EssenceStationUpgradingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int inCount = buf.readInt();
            int outCount = buf.readInt();

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new EssenceStationUpgradingRecipe(id, output, inputs, inCount, outCount);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, EssenceStationUpgradingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeInt(recipe.getInCount());
            buf.writeInt(recipe.getOutCount());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}