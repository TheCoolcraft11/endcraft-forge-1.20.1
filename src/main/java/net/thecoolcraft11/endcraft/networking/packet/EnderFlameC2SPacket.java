package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.particle.ModParticles;

import java.util.function.Supplier;

public class EnderFlameC2SPacket {
    private final BlockPos pos;
    public EnderFlameC2SPacket(BlockPos pos) {
        this.pos = pos;
    }
    public EnderFlameC2SPacket(FriendlyByteBuf buf) {
    this.pos = buf.readBlockPos();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel world = player.serverLevel().getLevel();
            Endcraft.LOGGER.info("Test");
            Endcraft.LOGGER.info(String.valueOf(pos));
            world.sendParticles(ModParticles.ENDER_FLAME.get(), pos.getX(), pos.getY(), pos.getZ(),2000,-0.2,2.5,0.2,2);
        });
        return true;
    }

}
