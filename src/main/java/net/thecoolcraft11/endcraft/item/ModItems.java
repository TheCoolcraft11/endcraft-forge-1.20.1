package net.thecoolcraft11.endcraft.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.custom.EmptyEssenceItem;
import net.thecoolcraft11.endcraft.item.custom.EnderStaffItem;
import net.thecoolcraft11.endcraft.item.custom.EnergyCellItem;
import net.thecoolcraft11.endcraft.item.custom.ShadowVeilItem;

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
    public static final RegistryObject<Item> ENDERITE_ENERGY = ITEMS.register("enderite_engery",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ENDER_STAFF = ITEMS.register("ender_staff",
            () -> new EnderStaffItem(new Item.Properties().stacksTo(1).durability(2048)));
    public static final RegistryObject<Item> ENERGY_CELL = ITEMS.register("enderite_cell",
            () -> new EnergyCellItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SHADOW_VEIL = ITEMS.register("shadow_veil",
            () -> new ShadowVeilItem(new Item.Properties().stacksTo(1)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}