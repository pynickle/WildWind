package org.polaris2023.wild_wind.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.OnlyIn;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.common.block.modified.ModBannerBlock;
import org.polaris2023.wild_wind.common.block.modified.ModWallBannerBlock;
import org.polaris2023.wild_wind.common.entity.layer.ModModelLayers;
import net.neoforged.api.distmarker.Dist;

@OnlyIn(Dist.CLIENT)
public class ModBannerRenderer implements BlockEntityRenderer<ModBannerBlockEntity> {

    private final ModelPart flag;
    private final ModelPart pole;
    private final ModelPart bar;

    public ModBannerRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelPart = context.bakeLayer(ModModelLayers.BANNER);
        this.flag = modelPart.getChild("flag");
        this.pole = modelPart.getChild("pole");
        this.bar = modelPart.getChild("bar");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, -2.0F, 20.0F, 40.0F, 1.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(44, 0).addBox(-1.0F, -30.0F, -1.0F, 2.0F, 42.0F, 2.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("bar", CubeListBuilder.create().texOffs(0, 42).addBox(-10.0F, -32.0F, -1.0F, 20.0F, 2.0F, 2.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(ModBannerBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        boolean flag = blockEntity.getLevel() == null;
        poseStack.pushPose();
        long i;
        if (flag) {
            i = 0L;
            poseStack.translate(0.5F, 0.5F, 0.5F);
            this.pole.visible = true;
        } else {
            i = blockEntity.getLevel().getGameTime();
            BlockState blockstate = blockEntity.getBlockState();
            if (blockstate.getBlock() instanceof ModBannerBlock) {
                poseStack.translate(0.5F, 0.5F, 0.5F);
                float f1 = -RotationSegment.convertToDegrees(blockstate.getValue(ModBannerBlock.ROTATION));
                poseStack.mulPose(Axis.YP.rotationDegrees(f1));
                this.pole.visible = true;
            } else {
                poseStack.translate(0.5F, -0.16666667F, 0.5F);
                float f3 = -blockstate.getValue(ModWallBannerBlock.FACING).toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f3));
                poseStack.translate(0.0F, -0.3125F, -0.4375F);
                this.pole.visible = false;
            }
        }

        poseStack.pushPose();
        poseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        VertexConsumer vertexconsumer = ModelBakery.BANNER_BASE.buffer(bufferSource, RenderType::entitySolid);
        this.pole.render(poseStack, vertexconsumer, packedLight, packedOverlay);
        this.bar.render(poseStack, vertexconsumer, packedLight, packedOverlay);
        BlockPos blockpos = blockEntity.getBlockPos();
        float f2 = ((float)Math.floorMod(blockpos.getX() * 7L + blockpos.getY() * 9L + blockpos.getZ() * 13L + i, 100L) + partialTick) / 100.0F;
        this.flag.xRot = (-0.0125F + 0.01F * Mth.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
        this.flag.y = -32.0F;
        ModBannerRenderer.renderPatterns(poseStack, bufferSource, packedLight, packedOverlay, this.flag, ModelBakery.BANNER_BASE, true, FastColor.ARGB32.opaque(blockEntity.color), blockEntity.getPatterns());
        poseStack.popPose();
        poseStack.popPose();
    }

    public static void renderPatterns(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, ModelPart flagPart, Material flagMaterial, boolean banner, int baseColor, BannerPatternLayers patterns) {
        ModBannerRenderer.renderPatterns(poseStack, buffer, packedLight, packedOverlay, flagPart, flagMaterial, banner, baseColor, patterns, false);
    }

    public static void renderPatterns(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, ModelPart flagPart, Material flagMaterial, boolean banner, int baseColor, BannerPatternLayers patterns, boolean glint) {
        flagPart.render(poseStack, flagMaterial.buffer(buffer, RenderType::entitySolid, glint), packedLight, packedOverlay);
        renderPatternLayer(poseStack, buffer, packedLight, packedOverlay, flagPart, banner ? Sheets.BANNER_BASE : Sheets.SHIELD_BASE, baseColor);
        for (int i = 0; i < 16 && i < patterns.layers().size(); ++i) {
            BannerPatternLayers.Layer bannerpatternlayers$layer = patterns.layers().get(i);
            Material material = banner ? Sheets.getBannerMaterial(bannerpatternlayers$layer.pattern()) : Sheets.getShieldMaterial(bannerpatternlayers$layer.pattern());
            ModBannerRenderer.renderPatternLayer(poseStack, buffer, packedLight, packedOverlay, flagPart, material, bannerpatternlayers$layer.color().getTextureDiffuseColor());
        }

    }

    private static void renderPatternLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, ModelPart flagPart, Material material, int color) {
        flagPart.render(poseStack, material.buffer(buffer, RenderType::entityNoOutline), packedLight, packedOverlay, color);
    }

    @Override
    public AABB getRenderBoundingBox(ModBannerBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        boolean standing = blockEntity.getBlockState().getBlock() instanceof BannerBlock;
        return AABB.encapsulatingFullBlocks(pos, standing ? pos.above() : pos.below());
    }

}