package net.thecoolcraft11.endcraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyMappingLookup;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static final String KEY_CATEGORY_ENDCRAFT = "key.category.endcraft";
    public static final String KEY_ARMOR_ABILITY = "key.endcraft.armor_ability";
    public static final String KEY_ARMOR_ABILITY_2 = "key.endcraft.armor_ability_2";
    public static final String KEY_TELEPORT_1 = "key.endcraft.teleport_1";
    public static final String KEY_TELEPORT_2 = "key.endcraft.teleport_2";
    public static final String KEY_TELEPORT_3 = "key.endcraft.teleport_3";
    public static final String KEY_TELEPORT_HOLD = "key.endcraft.teleport_hold";

    public static final KeyMapping ARMOR_ABILITY_KEY = new KeyMapping(KEY_ARMOR_ABILITY, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_WORLD_2, KEY_CATEGORY_ENDCRAFT);
    public static final KeyMapping ARMOR_ABILITY_KEY_2 = new KeyMapping(KEY_ARMOR_ABILITY_2, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_WORLD_2, KEY_CATEGORY_ENDCRAFT);
    public static final KeyMapping TELEPORT_KEY_1 = new KeyMapping(KEY_TELEPORT_1, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY_ENDCRAFT);
    public static final KeyMapping TELEPORT_KEY_2 = new KeyMapping(KEY_TELEPORT_2, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY_ENDCRAFT);
    public static final KeyMapping TELEPORT_KEY_3 = new KeyMapping(KEY_TELEPORT_3, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_ENDCRAFT);
    public static final KeyMapping TELEPORT_KEY_HOLD = new KeyMapping(KEY_TELEPORT_HOLD, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, KEY_CATEGORY_ENDCRAFT);



}
