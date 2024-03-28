package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import net.thecoolcraft11.endcraft.particle.ModParticles;

import java.util.function.Supplier;

public class OpenEnderiteChestC2SPacket {
    private final BlockPos pos;
    public OpenEnderiteChestC2SPacket(BlockPos pos) {
        this.pos = pos;
    }
    public OpenEnderiteChestC2SPacket(FriendlyByteBuf buf) {
    this.pos = buf.readBlockPos();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel world = player.serverLevel().getLevel();;
            BlockEntity entity = world.getBlockEntity(pos);
            if(entity instanceof EnderiteChestBlockEntity) {
                NetworkHooks.openScreen(player, (EnderiteChestBlockEntity)entity, pos);
                world.sendParticles(ModParticles.ENDER_FLAME.get(), pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5,500,0.2,1,-0.2,2);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        });
        return true;
    }

}
