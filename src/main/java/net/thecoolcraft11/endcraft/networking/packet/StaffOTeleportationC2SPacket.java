package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
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
           ResourceKey<Level> registryKey = Level.OVERWORLD;
           ResourceKey<Level> registryKey2 = Level.NETHER;
           ResourceKey<Level> registryKey3 = Level.END;
            if (player.getMainHandItem().getItem() == ModItems.STAFF_OF_TELEPORTATION.get()) {
                ServerLevel serverWorld = ((ServerLevel)player.level()).getServer().getLevel(registryKey);
                ServerLevel serverWorld2 = ((ServerLevel)player.level()).getServer().getLevel(registryKey2);
                ServerLevel serverWorld3 = ((ServerLevel)player.level()).getServer().getLevel(registryKey3);
                Endcraft.LOGGER.info(String.valueOf(type));
                if(player.getMainHandItem().getOrCreateTag().getString("Level"+ type).equals("overworld")) {
                    player.teleportTo(serverWorld,player.getMainHandItem().getOrCreateTag().getInt("x"+ type), player.getMainHandItem().getOrCreateTag().getInt("y"+ type), player.getMainHandItem().getOrCreateTag().getInt("z"+ type), 1f,1f);
                }
                if(player.getMainHandItem().getOrCreateTag().getString("Level"+ type).equals("nether")) {
                    player.teleportTo(serverWorld2,player.getMainHandItem().getOrCreateTag().getInt("x"+ type), player.getMainHandItem().getOrCreateTag().getInt("y"+ type), player.getMainHandItem().getOrCreateTag().getInt("z"+ type), 1f,1f);
                }
                if(player.getMainHandItem().getOrCreateTag().getString("Level"+ type).equals("end")) {
                    player.teleportTo(serverWorld3,player.getMainHandItem().getOrCreateTag().getInt("x"+ type), player.getMainHandItem().getOrCreateTag().getInt("y"+ type), player.getMainHandItem().getOrCreateTag().getInt("z"+ type), 1f,1f);
                }
                player.getCooldowns().addCooldown(ModItems.STAFF_OF_TELEPORTATION.get(), 50);
                player.getMainHandItem().hurtAndBreak(1, player, player1 -> player.broadcastBreakEvent(player.getMainHandItem().getEquipmentSlot()));
            }
        });
        return true;
    }
}
