package net.thecoolcraft11.endcraft.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.enchantment.ModEnchantments;
import net.thecoolcraft11.endcraft.particle.ModParticles;
import net.thecoolcraft11.endcraft.util.Raycast;

import java.util.function.Supplier;

public class VoidBornPortalActivatorC2SPacket {
    public VoidBornPortalActivatorC2SPacket() {
    }
    public VoidBornPortalActivatorC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel world = player.serverLevel().getLevel();
            HitResult hitResult = player.pick(player.getBlockReach(),0,false);
            Endcraft.LOGGER.info(String.valueOf(hitResult));
            world.sendParticles(ModParticles.VOID_BORN_PARTICLES.get(), hitResult.getLocation().x, hitResult.getLocation().y,hitResult.getLocation().z,2000,0,0,0,2);
        });
        return true;
    }

}
