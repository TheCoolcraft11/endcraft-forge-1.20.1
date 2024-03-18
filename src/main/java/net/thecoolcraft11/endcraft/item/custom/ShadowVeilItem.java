package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.client.gui.spectator.RootSpectatorMenuCategory;
import net.minecraft.client.telemetry.TelemetryProperty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class ShadowVeilItem extends Item {
    public ShadowVeilItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.remove(Entity.RemovalReason.DISCARDED);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pPlayer, int pSlotId, boolean pIsSelected) {
        pPlayer.noCulling = true;
        pPlayer.noPhysics = true;
        pPlayer.horizontalCollision = false;
        pPlayer.verticalCollision = false;
        pPlayer.verticalCollisionBelow = false;
        pPlayer.minorHorizontalCollision = false;
        pPlayer.setSwimming(true);
        pPlayer.updateSwimming();
        ((Player) pPlayer).getAbilities().mayfly = true;
        ((Player) pPlayer).getAbilities().flying = true;
        ((Player) pPlayer).getAbilities().mayBuild = false;
        ((Player) pPlayer).getAbilities().invulnerable = true;
        ((Player) pPlayer).getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(0);
        super.inventoryTick(pStack, pLevel, pPlayer, pSlotId, pIsSelected);
    }
}
