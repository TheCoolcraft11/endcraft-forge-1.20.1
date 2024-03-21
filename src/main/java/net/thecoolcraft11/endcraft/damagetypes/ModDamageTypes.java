package net.thecoolcraft11.endcraft.damagetypes;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModDamageTypes {


    public static final ResourceKey<DamageType> ESSENCE_AREA_REMOVED = register("death.essence.removed_area");
    public static final ResourceKey<DamageType> SWAM_VOID = register("death.swam_void");
    public static ResourceKey<DamageType> register(String string) {
     return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Endcraft.MOD_ID, string));
    }
    public static DamageSource of(Level world, ResourceKey<DamageType> key) {
        return  new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }
}

