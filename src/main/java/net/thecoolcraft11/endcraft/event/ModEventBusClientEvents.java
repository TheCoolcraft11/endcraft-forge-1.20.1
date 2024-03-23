package net.thecoolcraft11.endcraft.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.entity.ModBlockEntities;
import net.thecoolcraft11.endcraft.block.entity.renderer.EndPedastelBlockEntityRenderer;
import net.thecoolcraft11.endcraft.block.entity.renderer.EnderForgeConverterBlockEntityRenderer;
import net.thecoolcraft11.endcraft.block.entity.renderer.EssenceAltarBlockEntityRenderer;
import net.thecoolcraft11.endcraft.entity.client.ModModelLayers;
import net.thecoolcraft11.endcraft.entity.client.VoidGhostModel;

@Mod.EventBusSubscriber(modid = Endcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.ENDER_FORGE_BE.get(), EnderForgeConverterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.END_PEDASTEL_BE.get(), EndPedastelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ESSENCE_ALTAR_BE.get(), EssenceAltarBlockEntityRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.VOID_GHOST_LAYER, VoidGhostModel::createBodyLayer);
    }
}
