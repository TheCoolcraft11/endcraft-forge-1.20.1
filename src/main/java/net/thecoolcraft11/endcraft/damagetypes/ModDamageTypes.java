package net.thecoolcraft11.endcraft.damagetypes;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModDamageTypes {


    public static final ResourceKey<DamageType> ESSENCE_AREA_REMOVED = register("death.essence.removed_area");
    public static ResourceKey<DamageType> register(String string) {
     return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Endcraft.MOD_ID, string));
    }
}

