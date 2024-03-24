package net.thecoolcraft11.endcraft.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Endcraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<EnderForgeConverterBlockEntity>> ENDER_FORGE_BE = BLOCK_ENTITIES.register("ender_forge_be",() ->BlockEntityType.Builder.of(EnderForgeConverterBlockEntity::new, ModBlocks.ENDER_FORGE_CONVERTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<EnderChargerBlockEntity>> ENDER_CHARGER_BE = BLOCK_ENTITIES.register("ender_charger_be",() ->BlockEntityType.Builder.of(EnderChargerBlockEntity::new, ModBlocks.ENDER_CHARGER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<EssenceAltarBlockEntity>> ESSENCE_ALTAR_BE = BLOCK_ENTITIES.register("essence_altar_be",() ->BlockEntityType.Builder.of(EssenceAltarBlockEntity::new, ModBlocks.ESSENCE_ALTAR.get()).build(null));
    public static final RegistryObject<BlockEntityType<EndPedastelBlockEntity>> END_PEDASTEL_BE = BLOCK_ENTITIES.register("end_pedastel_be",() ->BlockEntityType.Builder.of(EndPedastelBlockEntity::new, ModBlocks.END_PEDASTEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModTableBlockEntity>> MOD_TABLE_BE = BLOCK_ENTITIES.register("mod_table_be",() ->BlockEntityType.Builder.of(ModTableBlockEntity::new, ModBlocks.MOD_TABLE_BLOCK.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
