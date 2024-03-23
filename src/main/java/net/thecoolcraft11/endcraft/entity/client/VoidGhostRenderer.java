package net.thecoolcraft11.endcraft.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;

public class VoidGhostRenderer extends MobRenderer<VoidGhostEntity, VoidGhostModel<VoidGhostEntity>> {
    public VoidGhostRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VoidGhostModel<>(pContext.bakeLayer(ModModelLayers.VOID_GHOST_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(VoidGhostEntity voidGhostEnity) {
        return new ResourceLocation(Endcraft.MOD_ID, "textures/entity/void_ghost.png");
    }

}
