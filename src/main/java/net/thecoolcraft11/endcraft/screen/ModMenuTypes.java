package net.thecoolcraft11.endcraft.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Endcraft.MOD_ID);

    public static final RegistryObject<MenuType<EnderForgeConverterMenu>> ENDER_FORGE_CONVERTER_MENU =
            registerMenuType("ender_forge_converter_menu", EnderForgeConverterMenu::new);
    public static final RegistryObject<MenuType<EnderChargerMenu>> ENDER_CHARGER_MENU =
            registerMenuType("ender_charger_menu", EnderChargerMenu::new);
    public static final RegistryObject<MenuType<EssenceAltarMenu>> ESSENCE_ALTAR_MENU =
            registerMenuType("essence_altar_menu", EssenceAltarMenu::new);
    public static final RegistryObject<MenuType<ModTableMenu>> MOD_TABLE_MENU =
            registerMenuType("mod_table_menu", ModTableMenu::new);
    public static final RegistryObject<MenuType<OculusCombinerMenu>> OCULUS_COMBINER_MENU =
            registerMenuType("oculus_combiner_menu", OculusCombinerMenu::new);
    public static final RegistryObject<MenuType<EnderiteChestMenu>> ENDERITE_CHEST_MENU =
            registerMenuType("enderite_chest_menu", EnderiteChestMenu::new);


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
