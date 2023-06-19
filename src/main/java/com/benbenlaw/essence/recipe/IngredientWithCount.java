package com.benbenlaw.essence.recipe;

import net.minecraft.world.item.crafting.Ingredient;

public class IngredientWithCount {
    private final Ingredient ingredient;
    private final int count;

    public IngredientWithCount(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCount() {
        return count;
    }
}
