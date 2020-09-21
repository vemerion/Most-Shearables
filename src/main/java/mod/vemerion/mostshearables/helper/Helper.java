package mod.vemerion.mostshearables.helper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class Helper {
	public static boolean hasFullArmorSetEquipped(PlayerEntity player, Class<? extends ArmorItem> set) {
		for (ItemStack armor : player.getArmorInventoryList()) {
			if (!set.isInstance(armor.getItem()))
				return false;
		}
		return true;
	}
}
