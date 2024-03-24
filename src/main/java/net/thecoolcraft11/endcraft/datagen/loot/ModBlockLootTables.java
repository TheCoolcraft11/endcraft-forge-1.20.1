package net.thecoolcraft11.endcraft.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }


    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ENDERITE_ORE.get());
        this.dropSelf(ModBlocks.ENDER_FORGE_CONVERTER.get());
        this.dropSelf(ModBlocks.ENDER_CHARGER_BLOCK.get());
        this.dropSelf(ModBlocks.ENDERITE_BLOCK.get());
        this.dropSelf(ModBlocks.ESSENCE_ALTAR.get());
        this.dropSelf(ModBlocks.INFECTED_DIRT.get());
        this.dropSelf(ModBlocks.INFECTED_GRASS.get());
        this.dropSelf(ModBlocks.INFECTED_STONE.get());
        this.dropSelf(ModBlocks.MOD_TABLE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
