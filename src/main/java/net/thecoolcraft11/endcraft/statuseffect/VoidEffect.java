package net.thecoolcraft11.endcraft.statuseffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.custom.VoidBlock;
import net.thecoolcraft11.endcraft.block.custom.VoidLayerBlock;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;

import java.util.stream.Collector;

public class VoidEffect extends MobEffect {
    protected VoidEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(ModDamageTypes.of(pLivingEntity.level(), ModDamageTypes.ESSENCE_AREA_REMOVED), 2 * pAmplifier);
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (!pLivingEntity.isSpectator()) {
            //pLivingEntity.move(MoverType.PLAYER, new Vec3(0,-1,0));

            if(pLivingEntity.level().getBlockState(pLivingEntity.getOnPos().above()).getBlock() == ModBlocks.VOID_LAYER.get()) {
                int layer = pLivingEntity.level().getBlockState(pLivingEntity.getOnPos().above()).getValue(VoidLayerBlock.LAYERS);
                if(layer < 8) {
                    pLivingEntity.level().setBlock(pLivingEntity.getOnPos().above(), ModBlocks.VOID_LAYER.get().defaultBlockState().setValue(VoidLayerBlock.LAYERS, (layer +1)), 3);
                }
            }else if(pLivingEntity.level().getBlockState(pLivingEntity.getOnPos()).getBlock() != Blocks.AIR){
                if (pLivingEntity.level().getBlockState(pLivingEntity.getOnPos().above()).getBlock() != ModBlocks.VOID_BLOCK.get()) {
                    pLivingEntity.level().setBlock(pLivingEntity.getOnPos().above(), ModBlocks.VOID_LAYER.get().defaultBlockState(), 3);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
