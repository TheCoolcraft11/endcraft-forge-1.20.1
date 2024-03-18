package net.thecoolcraft11.endcraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class Raycast {
    public static BlockPos raycastFromPlayer(Player player, ServerLevel world, double distance) {
        Vec3 playerPos = player.position();

        Vec3 lookVec = player.getLookAngle();

        double endX = playerPos.x + lookVec.x * distance;
        double endY = playerPos.y+ 1 + lookVec.y * distance;
        double endZ = playerPos.z + lookVec.z * distance;

        BlockPos endPos = new BlockPos((int) endX, (int) endY, (int) endZ);

        BlockPos hitPos = endPos;
        for (int i = 0; i < distance; i++) {
            BlockPos currentPos = new BlockPos(
                    (int) (playerPos.x + lookVec.x * i),
                    (int) (playerPos.y+1 + lookVec.y * i),
                    (int) (playerPos.z + lookVec.z * i)
            );
            if (world.getBlockState(currentPos).isSolid()) {
                hitPos = currentPos;
                break;
            }
        }
        return hitPos;
    }
}

