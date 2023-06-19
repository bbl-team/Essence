package com.benbenlaw.essence.item.custom;

import com.benbenlaw.essence.config.ConfigFile;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
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
        BlockEntity blockentity = null;

        if (blockState.is(Blocks.SPAWNER)) {

            blockentity = world.getBlockEntity(blockPos);




            world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

       //     world.addFreshEntity((new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),  new ItemStack(Items.SPAWNER.asItem()).setTag(this.addCustomNbtData(Items.SPAWNER.asItem().getDefaultInstance(), blockentity));

        //   assert blockentity != null;
         //   ItemStack spawnerItem = this.addCustomNbtData(Items.SPAWNER.asItem().getDefaultInstance(), blockentity);

            pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                    (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

            if (Math.random() > ConfigFile.spawnerShardChance.get()) {
                world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        new ItemStack(ModItems.SPAWNER_SHARD.get())));
            }
        }
        return InteractionResult.SUCCESS;
    }


    private void addCustomNbtData(ItemStack p_263370_, BlockEntity p_263368_) {
        CompoundTag compoundtag = p_263368_.saveWithFullMetadata();
        BlockItem.setBlockEntityData(p_263370_, p_263368_.getType(), compoundtag);
        if (p_263370_.getItem() instanceof PlayerHeadItem && compoundtag.contains("SkullOwner")) {
            CompoundTag compoundtag3 = compoundtag.getCompound("SkullOwner");
            CompoundTag compoundtag4 = p_263370_.getOrCreateTag();
            compoundtag4.put("SkullOwner", compoundtag3);
            CompoundTag compoundtag2 = compoundtag4.getCompound("BlockEntityTag");
            compoundtag2.remove("SkullOwner");
            compoundtag2.remove("x");
            compoundtag2.remove("y");
            compoundtag2.remove("z");
        } else {
            CompoundTag compoundtag1 = new CompoundTag();
            ListTag listtag = new ListTag();
            listtag.add(StringTag.valueOf("\"(+NBT)\""));
            compoundtag1.put("Lore", listtag);
            p_263370_.addTagElement("display", compoundtag1);
        }
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
