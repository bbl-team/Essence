package com.benbenlaw.essence.integration;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.fluid.ModFluidTypes;
import com.benbenlaw.essence.fluid.ModFluids;
import com.benbenlaw.essence.item.ModItems;
import com.benbenlaw.essence.util.ModTags;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class InformationJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Essence.MOD_ID, "information");
    }
     @Override
    public void registerRecipes(IRecipeRegistration reg) {

        reg.addIngredientInfo(new ItemStack(ModItems.SPAWNER_SHARD.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.spawner_shard"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.SPAWNER_SHARD_EXTRACTOR.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.spawner_shard_extractor"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.LIGHTNING_WATER_BUCKET.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.lightning_water_block"));
    //    reg.addIngredientInfo(new ItemStack(ModBlocks.LIGHTNING_WATER_BLOCK.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.lightning_water_block"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ACTIVATED_SPAWNER_SHARD.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.activated_spawner_shard"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ESSENCE_UPGRADER.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.essence_upgrader"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ESSENCE_CONVERTER.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.essence_converter"));
//
    //    reg.addIngredientInfo(new ItemStack(ModItems.BASIC_MOB_ESSENCE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.basic_mob_essence"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.BASIC_ORE_ESSENCE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.basic_ore_essence"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ADVANCED_MOB_ESSENCE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.advanced_mob_essence"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ADVANCED_ORE_ESSENCE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.advanced_ore_essence"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ELITE_MOB_ESSENCE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.elite_mob_essence"));
    //    reg.addIngredientInfo(new ItemStack(ModItems.ELITE_ORE_ESSENCE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.essence.elite_ore_essence"));


    }
}
