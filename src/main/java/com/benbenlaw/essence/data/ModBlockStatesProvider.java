package com.benbenlaw.essence.data;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.data.DataGenerator;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Random;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Essence.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.BASIC_MOB_ESSENCE_BLOCK.get());
        simpleBlock(ModBlocks.BASIC_ORE_ESSENCE_BLOCK.get());
        simpleBlock(ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK.get());
        simpleBlock(ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK.get());
        simpleBlock(ModBlocks.ELITE_MOB_ESSENCE_BLOCK.get());
        simpleBlock(ModBlocks.ELITE_ORE_ESSENCE_BLOCK.get());
    }
}