package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.client.gui.spectator.RootSpectatorMenuCategory;
import net.minecraft.client.telemetry.TelemetryProperty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.statuseffect.ModStatusEffects;

public class ShadowVeilItem extends Item {
    public ShadowVeilItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        MobEffectInstance effectInstance = new MobEffectInstance(ModStatusEffects.SHADOW_VEIL.get(), 300, 1, false, false);
        pPlayer.addEffect(effectInstance);
        pPlayer.getCooldowns().addCooldown(ModItems.SHADOW_VEIL.get(), 1200);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}
