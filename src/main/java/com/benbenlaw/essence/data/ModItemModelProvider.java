package com.benbenlaw.essence.data;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Essence.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        customItemModels();
    }

    private void customItemModels() {
        simpleItem(ModItems.ESSENCE_UPGRADER);
        simpleItem(ModItems.ACTIVATED_SPAWNER_SHARD);
        simpleItem(ModItems.SPAWNER_SHARD_EXTRACTOR);
        simpleItem(ModItems.LIGHTNING_SUMMONER);

        simpleItem(ModItems.SPAWNER_SHARD);
        simpleItem(ModItems.LIGHTNING_WATER_BUCKET);
        simpleItem(ModItems.BASIC_MOB_ESSENCE);
        simpleItem(ModItems.BASIC_ORE_ESSENCE);
        simpleItem(ModItems.ADVANCED_ORE_ESSENCE);
        simpleItem(ModItems.ADVANCED_MOB_ESSENCE);
        simpleItem(ModItems.ELITE_ORE_ESSENCE);
        simpleItem(ModItems.ELITE_MOB_ESSENCE);

    //    spawnEgg(ModItems.RESOURCE_SLIME_SPAWN_EGG);


    //    simpleItem(ModItems.LIGHTNING_WATER_BUCKET);


        simpleBlock(ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK);
        simpleBlock(ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK);

        simpleBlock(ModBlocks.ELITE_ORE_ESSENCE_BLOCK);
        simpleBlock(ModBlocks.ELITE_MOB_ESSENCE_BLOCK);

        simpleBlock(ModBlocks.BASIC_ORE_ESSENCE_BLOCK);
        simpleBlock(ModBlocks.BASIC_MOB_ESSENCE_BLOCK);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Essence.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlock(RegistryObject<Block> block) {
        return cubeAll(block.getId().getPath(), new ResourceLocation(Essence.MOD_ID,
                "block/" + block.getId().getPath()));
    }
}