package de.roboticbrain.vswe.forgingamod.assignment.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Items {
	
	public static Item typeCards;
	
	public static void init() {
		typeCards = new ItemTypeCard(ItemInfo.TYPE_CARD_ID);
	}

	public static void addNames() {
		for (int i = 0; i < 3; i++) {
			LanguageRegistry.addName(new ItemStack(typeCards, 1, i), ItemInfo.TYPE_CARD_NAMES[i]);
		}
	}
	
}
