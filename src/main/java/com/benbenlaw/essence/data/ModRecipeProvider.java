package com.benbenlaw.essence.data;

import com.benbenlaw.essence.data.custom.ResourceDuplicatorRecipeBuilder;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput p_254020_) {
        return super.run(p_254020_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //Basic Ore Essence

        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_ORE_ESSENCE.get(), Items.DIRT.asItem(), 1, 24).unlockedBy("has_essence", has(ModItems.BASIC_ORE_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_ORE_ESSENCE.get(), Items.COBBLESTONE.asItem(), 1, 24).unlockedBy("has_essence", has(ModItems.BASIC_ORE_ESSENCE.get())).save(pWriter);
        

        //Advanced Ore Essence

        new ResourceDuplicatorRecipeBuilder(ModItems.ADVANCED_ORE_ESSENCE.get(), Items.AMETHYST_SHARD.asItem(), 3, 1).unlockedBy("has_essence", has(ModItems.ADVANCED_ORE_ESSENCE.get())).save(pWriter);




        //Basic Mob Essence

        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_MOB_ESSENCE.get(), Items.BONE.asItem(), 3, 1).unlockedBy("has_essence", has(ModItems.BASIC_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_MOB_ESSENCE.get(), Items.LEATHER.asItem(), 2, 1).unlockedBy("has_essence", has(ModItems.BASIC_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_MOB_ESSENCE.get(), Items.ROTTEN_FLESH.asItem(), 2, 1).unlockedBy("has_essence", has(ModItems.BASIC_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_MOB_ESSENCE.get(), Items.SPIDER_EYE.asItem(), 2, 1).unlockedBy("has_essence", has(ModItems.BASIC_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.BASIC_MOB_ESSENCE.get(), Items.STRING.asItem(), 2, 1).unlockedBy("has_essence", has(ModItems.BASIC_MOB_ESSENCE.get())).save(pWriter);

        //Advanced Mob Essence

        new ResourceDuplicatorRecipeBuilder(ModItems.ADVANCED_MOB_ESSENCE.get(), Items.PRISMARINE_CRYSTALS.asItem(), 3, 1).unlockedBy("has_essence", has(ModItems.ADVANCED_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ADVANCED_MOB_ESSENCE.get(), Items.PRISMARINE_SHARD.asItem(), 3, 1).unlockedBy("has_essence", has(ModItems.ADVANCED_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ADVANCED_MOB_ESSENCE.get(), Items.SLIME_BALL.asItem(), 4, 1).unlockedBy("has_essence", has(ModItems.ADVANCED_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ADVANCED_MOB_ESSENCE.get(), Items.GUNPOWDER.asItem(), 2, 1).unlockedBy("has_essence", has(ModItems.ADVANCED_MOB_ESSENCE.get())).save(pWriter);


        //Elite Mob Essence

        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.BLAZE_ROD.asItem(), 4, 2).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.GHAST_TEAR.asItem(), 4, 2).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.NETHER_STAR.asItem(), 32, 1).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), ModItems.SPAWNER_SHARD.get(), 8, 1).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.WITHER_SKELETON_SKULL, 4, 1).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.SKELETON_SKULL, 1, 1).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.ZOMBIE_HEAD, 1, 1).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);
        new ResourceDuplicatorRecipeBuilder(ModItems.ELITE_MOB_ESSENCE.get(), Items.CREEPER_HEAD, 1, 1).unlockedBy("has_essence", has(ModItems.ELITE_MOB_ESSENCE.get())).save(pWriter);


    }
}
