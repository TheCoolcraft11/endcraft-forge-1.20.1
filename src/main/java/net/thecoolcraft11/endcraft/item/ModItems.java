package net.thecoolcraft11.endcraft.item;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.enchantment.ModEnchantments;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.item.custom.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Endcraft.MOD_ID);

    public static final RegistryObject<Item> ENDERITE_SCRAP = ITEMS.register("enderite_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_SWORD = ITEMS.register("enderite_sword",
            () -> new SwordItem(ModToolTiers.ENDERITE, 8, 1, new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_PICKAXE = ITEMS.register("enderite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ENDERITE, 5, 1, new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_AXE = ITEMS.register("enderite_axe",
            () -> new AxeItem(ModToolTiers.ENDERITE, 9, 1, new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_SHOVEL = ITEMS.register("enderite_shovel",
            () -> new ShovelItem(ModToolTiers.ENDERITE, 4, 1, new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_HOE = ITEMS.register("enderite_hoe",
            () -> new HoeItem(ModToolTiers.ENDERITE, 2, 1, new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_ESSENCE = ITEMS.register("empty_essence",
            () -> new EmptyEssenceItem(new Item.Properties().durability(64)));

    public static final RegistryObject<Item> OVERWORLD_ESSENCE = ITEMS.register("overworld_essence",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> VOID_ESSENCE = ITEMS.register("void_essence",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> NETHER_ESSENCE = ITEMS.register("nether_essence",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> END_ESSENCE = ITEMS.register("end_essence",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ESSENCE_CORE = ITEMS.register("essence_core",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ENDERITE_CORE = ITEMS.register("enderite_core",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ENDERITE_ENERGY = ITEMS.register("enderite_energy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_STAFF = ITEMS.register("ender_staff",
            () -> new EnderStaffItem(new Item.Properties().stacksTo(1).durability(2048)));
    public static final RegistryObject<Item> ENERGY_CELL = ITEMS.register("energy_cell",
            () -> new EnergyCellItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SHADOW_VEIL = ITEMS.register("shadow_veil",
            () -> new ShadowVeilItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> VOID_GHOST_SPAWN_EGG = ITEMS.register("void_ghost_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.VOID_GHOST, 0x000000, 0xFFFFFF, new Item.Properties()));

        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_1 = ITEMS.register("ender_upgrade_range_1",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 1));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_2 = ITEMS.register("ender_upgrade_range_2",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 2));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_3 = ITEMS.register("ender_upgrade_range_3",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 3));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_4 = ITEMS.register("ender_upgrade_range_4",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 4));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_5 = ITEMS.register("ender_upgrade_range_5",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 5));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_6 = ITEMS.register("ender_upgrade_range_6",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 6));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_7 = ITEMS.register("ender_upgrade_range_7",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 7));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_8 = ITEMS.register("ender_upgrade_range_8",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 8));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_9 = ITEMS.register("ender_upgrade_range_9",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 9));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_10 = ITEMS.register("ender_upgrade_range_10",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 10));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_11 = ITEMS.register("ender_upgrade_range_11",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 11));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_12 = ITEMS.register("ender_upgrade_range_12",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 12));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_13 = ITEMS.register("ender_upgrade_range_13",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 13));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_14 = ITEMS.register("ender_upgrade_range_14",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 14));
        public static final RegistryObject<Item> ENDER_UPGRADE_RANGE_15 = ITEMS.register("ender_upgrade_range_15",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "range", 15));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_1 = ITEMS.register("ender_upgrade_durability_1",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 1));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_2 = ITEMS.register("ender_upgrade_durability_2",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 2));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_3 = ITEMS.register("ender_upgrade_durability_3",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 3));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_4 = ITEMS.register("ender_upgrade_durability_4",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 4));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_5 = ITEMS.register("ender_upgrade_durability_5",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 5));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_6 = ITEMS.register("ender_upgrade_durability_6",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 6));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_7 = ITEMS.register("ender_upgrade_durability_7",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 7));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_8 = ITEMS.register("ender_upgrade_durability_8",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 8));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_9 = ITEMS.register("ender_upgrade_durability_9",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 9));
        public static final RegistryObject<Item> ENDER_UPGRADE_DURABILITY_10 = ITEMS.register("ender_upgrade_durability_10",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "durability", 10));

        public static final RegistryObject<Item> ENDER_UPGRADE_FALL_1 = ITEMS.register("ender_upgrade_fall_1",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "fall", 1));
        public static final RegistryObject<Item> ENDER_UPGRADE_FALL_2 = ITEMS.register("ender_upgrade_fall_2",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "fall", 2));
        public static final RegistryObject<Item> ENDER_UPGRADE_FALL_3 = ITEMS.register("ender_upgrade_fall_3",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "fall", 3));
        public static final RegistryObject<Item> ENDER_UPGRADE_FALL_4 = ITEMS.register("ender_upgrade_fall_4",
                () -> new EnderUpgrade(new Item.Properties().stacksTo(1), "fall", 4));
        public static final RegistryObject<Item> OCULUS_ORE = ITEMS.register("oculus_ore",
                () -> new OculusOreItem(new Item.Properties().durability(1)));
        public static final RegistryObject<Item> ENDERITE_HELMET = ITEMS.register("enderite_helmet",
                () -> new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> ENDERITE_CHESTPLATE = ITEMS.register("enderite_chestplate",
                () -> new ModArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> ENDERITE_LEGGINGS = ITEMS.register("enderite_leggings",
                () -> new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> ENDERITE_BOOTS = ITEMS.register("enderite_boots",
                () -> new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.BOOTS, new Item.Properties()));
        public static final RegistryObject<Item> PHANTASMA_PRISM_ITEM = ITEMS.register("phantasma_prism",
                () -> new PhantasmaPrismItem(new Item.Properties()));
        public static final RegistryObject<Item> ENDERITE_CHEST_KEY = ITEMS.register("enderite_chest_key",
                () -> new EnderiteChestKey(new Item.Properties()));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}