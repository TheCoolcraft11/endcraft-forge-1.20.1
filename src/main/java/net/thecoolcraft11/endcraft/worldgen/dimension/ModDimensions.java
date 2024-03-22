package net.thecoolcraft11.endcraft.worldgen.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModDimensions {
    public static final ResourceKey<LevelStem> VOIDBORN_ABYSS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Endcraft.MOD_ID, "voidborn_abyss"));
    public static final ResourceKey<Level> VOIDBORN_ABYSS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Endcraft.MOD_ID, "voidborn_abyss"));
    public static final ResourceKey<DimensionType> VOIDBORN_ABYSS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Endcraft.MOD_ID, "voidborn_abyss_type"));

}
