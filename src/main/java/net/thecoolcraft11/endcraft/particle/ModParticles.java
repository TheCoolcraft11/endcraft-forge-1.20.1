package net.thecoolcraft11.endcraft.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Endcraft.MOD_ID);

    public static final RegistryObject<SimpleParticleType> VOID_BORN_PARTICLES = PARTICLE_TYPES.register("voidborn_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ENDER_FLAME = PARTICLE_TYPES.register("ender_flame", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
