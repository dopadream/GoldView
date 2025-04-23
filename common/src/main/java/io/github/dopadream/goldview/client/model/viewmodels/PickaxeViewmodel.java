package io.github.dopadream.goldview.client.model.viewmodels;

import io.github.dopadream.goldview.client.ViewmodelRenderState;
import io.github.dopadream.goldview.client.animation.PickaxeViewmodelAnimation;
import io.github.dopadream.goldview.client.model.ViewModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;

@Environment(EnvType.CLIENT)
public class PickaxeViewmodel extends ViewModel<ViewmodelRenderState> {

	private final ModelPart root;
	private final ModelPart r_bone;
	private final ModelPart right_arm;
	private final ModelPart right_sleeve;
	private final ModelPart Pickaxe;
	private final ModelPart l_bone;
	private final ModelPart left_arm;
	private final ModelPart left_sleeve;


	public PickaxeViewmodel(ModelPart modelPart) {
        super(modelPart.getChild("root"));
        this.root = modelPart.getChild("root");
		this.r_bone = this.root.getChild("r_bone");
		this.Pickaxe = this.r_bone.getChild("Pickaxe");
		this.l_bone = this.root.getChild("l_bone");

		Minecraft minecraft = Minecraft.getInstance();

		AbstractClientPlayer abstractClientPlayer = minecraft.player;
        PlayerRenderer playerRenderer = null;
        if (abstractClientPlayer != null) {
            playerRenderer = (PlayerRenderer) minecraft.getEntityRenderDispatcher().getRenderer(abstractClientPlayer);
			this.right_arm = this.r_bone.getChild("right_arm");
			this.right_sleeve = this.right_arm.getChild("right_sleeve");
			this.left_arm = this.l_bone.getChild("left_arm");
			this.left_sleeve = this.left_arm.getChild("left_sleeve");
		} else {
			this.right_arm = null;
			this.right_sleeve = null;
			this.left_arm = null;
			this.left_sleeve = null;
		}


	}

	public static LayerDefinition createLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition r_bone = root.addOrReplaceChild("r_bone", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition right_arm = r_bone.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_sleeve = right_arm.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Pickaxe = r_bone.addOrReplaceChild("Pickaxe", CubeListBuilder.create().texOffs(4, 0).addBox(-6.5F, -12.0F, -0.005F, 13.0F, 3.0F, 0.01F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -13.0F, -0.5F, 1.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 8.1F, 0.0F, 1.5708F, 0.0873F, 1.5708F));

		PartDefinition l_bone = root.addOrReplaceChild("l_bone", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition left_arm = l_bone.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_sleeve = left_arm.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	
	public void setupAnim(ViewmodelRenderState viewmodelRenderState) {
		super.setupAnim(viewmodelRenderState);
		this.animate(viewmodelRenderState.idleAnimationState, PickaxeViewmodelAnimation.PICKAXE_IDLE, viewmodelRenderState.ageInTicks);
	}
}