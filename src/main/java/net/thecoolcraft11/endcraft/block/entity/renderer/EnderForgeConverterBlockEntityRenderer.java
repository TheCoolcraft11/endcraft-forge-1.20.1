package net.thecoolcraft11.endcraft.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.thecoolcraft11.endcraft.block.entity.EnderForgeConverterBlockEntity;

public class EnderForgeConverterBlockEntityRenderer implements BlockEntityRenderer<EnderForgeConverterBlockEntity> {
    public EnderForgeConverterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    @Override
    public void render(EnderForgeConverterBlockEntity enderForgeConverterBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = enderForgeConverterBlockEntity.getRenderStack();

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.45f, 0.5f);
        poseStack.scale(0.35f, 0.35f, 0.35f);
        poseStack.mulPose(Axis.XP.rotationDegrees(270));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(enderForgeConverterBlockEntity.getLevel(), enderForgeConverterBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, enderForgeConverterBlockEntity.getLevel(), 1);
        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
