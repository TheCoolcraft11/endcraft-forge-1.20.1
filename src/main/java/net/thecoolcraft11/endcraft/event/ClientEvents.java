package net.thecoolcraft11.endcraft.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.client.Keybinding;
import net.thecoolcraft11.endcraft.networking.ModMessages;
import net.thecoolcraft11.endcraft.networking.packet.EnderiteArmorTeleportationC2SPacket;
import net.thecoolcraft11.endcraft.networking.packet.StaffOTeleportationC2SPacket;


public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Endcraft.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(Keybinding.ARMOR_ABILITY_KEY.consumeClick()) {
                ModMessages.sendToServer(new EnderiteArmorTeleportationC2SPacket(0));
            }
            if (Keybinding.TELEPORT_KEY_HOLD.isDown()) {
                if(Keybinding.ARMOR_ABILITY_KEY_2.consumeClick()) {
                    ModMessages.sendToServer(new EnderiteArmorTeleportationC2SPacket(1));
                }
                if(Keybinding.TELEPORT_KEY_1.consumeClick()) {
                    ModMessages.sendToServer(new StaffOTeleportationC2SPacket(1));
                }
                if(Keybinding.TELEPORT_KEY_2.consumeClick()) {
                    ModMessages.sendToServer(new StaffOTeleportationC2SPacket(2));
                }
                if(Keybinding.TELEPORT_KEY_3.consumeClick()) {
                    ModMessages.sendToServer(new StaffOTeleportationC2SPacket(3));
                }
            }
        }
    }
    @Mod.EventBusSubscriber(modid = Endcraft.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.ARMOR_ABILITY_KEY);
            event.register(Keybinding.ARMOR_ABILITY_KEY_2);
            event.register(Keybinding.TELEPORT_KEY_1);
            event.register(Keybinding.TELEPORT_KEY_2);
            event.register(Keybinding.TELEPORT_KEY_3);

        }
    }
}
