package net.thecoolcraft11.endcraft.statuseffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class ShadowVeilEffect extends MobEffect {

    protected ShadowVeilEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pEntity, int pAmplifier) {
            if (pEntity instanceof Player) {
                if(shouldHaveEffect(((Player) pEntity))) {
                    pEntity.noCulling = true;
                    pEntity.noPhysics = true;
                    pEntity.horizontalCollision = false;
                    pEntity.verticalCollision = false;
                    pEntity.verticalCollisionBelow = false;
                    pEntity.minorHorizontalCollision = false;
                    ((Player) pEntity).getAbilities().mayfly = true;
                    ((Player) pEntity).getAbilities().flying = true;
                    ((Player) pEntity).getAbilities().mayBuild = false;
                    ((Player) pEntity).getAbilities().invulnerable = true;
                    ((Player) pEntity).getAbilities().setFlyingSpeed(0.15f);
                    MobEffectInstance mobEffectInstance = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1, 255, false, false, false);
                    MobEffectInstance mobEffectInstance2 = new MobEffectInstance(MobEffects.WEAKNESS, 1, 255, false, false, false);
                    ((Player) pEntity).addEffect(mobEffectInstance);
                    ((Player) pEntity).addEffect(mobEffectInstance2);
                }else {
                    if (!(((Player) pEntity).isCreative() || pEntity.isSpectator())) {
                        ((Player) pEntity).getAbilities().mayfly = false;
                        ((Player) pEntity).getAbilities().flying = false;
                        ((Player) pEntity).getAbilities().invulnerable = false;
                    }
                    if(!pEntity.isSpectator()) {
                        pEntity.noCulling = false;
                        pEntity.noPhysics = false;
                        pEntity.horizontalCollision = true;
                        pEntity.verticalCollision = true;
                        pEntity.verticalCollisionBelow = true;
                        pEntity.minorHorizontalCollision = true;

                    }
                    ((Player) pEntity).getAbilities().setFlyingSpeed(0.1f);
                    ((Player) pEntity).getAbilities().mayBuild = true;
                    ((Player) pEntity).getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1);
                    pEntity.removeEffect(MobEffects.DIG_SLOWDOWN);
                    pEntity.removeEffect(MobEffects.WEAKNESS);
                }
        }
        super.applyEffectTick(pEntity, pAmplifier);
    }
    private boolean shouldHaveEffect(Player player) {
        return  player.getEffect(ModStatusEffects.SHADOW_VEIL.get()).getDuration() > 2 || player.getEffect(ModStatusEffects.SHADOW_VEIL.get()).isInfiniteDuration();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
