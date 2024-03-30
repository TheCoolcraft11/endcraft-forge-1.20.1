package net.thecoolcraft11.endcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
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
        simpleItem(ModItems.OCULUS_ORE);

        trimmedArmorItem(ModItems.ENDERITE_HELMET);
        trimmedArmorItem(ModItems.ENDERITE_CHESTPLATE);
        trimmedArmorItem(ModItems.ENDERITE_LEGGINGS);
        trimmedArmorItem(ModItems.ENDERITE_BOOTS);

        simpleItem(ModItems.ENDERITE_CHEST_KEY);
        simpleItem(ModItems.ENDERITE_CHEST_ACCESS_PEARL);
        simpleItem(ModItems.ENDERITE_CHEST_OWNER_PEARL);

    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = Endcraft.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
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
