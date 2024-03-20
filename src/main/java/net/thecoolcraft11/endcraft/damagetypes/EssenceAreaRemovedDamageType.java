package net.thecoolcraft11.endcraft.damagetypes;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class EssenceAreaRemovedDamageType extends DamageSource {
    public EssenceAreaRemovedDamageType(Holder<DamageType> pType, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        super(pType, pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity pLivingEntity) {
        return Component.translatable("death.attack.essence.removed_area");
    }
}
