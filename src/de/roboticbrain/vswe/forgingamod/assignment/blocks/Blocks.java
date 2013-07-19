package de.roboticbrain.vswe.forgingamod.assignment.blocks;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import de.roboticbrain.vswe.forgingamod.assignment.items.ItemBlockFirework;
import de.roboticbrain.vswe.forgingamod.assignment.tileentities.TileEntityFireworkLauncher;

public class Blocks {
	
	public static Block fireworkLauncherBlock;
	
	public static void init() {
		fireworkLauncherBlock = new BlockFireworkLauncher(BlockInfo.FIREWORK_LAUNCHER_BLOCK_ID);
		GameRegistry.registerBlock(fireworkLauncherBlock, ItemBlockFirework.class, BlockInfo.FIREWORK_LAUNCHER_BLOCK_KEY);
	}

	public static void addNames() {
		LanguageRegistry.addName(fireworkLauncherBlock, BlockInfo.FIREWORK_LAUNCHER_BLOCK_NAME);
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFireworkLauncher.class, BlockInfo.FIREWORK_LAUNCHER_BLOCK_TE_KEY);
	}
	
}
