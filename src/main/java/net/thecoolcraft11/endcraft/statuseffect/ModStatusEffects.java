package net.thecoolcraft11.endcraft.statuseffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModStatusEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Endcraft.MOD_ID);

    public static final RegistryObject<MobEffect> FLY = EFFECTS.register("fly", () -> new FlyEffect(MobEffectCategory.BENEFICIAL, 0x98D982));
    public static final RegistryObject<MobEffect> VOID = EFFECTS.register("void", () -> new VoidEffect(MobEffectCategory.BENEFICIAL, 0x98D982));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
