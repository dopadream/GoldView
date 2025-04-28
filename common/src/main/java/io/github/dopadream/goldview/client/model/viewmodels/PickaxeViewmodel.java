package io.github.dopadream.goldview.client.model.viewmodels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.dopadream.goldview.client.ViewmodelRenderState;
import io.github.dopadream.goldview.client.animation.PickaxeViewmodelAnimation;
import io.github.dopadream.goldview.client.model.ViewModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.texture.OverlayTexture;

@Environment(EnvType.CLIENT)
public class PickaxeViewmodel extends ViewModel<ViewmodelRenderState> {


	private final ModelPart root;
	private final ModelPart Pickaxe;

	// The two arm models
	private final ModelPart right_arm_normal;
	private final ModelPart right_arm_slim;
	private final ModelPart left_arm_normal;
	private final ModelPart left_arm_slim;




	public PickaxeViewmodel(ModelPart armsNormal, ModelPart armsSlim, ModelPart pickaxe) {
		super(armsNormal.getChild("root"));
		this.root = armsNormal.getChild("root");

		ModelPart rBoneNormal = root.getChild("r_bone");
		ModelPart lBoneNormal = root.getChild("l_bone");
		ModelPart rBoneSlim = armsSlim.getChild("root").getChild("r_bone");
		ModelPart lBoneSlim = armsSlim.getChild("root").getChild("l_bone");

		// Assign arm and sleeve parts
		this.right_arm_normal = rBoneNormal.getChild("right_arm");
		this.right_arm_slim = rBoneSlim.getChild("right_arm");
		this.left_arm_normal = lBoneNormal.getChild("left_arm");
		this.left_arm_slim = lBoneSlim.getChild("left_arm");

		// Assign item part
		this.Pickaxe = pickaxe.getChild("root").getChild("r_bone").getChild("Pickaxe");
	}


	public void renderArmsOnly(PoseStack poseStack, VertexConsumer vertexConsumer, int light, boolean slim) {
		// Select the correct arm model based on slim skin or not
		ModelPart right_arm = slim ? this.right_arm_slim : this.right_arm_normal;
		ModelPart left_arm = slim ? this.left_arm_slim : this.left_arm_normal;


		// Render arms and sleeves
		right_arm.render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
		left_arm.render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
	}


	public void renderPickaxeOnly(PoseStack poseStack, VertexConsumer vertexConsumer, int light) {
		if (this.Pickaxe != null) {
			this.Pickaxe.render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
		}
	}

	public static LayerDefinition createLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition r_bone = root.addOrReplaceChild("r_bone", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));
		PartDefinition right_arm = r_bone.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition right_sleeve = right_arm.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition l_bone = root.addOrReplaceChild("l_bone", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));
		PartDefinition left_arm = l_bone.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition left_sleeve = left_arm.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createSlimLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition r_bone = root.addOrReplaceChild("r_bone", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));
		PartDefinition right_arm = r_bone.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition right_sleeve = right_arm.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition l_bone = root.addOrReplaceChild("l_bone", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));
		PartDefinition left_arm = l_bone.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition left_sleeve = left_arm.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createPickaxeLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition r_bone = root.addOrReplaceChild("r_bone", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition Pickaxe = r_bone.addOrReplaceChild("Pickaxe", CubeListBuilder.create().texOffs(4, 0).addBox(-6.5F, -12.0F, -0.005F, 13.0F, 3.0F, 0.01F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-0.5F, -13.0F, -0.5F, 1.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9F, 10.5F, 0.0F, 1.5708F, -0.0873F, 4.7123927F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(ViewmodelRenderState viewmodelRenderState) {
		super.setupAnim(viewmodelRenderState);
		if (!viewmodelRenderState.idleAnimationState.isStarted()) {
			viewmodelRenderState.idleAnimationState.start(0);
		}
		this.animate(viewmodelRenderState.idleAnimationState, PickaxeViewmodelAnimation.PICKAXE_IDLE, Minecraft.getInstance().player.tickCount);
	}
}