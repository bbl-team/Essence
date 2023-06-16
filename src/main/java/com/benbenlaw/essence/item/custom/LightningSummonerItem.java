package com.benbenlaw.essence.item.custom;

import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.fluid.ModFluidTypes;
import com.benbenlaw.essence.fluid.ModFluids;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.WaterloggedVegetationPatchFeature;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;


public class LightningSummonerItem extends Item {
    public LightningSummonerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        BlockPos blockPos = pContext.getClickedPos();
        Level world = pContext.getLevel();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);
        FluidState fluidState = pContext.getLevel().getFluidState(blockPos);

        if (blockState.is(Blocks.LIGHTNING_ROD) && fluidState.is(Fluids.WATER)) {

            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
            lightning.setPos(Vec3.upFromBottomCenterOf(blockPos, 1));
            world.addFreshEntity(lightning);
            world.setBlockAndUpdate(blockPos, ModBlocks.LIGHTNING_WATER_BLOCK.get().defaultBlockState());
            pContext.getPlayer().getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, pContext.getPlayer(),
                    (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable Level pLevel, java.util.List<net.minecraft.network.chat.Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.essence.lightning_summoner.tooltip.shift").withStyle(ChatFormatting.GREEN));
        }
        else
            pTooltipComponents.add(Component.translatable("tooltip.essence.lightning_summoner.tooltip").withStyle(ChatFormatting.BLUE));
    }
}
