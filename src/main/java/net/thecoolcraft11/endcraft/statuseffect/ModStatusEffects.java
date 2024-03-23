package net.thecoolcraft11.endcraft.statuseffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModStatusEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Endcraft.MOD_ID);

    public static final RegistryObject<MobEffect> FLY = EFFECTS.register("fly", () -> new FlyEffect(MobEffectCategory.BENEFICIAL, 0x98D982));
    public static final RegistryObject<MobEffect> VOID = EFFECTS.register("void", () -> new VoidEffect(MobEffectCategory.BENEFICIAL, 0x98D982));
    public static final RegistryObject<MobEffect> SHADOW_VEIL = EFFECTS.register("shadow_veil", () -> new ShadowVeilEffect(MobEffectCategory.BENEFICIAL, 0x005255).addAttributeModifier(Attributes.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386", -1, AttributeModifier.Operation.MULTIPLY_BASE));


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
