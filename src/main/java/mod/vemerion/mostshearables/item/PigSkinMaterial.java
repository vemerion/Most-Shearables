package mod.vemerion.mostshearables.item;

import mod.vemerion.mostshearables.Main;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class PigSkinMaterial implements IArmorMaterial {

	private static final int[] MAX_DAMAGE = new int[] { 13, 15, 16, 11 };
	private static final int[] DAMAGE_REDUCTION = new int[] { 1, 3, 2, 1 };
	
	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE[slotIn.getIndex()] * 2;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return DAMAGE_REDUCTION[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return 3;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return SoundEvents.ENTITY_PIG_AMBIENT;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(Main.PIG_SKIN);
	}

	@Override
	public String getName() {
		return Main.MODID + ":pig_skin";
	}

	@Override
	public float getToughness() {
		return 0;
	}

}
