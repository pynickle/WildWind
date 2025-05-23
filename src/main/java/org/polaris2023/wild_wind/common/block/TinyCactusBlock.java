package org.polaris2023.wild_wind.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.SubscribeEvent;


public class TinyCactusBlock extends FlowerBlock {


    public TinyCactusBlock(Properties properties) {
        super(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.NONE).pushReaction(PushReaction.DESTROY).noLootTable());
    }


    @Override
    public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
        return groundState.is(Blocks.SAND) || groundState.is(Blocks.RED_SAND);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, worldIn, blockpos);
    }

        public static void execute2(LevelAccessor world, Entity entity) {
            if (entity == null)
                return;
            entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.CACTUS)), 1);
        }
}