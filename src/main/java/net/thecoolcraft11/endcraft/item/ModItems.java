package net.thecoolcraft11.endcraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.custom.EmptyEssenceItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Endcraft.MOD_ID);

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



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}