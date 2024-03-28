package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.util.Raycast;

import java.util.function.Supplier;

public class EnderiteArmorTeleportationC2SPacket {
    private final int type;
    public EnderiteArmorTeleportationC2SPacket(int type) {
        this.type = type;

    }
    public EnderiteArmorTeleportationC2SPacket(FriendlyByteBuf buf) {
        this.type = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(type);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel world = player.serverLevel().getLevel();
            if (type == 0) {
                double reachDistance = 8;
                if(hasFullArmorOn(player)) {
                    if(!ArmorHasCooldown(player)) {
                        player.teleportTo(world,
                                Raycast.raycastFromPlayer(player, world, reachDistance+ 2).getX() + 0.5,
                                Raycast.raycastFromPlayer(player, world, reachDistance).getY()+ 2 + 1,
                                Raycast.raycastFromPlayer(player, world, reachDistance).getZ()+ 2 + 0.5,
                                player.getYRot(),
                                player.getXRot());
                        addArmorCooldowns(player, 100);
                        damageArmor(player, 8);
                        player.playNotifySound(SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 1f);
                    }
                }
            }else if (type == 1) {
                double reachDistance = 32;
                if(hasFullArmorOn(player)) {
                    if (!ArmorHasCooldown(player)) {
                        player.teleportTo(world,
                                Raycast.raycastFromPlayer(player, world, reachDistance).getX()+ 2 + 0.5,
                                Raycast.raycastFromPlayer(player, world, reachDistance).getY()+ 2 + 1,
                                Raycast.raycastFromPlayer(player, world, reachDistance).getZ()+ 2 + 0.5,
                                player.getYRot(),
                                player.getXRot());
                        addArmorCooldowns(player, 500);
                        damageArmor(player, 32);
                        player.playNotifySound(SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 1f);
                    }
                }
            }
        });
        return true;
    }

    private void addArmorCooldowns(ServerPlayer player, int cooldown) {
        player.getCooldowns().addCooldown(ModItems.ENDERITE_BOOTS.get(), cooldown);
        player.getCooldowns().addCooldown(ModItems.ENDERITE_LEGGINGS.get(), cooldown);
        player.getCooldowns().addCooldown(ModItems.ENDERITE_CHESTPLATE.get(), cooldown);
        player.getCooldowns().addCooldown(ModItems.ENDERITE_HELMET.get(), cooldown);
    }
    private void damageArmor(ServerPlayer player, int damage) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);
        boots.hurtAndBreak(damage, player, player1 -> player.broadcastBreakEvent(player.getInventory().getArmor(0).getEquipmentSlot()));
        leggings.hurtAndBreak(damage, player, player1 -> player.broadcastBreakEvent(player.getInventory().getArmor(1).getEquipmentSlot()));
        chestplate.hurtAndBreak(damage, player, player1 -> player.broadcastBreakEvent(player.getInventory().getArmor(2).getEquipmentSlot()));
        helmet.hurtAndBreak(damage, player, player1 -> player.broadcastBreakEvent(player.getInventory().getArmor(3).getEquipmentSlot()));
    }

    private boolean ArmorHasCooldown(ServerPlayer player) {
       boolean boots =  player.getCooldowns().isOnCooldown(ModItems.ENDERITE_BOOTS.get());
       boolean leggings =  player.getCooldowns().isOnCooldown(ModItems.ENDERITE_LEGGINGS.get());
       boolean chestplate =  player.getCooldowns().isOnCooldown(ModItems.ENDERITE_CHESTPLATE.get());
       boolean helmet =  player.getCooldowns().isOnCooldown(ModItems.ENDERITE_HELMET.get());

       return (boots && leggings && chestplate && helmet);
    }

    private boolean hasFullArmorOn(ServerPlayer player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return (boots.getItem() == ModItems.ENDERITE_BOOTS.get() && leggings.getItem() == ModItems.ENDERITE_LEGGINGS.get() && chestplate.getItem() == ModItems.ENDERITE_CHESTPLATE.get() && helmet.getItem() == ModItems.ENDERITE_HELMET.get());
    }

}
