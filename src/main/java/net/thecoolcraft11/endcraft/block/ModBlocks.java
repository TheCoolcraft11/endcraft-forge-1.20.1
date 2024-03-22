package net.thecoolcraft11.endcraft.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.custom.*;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Endcraft.MOD_ID);

    public static final RegistryObject<Block> ENDERITE_ORE = registerBlock("enderite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.AMETHYST).destroyTime(10f).explosionResistance(10f)));
    public static final RegistryObject<Block> ENDER_FORGE_CONVERTER = registerBlock("ender_forge_converter",
            () -> new EnderForgeConverterBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).noOcclusion()));
    public static final RegistryObject<Block> ENDER_CHARGER_BLOCK = registerBlock("ender_charger",
            () -> new EnderChargerBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> ESSENCE_ALTAR = registerBlock("essence_altar",
            () -> new EssenceAltarBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));
    public static final RegistryObject<Block> ENDERITE_BLOCK = registerBlock("enderite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> VOID_BLOCK = registerBlock("void_block",
            () -> new VoidBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).noLootTable().noCollission()));
    public static final RegistryObject<Block> VOID_LAYER = registerBlock("void_layer",
            () -> new VoidLayerBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).noLootTable().noCollission()));
    public static final RegistryObject<Block> END_PEDASTEL = registerBlock("end_pedastel",
            () -> new EndPedastelBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).noLootTable()));
    public static final RegistryObject<Block> INFECTED_GRASS = registerBlock("infected_grass",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> INFECTED_STONE = registerBlock("infected_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> INFECTED_DIRT = registerBlock("infected_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> VOIDBORN_ABYSS_PORTAL = registerBlock("voidborn_abyss_portal",
            () -> new VoidbornAbyssPortal(BlockBehaviour.Properties.copy(Blocks.END_PORTAL).noOcclusion().noCollission().noLootTable()));
    public static final RegistryObject<Block> VOIDBORN_ABYSS_PORTAL_ACTIVATOR = registerBlock("voidborn_abyss_portal_activator",
            () -> new VoidbornAbyssPortalActivator(BlockBehaviour.Properties.copy(Blocks.BEDROCK).noLootTable()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
