package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.network.NetworkEvent;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.enchantment.ModEnchantments;
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
            if(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get(), player.getMainHandItem()) != 0) {
                reachDistance = 64 + 10 * player.getMainHandItem().getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get());

            }
            if (!(player.getMainHandItem().getDamageValue() >= player.getMainHandItem().getMaxDamage())) {

                player.teleportTo(world,
                        Raycast.raycastFromPlayer(player, world, reachDistance).getX() + 0.5,
                        Raycast.raycastFromPlayer(player, world, reachDistance).getY() + 1,
                        Raycast.raycastFromPlayer(player, world, reachDistance).getZ() + 0.5,
                        player.getYRot(),
                        player.getXRot());

                    player.getMainHandItem().setDamageValue (player.getMainHandItem().getDamageValue() + 25 - EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get(), player.getMainHandItem()) * 2);

                if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get(), player.getMainHandItem()) != 0) {
                    if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get(), player.getMainHandItem()) == 4) {
                        player.fallDistance = -1;
                    }
                    else {
                        player.fallDistance = (float) (player.fallDistance - EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get(), player.getMainHandItem()) * 10);
                    }
                }
            }
        });
        return true;
    }

}
