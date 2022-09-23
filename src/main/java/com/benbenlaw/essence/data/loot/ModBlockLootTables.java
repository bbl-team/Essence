package com.benbenlaw.essence.data.loot;

import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        simpleDrops();
    }

    private void simpleDrops() {
        this.dropSelf(ModBlocks.BASIC_MOB_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.ELITE_MOB_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.BASIC_ORE_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.ELITE_ORE_ESSENCE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
