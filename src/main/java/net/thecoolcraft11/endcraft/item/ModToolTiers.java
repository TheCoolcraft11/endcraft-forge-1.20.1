package net.thecoolcraft11.endcraft.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier ENDERITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 3058, 14.0f, 4f, 25,
                    ModTags.Blocks.NEEDS_ENDERITE_TOOL, () -> Ingredient.of(ModItems.ENDERITE_INGOT.get())),
            new ResourceLocation(Endcraft.MOD_ID, "sapphire"), List.of(Tiers.NETHERITE), List.of());

}