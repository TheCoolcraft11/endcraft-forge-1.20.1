package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.util.Raycast;

import java.rmi.registry.Registry;
import java.util.Set;
import java.util.function.Supplier;

public class StaffOTeleportationC2SPacket {
    private final int type;
    public StaffOTeleportationC2SPacket(int type) {
        this.type = type;

    }
    public StaffOTeleportationC2SPacket(FriendlyByteBuf buf) {
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
            if (player.getMainHandItem().getItem() == ModItems.STAFF_OF_TELEPORTATION.get() && !player.getCooldowns().isOnCooldown(ModItems.STAFF_OF_TELEPORTATION.get()) && player.getMainHandItem().getDamageValue() <= 0) {
                CompoundTag tag = player.getMainHandItem().getOrCreateTag();
                if (tag.contains("x" + type) && tag.contains("y" + type) && tag.contains("z" + type) && tag.contains("Level" + type)) {
                    player.teleportTo(deserializeServerLevel(world.getServer(), tag.getString("Level" + type)), tag.getFloat("x" + type), tag.getFloat("y" + type), tag.getFloat("z" + type), 0f, 0f);
                    player.getMainHandItem().setDamageValue(player.getMainHandItem().getMaxDamage());
                    player.getCooldowns().addCooldown(ModItems.STAFF_OF_TELEPORTATION.get(), 50);
                } else {
                    player.playNotifySound(SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1f, 1f);
                }
            }
        });
        return true;
    }
    private static ServerLevel deserializeServerLevel(MinecraftServer server, String dimensionName) {
        for (ServerLevel level : server.getAllLevels()) {
            if (level.dimension().location().toString().equals(dimensionName)) {
                return level;
            }
        }
        return null;
    }
}
