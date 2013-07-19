package de.roboticbrain.vswe.forgingamod.assignment.items;

import net.minecraft.item.ItemBlock;

public class ItemBlockFirework extends ItemBlock {
	
	public ItemBlockFirework(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}
}
