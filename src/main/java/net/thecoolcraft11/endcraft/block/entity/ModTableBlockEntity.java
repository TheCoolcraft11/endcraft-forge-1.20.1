package net.thecoolcraft11.endcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.enchantment.ModEnchantments;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.ModTableMenu;
import net.thecoolcraft11.endcraft.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(4);
    private static final int INPUT_SLOT_I = 0;
    private static final int INPUT_SLOT_E = 1;
    private static final int INPUT_SLOT_U = 2;
    private static final int OUTPUT_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    public ModTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MOD_TABLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> inventory);
    }

    public void drops() {
        SimpleContainer simpleContainer = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            simpleContainer.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, simpleContainer);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.endcraft.mod_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ModTableMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            setChanged(pLevel, pPos, pState);

                craftItem();
        }else {
        }
    }

    int nextE = 0;
    private void craftItem() {
        if (this.inventory.getStackInSlot(INPUT_SLOT_U).getOrCreateTag().getString("type").equals("range")) {
            if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()) < 15) {
                ItemStack stack = this.inventory.getStackInSlot(OUTPUT_SLOT).getItem().getDefaultInstance();
                stack.enchant(ModEnchantments.RANGE_ENCHANTMENT.get(), Math.min(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()) + this.inventory.getStackInSlot(INPUT_SLOT_U).getOrCreateTag().getInt("level"), 15));
                if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) <= 10) {
                    stack.enchant(ModEnchantments.DURABILITY_ENCHANTMENT.get() ,this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()));
                }
                if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()) > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()) <= 4) {
                    stack.enchant(ModEnchantments.FALL_ENCHANTMENT.get() ,this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()));
                }



                this.inventory.setStackInSlot(OUTPUT_SLOT, stack);
                this.inventory.getStackInSlot(INPUT_SLOT_I).setCount(this.inventory.getStackInSlot(INPUT_SLOT_I).getCount() - 4);
                this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().putInt("Energies", this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().getInt("Energies") - 300);
                this.inventory.getStackInSlot(INPUT_SLOT_U).setCount(0);
            }
        }
        if (this.inventory.getStackInSlot(INPUT_SLOT_U).getOrCreateTag().getString("type").equals("fall")) {
            if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()) < 4) {
                ItemStack stack = this.inventory.getStackInSlot(OUTPUT_SLOT).getItem().getDefaultInstance();
                stack.enchant(ModEnchantments.FALL_ENCHANTMENT.get(), Math.min(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()) + this.inventory.getStackInSlot(INPUT_SLOT_U).getOrCreateTag().getInt("level"), 4));
                if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) <= 10) {
                    stack.enchant(ModEnchantments.DURABILITY_ENCHANTMENT.get() ,this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()));
                }
                if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()) > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()) <= 15) {
                    stack.enchant(ModEnchantments.RANGE_ENCHANTMENT.get() ,this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()));
                }



                this.inventory.setStackInSlot(OUTPUT_SLOT, stack);
                this.inventory.getStackInSlot(INPUT_SLOT_I).setCount(this.inventory.getStackInSlot(INPUT_SLOT_I).getCount() - 4);
                this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().putInt("Energies", this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().getInt("Energies") - 300);
                this.inventory.getStackInSlot(INPUT_SLOT_U).setCount(0);
            }
        }
                if (this.inventory.getStackInSlot(INPUT_SLOT_U).getOrCreateTag().getString("type").equals("durability")) {
            if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) < 10) {
                        ItemStack stack = this.inventory.getStackInSlot(OUTPUT_SLOT).getItem().getDefaultInstance();
                        stack.enchant(ModEnchantments.DURABILITY_ENCHANTMENT.get(), Math.min(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) + this.inventory.getStackInSlot(INPUT_SLOT_U).getOrCreateTag().getInt("level"), 10));
                        if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()) > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.DURABILITY_ENCHANTMENT.get()) <= 10) {
                            stack.enchant(ModEnchantments.RANGE_ENCHANTMENT.get() ,this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.RANGE_ENCHANTMENT.get()));
                        }
                        if(this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()) > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()) <= 4) {
                            stack.enchant(ModEnchantments.FALL_ENCHANTMENT.get() ,this.inventory.getStackInSlot(OUTPUT_SLOT).getEnchantmentLevel(ModEnchantments.FALL_ENCHANTMENT.get()));
                        }



                        this.inventory.setStackInSlot(OUTPUT_SLOT, stack);
                        this.inventory.getStackInSlot(INPUT_SLOT_I).setCount(this.inventory.getStackInSlot(INPUT_SLOT_I).getCount() - 4);
                        this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().putInt("Energies", this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().getInt("Energies") - 300);
                        this.inventory.getStackInSlot(INPUT_SLOT_U).setCount(0);
                    }
        }


    }

    private boolean hasRecipe() {
        return this.inventory.getStackInSlot(INPUT_SLOT_I).getItem() == ModItems.ENDERITE_INGOT.get() && this.inventory.getStackInSlot(INPUT_SLOT_I).getCount() >= 4 && this.inventory.getStackInSlot(INPUT_SLOT_U).is(ModTags.Items.IS_STAFF_UPGRADE) && this.inventory.getStackInSlot(INPUT_SLOT_E).getOrCreateTag().getInt("Energies") > 100 && this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == ModItems.ENDER_STAFF.get();
    }

}