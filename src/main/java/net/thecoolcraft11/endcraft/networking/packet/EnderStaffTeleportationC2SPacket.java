package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.custom.EnderStaffItem;
import net.thecoolcraft11.endcraft.util.Raycast;

import java.util.function.Supplier;

public class EnderStaffTeleportationC2SPacket {
    public EnderStaffTeleportationC2SPacket() {

    }
    public EnderStaffTeleportationC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel world = player.serverLevel().getLevel();

            double reachDistance = 64;
            if(EnderStaffItem.hasNbtUpgrade(player.getMainHandItem(), "range")) {
                reachDistance = 64 + 10 * EnderStaffItem.getNbtLevel(player.getMainHandItem(), "range");
            }
            if (!(player.getMainHandItem().getDamageValue() >= player.getMainHandItem().getMaxDamage())) {

                player.teleportTo(world,
                        Raycast.raycastFromPlayer(player, world, reachDistance).getX() + 0.5,
                        Raycast.raycastFromPlayer(player, world, reachDistance).getY() + 1,
                        Raycast.raycastFromPlayer(player, world, reachDistance).getZ() + 0.5,
                        player.getYRot(),
                        player.getXRot());
                System.out.println(reachDistance);
                player.sendSystemMessage(Component.literal("ReachDistance: " + reachDistance));

                if (EnderStaffItem.hasNbtUpgrade(player.getMainHandItem(), "durability")) {
                    player.getMainHandItem().setDamageValue((int) (player.getMainHandItem().getDamageValue() + 5 / EnderStaffItem.getNbtLevel(player.getMainHandItem(), "durability")));
                } else {
                    player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 5);
                }
                if (EnderStaffItem.hasNbtUpgrade(player.getMainHandItem(), "fall")) {
                    player.fallDistance = (float) (player.fallDistance - EnderStaffItem.getNbtLevel(player.getMainHandItem(), "fall") * 10);
                }
            }
        });
        return true;
    }

}
