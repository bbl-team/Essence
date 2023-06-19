package com.benbenlaw.essence.block.entity.client;

import com.benbenlaw.essence.block.entity.custom.ResourceDuplicatorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.items.ItemStackHandler;
import org.joml.Vector3f;

import java.util.Objects;

public class ResourceDuplicatorBlockEntityRenderer implements BlockEntityRenderer<ResourceDuplicatorBlockEntity> {

    public ResourceDuplicatorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(ResourceDuplicatorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {


        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();

        if (!itemStack.isEmpty()) {

            pPoseStack.pushPose();

            // Get the block's facing direction
            BlockState blockState = Objects.requireNonNull(pBlockEntity.getLevel()).getBlockState(pBlockEntity.getBlockPos());

            if (blockState.getBlock() == Blocks.AIR) {
                // Handle air block case
                return; // or perform any other necessary actions
            }

            Direction blockFacing = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);

            // Translate the position to the front of the block based on its facing direction
            pPoseStack.translate(0.5f + blockFacing.getStepX() * 0.5f,
                    0.5f + blockFacing.getStepY() * 0.5f,
                    0.5f + blockFacing.getStepZ() * 0.5f);

            pPoseStack.scale(0.7f, 0.7f, 0.7f); //size
            BakedModel model = itemRenderer.getModel(itemStack, null, null, 0);

            if (!itemRenderer.getItemModelShaper().getItemModel(itemStack).isGui3d()) {
                pPoseStack.scale(0.75F, 0.75F, 0.75F);
            }

            itemRenderer.render(itemStack, ItemDisplayContext.FIXED, true, pPoseStack, pBufferSource,
                    getLightLevel(Objects.requireNonNull(pBlockEntity.getLevel()), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, model);

            pPoseStack.popPose();
        }
    }



    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }




}