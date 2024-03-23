package net.thecoolcraft11.endcraft.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;
import net.thecoolcraft11.endcraft.particle.ModParticles;
import net.thecoolcraft11.endcraft.particle.custom.EnderFlameParticle;
import net.thecoolcraft11.endcraft.particle.custom.VoidBornPortalParticle;
import net.thecoolcraft11.endcraft.statuseffect.ModStatusEffects;

@Mod.EventBusSubscriber(modid = Endcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvent {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.VOID_BORN_PARTICLES.get(),
                VoidBornPortalParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.ENDER_FLAME.get(),
                EnderFlameParticle.Provider::new);
    }
    @SubscribeEvent
    public static void registerAttributers(EntityAttributeCreationEvent event) {
        event.put(ModEntities.VOID_GHOST.get(), VoidGhostEntity.createAttributes().build());
    }

}

