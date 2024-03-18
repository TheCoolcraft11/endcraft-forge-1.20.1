package net.thecoolcraft11.endcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Endcraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("endcraft",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VOID_ESSENCE.get()))
                    .title(Component.translatable("creativetab.endcraft"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.EMPTY_ESSENCE.get());
                        pOutput.accept(ModItems.VOID_ESSENCE.get());
                        pOutput.accept(ModItems.OVERWORLD_ESSENCE.get());
                        pOutput.accept(ModItems.NETHER_ESSENCE.get());
                        pOutput.accept(ModItems.END_ESSENCE.get());
                        pOutput.accept(ModBlocks.ENDER_FORGE_CONVERTER.get());
                        pOutput.accept(ModItems.ENDERITE_SWORD.get());
                        pOutput.accept(ModItems.ENDERITE_PICKAXE.get());
                        pOutput.accept(ModItems.ENDERITE_AXE.get());
                        pOutput.accept(ModItems.ENDERITE_SHOVEL.get());
                        pOutput.accept(ModItems.ENDERITE_HOE.get());
                        pOutput.accept(ModItems.ENDERITE_INGOT.get());
                        pOutput.accept(ModItems.ENDERITE_SCRAP.get());
                        pOutput.accept(ModItems.ENERGY_CELL.get());
                        pOutput.accept(ModItems.ENDERITE_ENERGY.get());
                        pOutput.accept(ModItems.ENDER_STAFF.get());
                        pOutput.accept(ModItems.SHADOW_VEIL.get());
                        pOutput.accept(ModItems.ESSENCE_CORE.get());
                        pOutput.accept(ModItems.ENDERITE_CORE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}