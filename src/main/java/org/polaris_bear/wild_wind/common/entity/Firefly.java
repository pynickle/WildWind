package org.polaris_bear.wild_wind.common.entity;

import com.google.common.base.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.entity.goal.FireflyBaseGoal;
import org.polaris_bear.wild_wind.common.entity.goal.FireflyFlyGoal;
import org.polaris_bear.wild_wind.common.entity.goal.FireflyRoostGoal;
import org.polaris_bear.wild_wind.common.entity.goal.FireflyGlowGoal;
import org.polaris_bear.wild_wind.common.init.ModEntities;
import org.polaris_bear.wild_wind.common.init.tags.ModItemTags;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class Firefly extends Animal implements FlyingAnimal, GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDimensions BABY_DIMENSIONS = ModEntities.FIREFLY.get().getDimensions().scale(0.5f).withEyeHeight(0.2975F);

    private static final EntityDataAccessor<Boolean> ROOST = SynchedEntityData.defineId(Firefly.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TICKER = SynchedEntityData.defineId(Firefly.class, EntityDataSerializers.INT);

    public static final RawAnimation IDLE_RAW = RawAnimation.begin().thenLoop("idle");
    public static final RawAnimation BABY_RAW = RawAnimation.begin().thenLoop("baby");
    public Firefly(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = getRandom().nextInt(3) + 1;

    }

    @Override
    protected @NotNull EntityDimensions getDefaultDimensions(@NotNull Pose pose) {
        return isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.FIREFLY.get().create(serverLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ROOST, false);
        builder.define(TICKER, 0);


    }



    public void setRoost(boolean r) {
        this.entityData.set(ROOST, r);
    }

    public void setTicker(int ticker) { this.entityData.set(TICKER, ticker); }

    public int getTicker() { return this.entityData.get(TICKER); }

    public boolean isTicker() {
        if (getTicker() < 600) {
            addTicker();
            return true;
        }
        return false;
    }

    public void addTicker() { this.entityData.set(TICKER, this.entityData.get(TICKER)+1); }
    public void clearTicker() { this.entityData.set(TICKER, 0); }

    public boolean isRoost() {
        return this.entityData.get(ROOST);
    }


    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setRoost(compound.getBoolean("roost"));
        this.setTicker(compound.getInt("ticker"));
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModItemTags.FIREFLY_FOOD.get());
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("roost", isRoost());
        compound.putInt("ticker", getTicker());
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level) {
            @Override
            public boolean isStableDestination(BlockPos pos) {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        flyingPathNavigation.setCanPassDoors(false);
        return flyingPathNavigation;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {

    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {

    }
    public static final List<Function<Firefly, Goal>> FACTORY = List
            .of(
                    FireflyBaseGoal::new,
                    FireflyFlyGoal::new,
                    FireflyRoostGoal::new,
                    FireflyGlowGoal::new
            );
    @Override
    protected void registerGoals() {
        for (int i = 0; i < FACTORY.size(); i++) {
            this.goalSelector.addGoal(i, FACTORY.get(i).apply(this));
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return super.mobInteract(player, hand);
    }

    public static AttributeSupplier createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8f)
                .add(Attributes.FLYING_SPEED, 0.6f)
                .add(Attributes.GRAVITY, 0.0f)
                .build();
    }



    @Override
    public boolean isFlying() {
        return true;
    }

    /**
     * Register your {@link AnimationController AnimationControllers} and their respective animations and conditions
     * <p>
     * Override this method in your animatable object and add your controllers via {@link AnimatableManager.ControllerRegistrar#add ControllerRegistrar.add}
     * <p>
     * You may add as many controllers as wanted
     * <p>
     * Each controller can only play <u>one</u> animation at a time, and so animations that you intend to play concurrently should be handled in independent controllers
     * <p>
     * Note having multiple animations playing via multiple controllers can override parts of one animation with another if both animations use the same bones or child bones
     *
     * @param controllers The object to register your controller instances to
     */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(
                this,
                this::handle
        ));
    }

    private PlayState handle(AnimationState<Firefly> state) {
        state.setControllerSpeed(isRoost() ? 0.3f: 1f);
        state.setAndContinue( isBaby() ? BABY_RAW : IDLE_RAW);
        return PlayState.CONTINUE;
    }

    /**
     * Each instance of a {@code GeoAnimatable} must return an instance of an {@link AnimatableInstanceCache}, which handles instance-specific animation info
     * <p>
     * Generally speaking, you should create your cache using {@code GeckoLibUtil#createCache} and store it in your animatable instance, returning that cached instance when called
     *
     * @return A cached instance of an {@code AnimatableInstanceCache}
     */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
