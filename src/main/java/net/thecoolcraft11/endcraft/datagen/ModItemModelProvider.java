package net.thecoolcraft11.endcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Endcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ENDERITE_INGOT);

        handheldItem(ModItems.ENDERITE_SWORD);
        handheldItem(ModItems.ENDERITE_PICKAXE);
        handheldItem(ModItems.ENDERITE_AXE);
        handheldItem(ModItems.ENDERITE_SHOVEL);
        handheldItem(ModItems.ENDERITE_HOE);
        simpleItem(ModItems.SHADOW_VEIL);
        simpleItem(ModItems.ENDERITE_SCRAP);
        simpleItem(ModItems.ESSENCE_CORE);
        simpleItem(ModItems.ENDERITE_CORE);
        withExistingParent(ModItems.VOID_GHOST_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_1);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_2);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_3);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_4);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_5);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_6);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_7);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_8);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_9);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_10);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_11);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_12);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_13);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_14);
        simpleItem(ModItems.ENDER_UPGRADE_RANGE_15);
        simpleItem(ModItems.ENDER_UPGRADE_FALL_1);
        simpleItem(ModItems.ENDER_UPGRADE_FALL_2);
        simpleItem(ModItems.ENDER_UPGRADE_FALL_3);
        simpleItem(ModItems.ENDER_UPGRADE_FALL_4);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_1);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_2);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_3);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_4);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_5);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_6);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_7);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_8);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_9);
        simpleItem(ModItems.ENDER_UPGRADE_DURABILITY_10);
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Endcraft.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Endcraft.MOD_ID,"item/" + item.getId().getPath()));
    }
}
