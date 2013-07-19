package de.roboticbrain.vswe.forgingamod.assignment.config;

import java.io.File;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import de.roboticbrain.vswe.forgingamod.assignment.blocks.BlockInfo;
import de.roboticbrain.vswe.forgingamod.assignment.items.ItemInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void loadConfigs(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockInfo.FIREWORK_LAUNCHER_BLOCK_ID = config.getBlock(BlockInfo.FIREWORK_LAUNCHER_BLOCK_KEY, BlockInfo.FIREWORK_LAUNCHER_BLOCK_DEFAULT).getInt();
		
		ItemInfo.TYPE_CARD_ID = config.getItem(ItemInfo.TYPE_CARD_KEY, ItemInfo.TYPE_CARD_DEFAULT).getInt() - 256;
		
		if (config.hasChanged()) {
			config.save();
		}
	}
	
}
