package com.benbenlaw.essence.item.custom;

import com.benbenlaw.essence.config.ConfigFile;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class SpawnerShardExtractorItem extends Item {
    public SpawnerShardExtractorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        BlockPos blockPos = pContext.getClickedPos();
        Level world = pContext.getLevel();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);
        Random rand = new Random();
        int maxXP = 14;
        int mixXP = 5;
        int randomXP = rand.nextInt(maxXP) - rand.nextInt(mixXP);

        if (blockState.is(Blocks.SPAWNER)) {

            world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

            world.addFreshEntity((new ExperienceOrb(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomXP)));
            world.addFreshEntity((new ExperienceOrb(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomXP)));
            world.addFreshEntity((new ExperienceOrb(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomXP)));

            pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                    (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

            if (Math.random() > ConfigFile.spawnerShardChance.get()) {
                world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        new ItemStack(ModItems.SPAWNER_SHARD.get())));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable Level pLevel, java.util.List<net.minecraft.network.chat.Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.essence.spawner_shard_extractor.tooltip.shift").withStyle(ChatFormatting.GREEN));
        }
        else
            pTooltipComponents.add(Component.translatable("tooltip.essence.spawner_shard_extractor.tooltip").withStyle(ChatFormatting.BLUE));
    }
}
