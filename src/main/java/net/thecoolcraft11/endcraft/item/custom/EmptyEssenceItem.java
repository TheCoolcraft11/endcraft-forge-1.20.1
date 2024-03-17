package net.thecoolcraft11.endcraft.item.custom;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.thecoolcraft11.endcraft.item.ModItems;

public class EmptyEssenceItem extends Item {

    public EmptyEssenceItem(Properties properties) {
        super(properties);

    }


    @Override
    public int getBarColor(ItemStack stack) {
        return 0x7119F0;
    }

    public boolean isBarVisible(ItemStack stack) {
        return true;

    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        entity.noCulling = true;
        entity.noPhysics = true;
        ((Player) entity).getAbilities().mayBuild = false;
        ((Player) entity).getAbilities().mayfly = true;
        ((Player) entity).getAbilities().instabuild = true;
        ((Player) entity).getAbilities().invulnerable = true;
        ((Player) entity).attackAnim = 0.8f;
        ((Player) entity).horizontalCollision = false;
        ((Player) entity).verticalCollision = false;
        if (isInVoid((Player) entity)) {
            stack.getOrCreateTag().putInt("MaxDurability", stack.getOrCreateTag().getInt("MaxDurability") + 1);
            stack.getOrCreateTag().putString("essence", "the_void");
        }
        stack.setDamageValue(stack.getMaxDamage() - stack.getOrCreateTag().getInt("MaxDurability"));

        if(stack.getOrCreateTag().getInt("MaxDurability") >= 64) {
            if(stack.getOrCreateTag().getString("essence").equals("the_void")) {
                removeItem((Player) entity, getItem((Player) entity, ModItems.EMPTY_ESSENCE.get()));
                addItem(((Player) entity), ModItems.VOID_ESSENCE.get().getDefaultInstance());
            }
            if(stack.getOrCreateTag().getString("essence").equals("overworld")) {
                removeItem((Player) entity, getItem((Player) entity, ModItems.EMPTY_ESSENCE.get()));
                addItem(((Player) entity), ModItems.OVERWORLD_ESSENCE.get().getDefaultInstance());
            }

        }
    }
    private boolean isInVoid(Player player) {
        return player.getY() < -64;
    }


    private int getItem(Player player, Item item) {
        for (int i = 0; i < player.inventoryMenu.getSize(); i++) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (!itemStack.isEmpty() && itemStack.getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    public static void removeItem(Player player, int slot) {
        if (!(slot == -1))  {
            player.getInventory().removeItem(slot, 1);
        }
    }
    public static void addItem(Player player, ItemStack stack) {
        player.getInventory().add(stack);
    }
}

