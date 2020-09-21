package mod.vemerion.mostshearables.item;

import java.util.List;

import mod.vemerion.mostshearables.Main;
import mod.vemerion.mostshearables.helper.Helper;
import mod.vemerion.mostshearables.model.BearHideArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BearHideArmorItem extends ArmorItem {
	@OnlyIn(Dist.CLIENT)
	BearHideArmorModel<?> model;

	public BearHideArmorItem(EquipmentSlotType slot) {
		super(Main.BEAR_HIDE_MATERIAL, slot, new Item.Properties().maxStackSize(1).group(ItemGroup.COMBAT));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return Main.MODID + ":textures/armor/bear_hide_armor.png";
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {
		if (model == null) {
			model = new BearHideArmorModel<>(0.3f);
		}

		if (armorSlot == EquipmentSlotType.FEET) {
			model.leftPaw.showModel = true;
			model.rightPaw.showModel = true;
		} else {
			model.leftPaw.showModel = false;
			model.rightPaw.showModel = false;
		}

		if (armorSlot == EquipmentSlotType.LEGS) {
			model.leftLeg.showModel = true;
			model.rightLeg.showModel = true;
		} else {
			model.leftLeg.showModel = false;
			model.rightLeg.showModel = false;
		}

		model.isSitting = _default.isSitting;
		model.isSneak = _default.isSneak;
		model.isChild = _default.isChild;
		model.rightArmPose = _default.rightArmPose;
		model.leftArmPose = _default.leftArmPose;

		return (A) model;
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote && Helper.hasFullArmorSetEquipped(player, BearHideArmorItem.class)) {
			List<PolarBearEntity> bears = world.getEntitiesWithinAABB(PolarBearEntity.class,
					player.getBoundingBox().grow(5, 3, 5));

			for (PolarBearEntity bear : bears) {
				bear.setAttackTarget(player);
				bear.setRevengeTarget(player);
			}
		}
	}
}
