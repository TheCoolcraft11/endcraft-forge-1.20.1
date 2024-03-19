package net.thecoolcraft11.endcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.ItemStackHandler;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.ModBlockEntities;
import net.thecoolcraft11.endcraft.enchantment.ModEnchantments;
import net.thecoolcraft11.endcraft.item.ModCreativeModTabs;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.networking.ModMessages;
import net.thecoolcraft11.endcraft.screen.EnderChargerMenu;
import net.thecoolcraft11.endcraft.screen.EnderChargerScreen;
import net.thecoolcraft11.endcraft.screen.EnderForgeConverterScreen;
import net.thecoolcraft11.endcraft.screen.ModMenuTypes;
import org.slf4j.Logger;


@Mod(Endcraft.MOD_ID)
public class Endcraft {
    public static final String MOD_ID = "endcraft";
    public static final Logger LOGGER = LogUtils.getLogger();


    public Endcraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModEnchantments.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register(); //event.enqueWork(() -> {here});
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ENDER_FORGE_CONVERTER.get(), RenderType.cutout());
            MenuScreens.register(ModMenuTypes.ENDER_FORGE_CONVERTER_MENU.get(), EnderForgeConverterScreen::new);
            MenuScreens.register(ModMenuTypes.ENDER_CHARGER_MENU.get(), EnderChargerScreen::new);
        }
    }
}
