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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.EnderForgeConverterMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnderForgeConverterBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(2);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1000;
    public EnderForgeConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ENDER_FORGE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> EnderForgeConverterBlockEntity.this.progress;
                    case 1 -> EnderForgeConverterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> EnderForgeConverterBlockEntity.this.progress = pValue;
                    case 1 -> EnderForgeConverterBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
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
        return Component.translatable("block.endcraft.ender_forge_converter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new EnderForgeConverterMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());
        pTag.putInt("ender_forge_converter.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("ender_forge_converter.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        }else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        int damage = this.inventory.getStackInSlot(OUTPUT_SLOT).getDamageValue();
        this.inventory.extractItem(INPUT_SLOT, 1, false);
        this.inventory.setStackInSlot(OUTPUT_SLOT, getTool());
        this.inventory.getStackInSlot(OUTPUT_SLOT).setDamageValue(damage);
    }

    private ItemStack getTool() {
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_SWORD) {
            return ModItems.ENDERITE_SWORD.get().getDefaultInstance();
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_PICKAXE) {
            return ModItems.ENDERITE_PICKAXE.get().getDefaultInstance();
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_AXE) {
            return ModItems.ENDERITE_AXE.get().getDefaultInstance();
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_SHOVEL) {
            return ModItems.ENDERITE_SHOVEL.get().getDefaultInstance();
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_HOE) {
            return ModItems.ENDERITE_HOE.get().getDefaultInstance();
        }
        return null;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseProgress() {
     progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.inventory.getStackInSlot(INPUT_SLOT).getItem() == ModItems.ENDERITE_INGOT.get();

        return hasCraftingItem && hasToolInOutputSlot();
    }

    private boolean hasToolInOutputSlot() {
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_SWORD) {
            return true;
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_PICKAXE) {
            return true;
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_AXE) {
            return true;
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_SHOVEL) {
            return true;
        }
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == Items.NETHERITE_HOE) {
            return true;
        }
        return false;
    }
}
