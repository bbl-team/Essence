package com.benbenlaw.essence.block.entity.client;

import com.benbenlaw.essence.block.entity.custom.ResourceDuplicatorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraftforge.common.Tags;

public class ResourceDuplicatorBlockEntityRenderer implements BlockEntityRenderer<ResourceDuplicatorBlockEntity> {
    public ResourceDuplicatorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    /*

    @Override
    public void render(ResourceDuplicatorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();

        pPoseStack.translate(0.5f, 1.05f, 0.5);  //x, y, x pos
        pPoseStack.scale(1.3f, 1.3f, 1.3f); //size
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));

        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GROUND, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);

        pPoseStack.popPose();
    }


     */

    @Override
    public void render(ResourceDuplicatorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();
        float time = pBlockEntity.getLevel().getGameTime();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1.25f, 0.5);  //x, y, x pos
        pPoseStack.scale(1.3f, 1.3f, 1.3f); //size
        pPoseStack.mulPose(Vector3f.YP.rotation(time / 20.0F));
        if (!itemRenderer.getItemModelShaper().getItemModel(itemStack).isGui3d()) {
            pPoseStack.scale(0.75F, 0.75F, 0.75F);
        }
        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GROUND, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);

        pPoseStack.popPose();
    }


    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }




}