package net.thecoolcraft11.endcraft.entity.client;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.thecoolcraft11.endcraft.entity.animations.ModAnimationDefinitions;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;

public class VoidGhostModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart voidghost;
	private final ModelPart head;

	public VoidGhostModel(ModelPart root) {
		this.voidghost = root.getChild("voidghost");
		this.head = voidghost.getChild("head");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition voidghost = partdefinition.addOrReplaceChild("voidghost", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_wing = voidghost.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offset(-0.5F, -5.0F, 1.0F));

		PartDefinition left_wing = voidghost.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offset(0.5F, -5.0F, 1.0F));

		PartDefinition left_arm = voidghost.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.5F, 0.0F));

		PartDefinition right_arm = voidghost.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-1.5F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -5.5F, 0.0F));

		PartDefinition body = voidghost.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-1.5F, 1.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition head = voidghost.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animate(((VoidGhostEntity) entity).idleAnimationState, ModAnimationDefinitions.IDLE, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		voidghost.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return voidghost;
	}
}