package com.benbenlaw.essence.util;

import com.benbenlaw.essence.Essence;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks {

        //New Block Tags

        public static final TagKey<Block> ESSENCE_ORE = forgeTag("ores/essence");




        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Essence.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

    //New Item Tags

    public static class Items {
        public static final TagKey<Item> ESSENCE_ESSENCES = tag("essence_essences");





        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Essence.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }

}