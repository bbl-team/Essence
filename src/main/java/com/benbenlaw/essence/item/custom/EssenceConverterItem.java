package com.benbenlaw.essence.item.custom;

import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.fluid.ModFluids;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class EssenceConverterItem extends Item {
    public EssenceConverterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        BlockPos blockPos = pContext.getClickedPos();
        Level world = pContext.getLevel();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);

        if (blockState.is(ModBlocks.BASIC_MOB_ESSENCE_BLOCK.get())) {

            if (!world.isClientSide) {

                world.setBlock(blockPos, ModBlocks.BASIC_ORE_ESSENCE_BLOCK.get().defaultBlockState(), 1);

                pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                        (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }

        if (blockState.is(ModBlocks.BASIC_ORE_ESSENCE_BLOCK.get())) {

            if (!world.isClientSide) {

                world.setBlock(blockPos, ModBlocks.BASIC_MOB_ESSENCE_BLOCK.get().defaultBlockState(), 1);

                pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                        (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }

        if (blockState.is(ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK.get())) {

            if (!world.isClientSide) {

                world.setBlock(blockPos, ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK.get().defaultBlockState(), 1);

                pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                        (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
        if (blockState.is(ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK.get())) {

            if (!world.isClientSide) {

                world.setBlock(blockPos, ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK.get().defaultBlockState(), 1);

                pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                        (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
        if (blockState.is(ModBlocks.ELITE_ORE_ESSENCE_BLOCK.get())) {

            if (!world.isClientSide) {

                world.setBlock(blockPos, ModBlocks.ELITE_MOB_ESSENCE_BLOCK.get().defaultBlockState(), 1);

                pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                        (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
        if (blockState.is(ModBlocks.ELITE_MOB_ESSENCE_BLOCK.get())) {

            if (!world.isClientSide) {

                world.setBlock(blockPos, ModBlocks.ELITE_ORE_ESSENCE_BLOCK.get().defaultBlockState(), 1);

                pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                        (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }


        return InteractionResult.SUCCESS;
    }
}
