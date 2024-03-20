package net.thecoolcraft11.endcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.EssenceAltarMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EssenceAltarBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(18);
    private static final int INPUT_SLOT = 16;
    private static final int OUTPUT_SLOT = 17;
    private static final int CRAFT_SLOT1 = 0;
    private static final int CRAFT_SLOT2 = 1;
    private static final int CRAFT_SLOT3 = 2;
    private static final int CRAFT_SLOT4 = 3;
    private static final int CRAFT_SLOT5 = 4;
    private static final int CRAFT_SLOT6 = 5;
    private static final int CRAFT_SLOT7 = 6;
    private static final int CRAFT_SLOT8 = 7;
    private static final int CRAFT_SLOT9 = 8;
    private static final int CRAFT_SLOT10 = 9;
    private static final int CRAFT_SLOT11 = 10;
    private static final int CRAFT_SLOT12 = 11;
    private static final int CRAFT_SLOT13 = 12;
    private static final int CRAFT_SLOT14 = 13;
    private static final int CRAFT_SLOT15 = 14;
    private static final int CRAFT_SLOT16 = 15;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    int progress = 0;
    int maxProgress = 100;

    public EssenceAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ESSENCE_ALTAR_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> EssenceAltarBlockEntity.this.progress;
                    case 1 -> EssenceAltarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> EssenceAltarBlockEntity.this.progress = pValue;
                    case 1 -> EssenceAltarBlockEntity.this.maxProgress = pValue;
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
        return Component.translatable("block.endcraft.essence_altar");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new EssenceAltarMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());
        pTag.putInt("essence_altar.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("essence_altar.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                setChanged(pLevel, pPos, pState);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private void craftItem() {
        int destroyamount = this.inventory.getStackInSlot(INPUT_SLOT).getCount() * 2;
        this.inventory.getStackInSlot(CRAFT_SLOT1).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT2).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT3).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT4).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT5).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT6).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT7).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT8).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT9).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT10).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT11).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT12).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT13).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT14).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT15).setCount(0);
        this.inventory.getStackInSlot(CRAFT_SLOT16).setCount(0);
        this.inventory.getStackInSlot(INPUT_SLOT).setCount(0);
        removeBlocksAroundPoint(this.level,this.worldPosition,destroyamount);
        //damagePlayer(this.getWorld(),findNearestPlayerInArea(this.getWorld(),this.getPos(),destroyamount), destroyamount);
        //Damage missing!


        this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putInt("MaxDurability", this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getInt("MaxDurability") + destroyamount);
        if(this.level.dimension() == Level.OVERWORLD) {
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putString("essence", "overworld");
        }
        if(this.level.dimension() == Level.NETHER) {
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putString("essence", "nether");
        }
        if(this.level.dimension() == Level.END) {
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putString("essence", "the_end");
        }
        this.inventory.getStackInSlot(OUTPUT_SLOT).setDamageValue(this.inventory.getStackInSlot(OUTPUT_SLOT).getMaxDamage() - this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getInt("MaxDurability"));
        if(this.inventory.getStackInSlot(OUTPUT_SLOT).getDamageValue() == 0) {
            if(this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getString("essence").equals("overworld")) {
                this.inventory.setStackInSlot( OUTPUT_SLOT, ModItems.OVERWORLD_ESSENCE.get().getDefaultInstance());
            }
            if(this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getString("essence").equals("nether")) {
                this.inventory.setStackInSlot(OUTPUT_SLOT, ModItems.NETHER_ESSENCE.get().getDefaultInstance());
            }
            if(this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getString("essence").equals("the_end")) {
                this.inventory.setStackInSlot(OUTPUT_SLOT,ModItems.END_ESSENCE.get().getDefaultInstance());
            }
        }
    }

    private static void removeBlocksAroundPoint(Level world, BlockPos centerPos, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int y = -100; y <= 320; y++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.offset(x, y-halfRadius, z);
                    if(!(world.getBlockState(blockPos).getBlock() == ModBlocks.ESSENCE_ALTAR.get() || world.getBlockState(blockPos).getBlock().getExplosionResistance() >= 3600000)) {
                        FluidState fluidState = world.getFluidState(blockPos);
                        if (fluidState.getFluidType() == Fluids.WATER.getFluidType() || fluidState.getFluidType() == Fluids.LAVA.getFluidType()) {
                            world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                        } else {
                            world.removeBlock(blockPos, true);}

                    }
                }
            }
        }
    }
    private boolean hasRecipe() {
        boolean istrue = false;
        if(this.hasItemInSlot(CRAFT_SLOT1,Items.TINTED_GLASS,64)) {
            if(this.hasItemInSlot(CRAFT_SLOT2,Items.TINTED_GLASS,64)) {
                if(this.hasItemInSlot(CRAFT_SLOT3,Items.TINTED_GLASS,64)) {
                    if(this.hasItemInSlot(CRAFT_SLOT4,Items.TINTED_GLASS,64)) {
                        if(this.hasItemInSlot(CRAFT_SLOT5,Items.TINTED_GLASS,64)) {
                            if(this.hasItemInSlot(CRAFT_SLOT6,ModItems.ESSENCE_CORE.get(),1)) {
                                if(this.hasItemInSlot(CRAFT_SLOT7,ModItems.ENDERITE_CORE.get(),1)) {
                                    if(this.hasItemInSlot(CRAFT_SLOT8,Items.TINTED_GLASS,64)) {
                                        if(this.hasItemInSlot(CRAFT_SLOT9,Items.TINTED_GLASS,64)) {
                                            if(this.hasItemInSlot(CRAFT_SLOT10,Items.OBSIDIAN,64)) {
                                                if(this.hasItemInSlot(CRAFT_SLOT11,Items.OBSIDIAN,64)) {
                                                    if(this.hasItemInSlot(CRAFT_SLOT12, Items.TINTED_GLASS,64)) {
                                                        if(this.hasItemInSlot(CRAFT_SLOT13,ModBlocks.ENDERITE_BLOCK.get().asItem(), 3)) {
                                                            if(this.hasItemInSlot(CRAFT_SLOT14,ModBlocks.ENDERITE_BLOCK.get().asItem(), 2)) {
                                                                if(this.hasItemInSlot(CRAFT_SLOT14,ModBlocks.ENDERITE_BLOCK.get().asItem(), 2)) {
                                                                    if(this.hasItemInSlot(CRAFT_SLOT16,ModBlocks.ENDERITE_BLOCK.get().asItem(), 3)) {
                                                                        if(this.inventory.getStackInSlot(INPUT_SLOT).getItem() == ModItems.ENDERITE_ENERGY.get()) {
                                                                            if(this.hasItemInSlot(OUTPUT_SLOT,ModItems.EMPTY_ESSENCE.get(),1)) {
                                                                                istrue = true;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return istrue;
    }

    private boolean hasItemInSlot(int slot, Item item, int count) {
        boolean istrue = false;
        if(this.inventory.getStackInSlot(slot).getItem() == item) {
            if(this.inventory.getStackInSlot(slot).getCount() == count) {
                istrue = true;
            }
        }
        return istrue;
    }

}