package net.thecoolcraft11.endcraft.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.networking.packet.EnderFlameC2SPacket;
import net.thecoolcraft11.endcraft.networking.packet.EnderStaffTeleportationC2SPacket;
import net.thecoolcraft11.endcraft.networking.packet.VoidBornPortalActivatorC2SPacket;
import net.thecoolcraft11.endcraft.particle.custom.EnderFlameParticle;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Endcraft.MOD_ID, "messages")).networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EnderStaffTeleportationC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(EnderStaffTeleportationC2SPacket::new).encoder(EnderStaffTeleportationC2SPacket::toBytes).consumerMainThread(EnderStaffTeleportationC2SPacket::handle).add();
        net.messageBuilder(VoidBornPortalActivatorC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(VoidBornPortalActivatorC2SPacket::new).encoder(VoidBornPortalActivatorC2SPacket::toBytes).consumerMainThread(VoidBornPortalActivatorC2SPacket::handle).add();
        net.messageBuilder(EnderFlameC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(EnderFlameC2SPacket::new).encoder(EnderFlameC2SPacket::toBytes).consumerMainThread(EnderFlameC2SPacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
