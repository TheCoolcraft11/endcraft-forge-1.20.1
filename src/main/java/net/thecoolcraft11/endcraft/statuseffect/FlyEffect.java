package net.thecoolcraft11.endcraft.statuseffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.thecoolcraft11.endcraft.Endcraft;

public class FlyEffect extends MobEffect {
    protected FlyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
            if (pLivingEntity instanceof Player) {
                if(shouldHaveFlyingEffect(((Player) pLivingEntity))) {
                    Endcraft.LOGGER.info("T1");
                    ((Player) pLivingEntity).getAbilities().mayfly = true;
                    ((Player) pLivingEntity).getAbilities().setFlyingSpeed(0.1f * (pAmplifier + 1));
                }else {
                    if (!(((Player) pLivingEntity).isCreative() || pLivingEntity.isSpectator())) {
                        Endcraft.LOGGER.info("T2");
                        ((Player) pLivingEntity).getAbilities().flying = false;
                        ((Player) pLivingEntity).getAbilities().mayfly = false;
                    }
                    ((Player) pLivingEntity).getAbilities().setFlyingSpeed(0.1f);
                }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
    private boolean shouldHaveFlyingEffect(Player player) {
        return  player.getEffect(ModStatusEffects.FLY.get()).getDuration() > 2 || player.getEffect(ModStatusEffects.FLY.get()).isInfiniteDuration();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
