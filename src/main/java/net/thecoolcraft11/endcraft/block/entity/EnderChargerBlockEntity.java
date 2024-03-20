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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.EnderChargerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class EnderChargerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(2);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    int energytomove = 1;

    public EnderChargerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ENDER_CHARGER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> EnderChargerBlockEntity.this.energytomove;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> EnderChargerBlockEntity.this.energytomove = pValue;
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
        return Component.translatable("block.endcraft.ender_charger");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new EnderChargerMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());
        pTag.putInt("ender_charger.energiestomove", energytomove);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
        energytomove = pTag.getInt("ender_charger.energiestomove");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            setChanged(pLevel, pPos, pState);

                craftItem();
        }else {
            energytomove = 1;
        }
    }

    int nextE = 0;
    private void craftItem() {
        if (this.inventory.getStackInSlot(INPUT_SLOT).isEmpty() || this.inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.inventory.getStackInSlot(OUTPUT_SLOT).getDamageValue() == 0 || this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("Energies") == 0) {
            energytomove = 1;
        }

        this.inventory.getStackInSlot(OUTPUT_SLOT).setDamageValue(this.inventory.getStackInSlot(OUTPUT_SLOT).getDamageValue() - energytomove);
        this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().putInt("Energies", this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("Energies") - energytomove);
        if(energytomove <= this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("Energies")) {
            if (nextE >= 16) {
                energytomove++;
                nextE = 0;
            }else {
                nextE++;
            }
            Endcraft.LOGGER.info(String.valueOf(energytomove));
        }else {
            energytomove = this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("Energies");

        }
    }
    public String getEnergytomove4() {
        if (this.inventory.getStackInSlot(OUTPUT_SLOT).getMaxDamage() != 0) {
            DecimalFormat df = new DecimalFormat("#.##");
            float f = (float) (2048 - this.inventory.getStackInSlot(OUTPUT_SLOT).getDamageValue()) / this.inventory.getStackInSlot(OUTPUT_SLOT).getMaxDamage() * 100;
            return (df.format(f).replace(",", "."));
        }
        return "";
    }



    private boolean hasRecipe() {
        return (this.inventory.getStackInSlot(INPUT_SLOT).getItem() == ModItems.ENERGY_CELL.get()  && this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("Energies") > 0 && this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == ModItems.ENDER_STAFF.get() && this.inventory.getStackInSlot(OUTPUT_SLOT).getDamageValue() > 0);
    }

}