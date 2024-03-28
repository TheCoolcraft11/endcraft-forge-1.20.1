package net.thecoolcraft11.endcraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static final String KEY_CATEGORY_ENDCRAFT = "key.category.endcraft";
    public static final String KEY_ARMOR_ABILITY = "key.endcraft.armor_ability";
    public static final String KEY_ARMOR_ABILITY_2 = "key.endcraft.armor_ability_2";

    public static final KeyMapping ARMOR_ABILITY_KEY = new KeyMapping(KEY_ARMOR_ABILITY, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_ENDCRAFT);
    public static final KeyMapping ARMOR_ABILITY_KEY_2 = new KeyMapping(KEY_ARMOR_ABILITY_2, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY_ENDCRAFT);



}
