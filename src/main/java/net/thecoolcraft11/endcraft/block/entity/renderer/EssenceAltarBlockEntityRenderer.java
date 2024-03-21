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
import net.thecoolcraft11.endcraft.block.entity.EssenceAltarBlockEntity;

public class EssenceAltarBlockEntityRenderer implements BlockEntityRenderer<EssenceAltarBlockEntity> {
    public EssenceAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    @Override
    public void render(EssenceAltarBlockEntity essenceAltarBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = essenceAltarBlockEntity.getRenderStack();

        poseStack.pushPose();
        poseStack.translate(0.5f, essenceAltarBlockEntity.getScale() * 1.5,0.5f);
        poseStack.scale(essenceAltarBlockEntity.getScale() * 2.75f, essenceAltarBlockEntity.getScale() * 2.75f, essenceAltarBlockEntity.getScale() * 2.75f);

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(essenceAltarBlockEntity.getLevel(), essenceAltarBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, essenceAltarBlockEntity.getLevel(), 1);
        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

}
