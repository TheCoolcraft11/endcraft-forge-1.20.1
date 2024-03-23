package net.thecoolcraft11.endcraft.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class VoidGhostEntity extends Monster {
    public VoidGhostEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }else {
            --this.idleAnimationTimeout;
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 3.0));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 3.0, false));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 3.25D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 32f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FLYING_SPEED, 0.2)
                .add(Attributes.ARMOR, 2)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }
}
