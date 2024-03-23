package net.thecoolcraft11.endcraft.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.statuseffect.ModStatusEffects;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Endcraft.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onEntityHurt(LivingHurtEvent event) {
            if(event.getSource().getEntity() != null) {
            if (event.getSource().getEntity().getType() == ModEntities.VOID_GHOST.get()) {
                if (event.getEntity().getEffect(ModStatusEffects.VOID.get()) != null) {
                    MobEffectInstance mobEffectInstance = new MobEffectInstance(ModStatusEffects.VOID.get(), 60, event.getEntity().getEffect(ModStatusEffects.VOID.get()).getAmplifier() + 1);
                    event.getEntity().addEffect(mobEffectInstance);
                } else {
                    MobEffectInstance mobEffectInstance = new MobEffectInstance(ModStatusEffects.VOID.get(), 60, 1);
                    event.getEntity().addEffect(mobEffectInstance);
                }
                event.getSource().getEntity().kill();
                }
            }
        }
    }
}
