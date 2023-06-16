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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class EssenceUpgraderItem extends SimpleFoiledItem {
    public EssenceUpgraderItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        BlockPos blockPos = pContext.getClickedPos();
        Level world = pContext.getLevel();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);
        Player tellPlayer = pContext.getPlayer();

        FluidState northBlock = pContext.getLevel().getFluidState(blockPos.north());
        FluidState southBlock = pContext.getLevel().getFluidState(blockPos.south());
        FluidState eastBlock = pContext.getLevel().getFluidState(blockPos.east());
        FluidState westBlock = pContext.getLevel().getFluidState(blockPos.west());

        if (blockState.is(ModBlocks.BASIC_MOB_ESSENCE_BLOCK.get())) {

            if(!world.isClientSide) {

                if (!northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && !eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    tellPlayer.sendSystemMessage(Component.translatable("world.essence.needs_lightning_water").withStyle(ChatFormatting.RED));

                } else if (northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

                    pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                            (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                    world.addFreshEntity(new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() + 1.01, blockPos.getZ() + 0.5,
                            new ItemStack(ModItems.ADVANCED_MOB_ESSENCE.get())));
                }
            }
        }

        if (blockState.is(ModBlocks.BASIC_ORE_ESSENCE_BLOCK.get())) {

            if(!world.isClientSide) {

                if (!northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && !eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    tellPlayer.sendSystemMessage(Component.translatable("world.essence.needs_lightning_water").withStyle(ChatFormatting.RED));

                } else if (northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

                    pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                            (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                    world.addFreshEntity(new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() + 1.01, blockPos.getZ() + 0.5,
                            new ItemStack(ModItems.ADVANCED_ORE_ESSENCE.get())));
                }
            }
        }

        if (blockState.is(ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK.get())) {

            if(!world.isClientSide) {

                if (!northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && !eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    tellPlayer.sendSystemMessage(Component.translatable("world.essence.needs_lightning_water").withStyle(ChatFormatting.RED));

                } else if (northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

                    pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                            (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                    world.addFreshEntity(new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() + 1.01, blockPos.getZ() + 0.5,
                            new ItemStack(ModItems.ELITE_ORE_ESSENCE.get())));
                }
            }
        }

        if (blockState.is(ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK.get())) {

            if(!world.isClientSide) {

                if (!northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && !eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && !westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    tellPlayer.sendSystemMessage(Component.translatable("world.essence.needs_lightning_water").withStyle(ChatFormatting.RED));

                } else if (northBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && southBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())
                        && eastBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get()) && westBlock.is(ModFluids.SOURCE_LIGHTNING_WATER.get())) {

                    world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

                    pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                            (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                    world.addFreshEntity(new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() + 1.01, blockPos.getZ() + 0.5,
                            new ItemStack(ModItems.ELITE_MOB_ESSENCE.get())));
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
