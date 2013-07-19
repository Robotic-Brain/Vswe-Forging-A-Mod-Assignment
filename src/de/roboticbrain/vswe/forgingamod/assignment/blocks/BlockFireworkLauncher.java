package de.roboticbrain.vswe.forgingamod.assignment.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.roboticbrain.vswe.forgingamod.assignment.ModInfo;
import de.roboticbrain.vswe.forgingamod.assignment.tileentities.TileEntityFireworkLauncher;

public class BlockFireworkLauncher extends BlockContainer {

	public BlockFireworkLauncher(int id) {
		super(id, Material.iron);
		setUnlocalizedName(BlockInfo.FIREWORK_LAUNCHER_BLOCK_UNLOCALIZED_NAME);
		
		setHardness(2F);
		setStepSound(soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon bottomIcon;
	@SideOnly(Side.CLIENT)
	private Icon[] sideIcons;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(ModInfo.MAIN_ASSET_LOCATION + ":" + BlockInfo.FIREWORK_LAUNCHER_BLOCK_TEXTURE_TOP);
		bottomIcon = register.registerIcon(ModInfo.MAIN_ASSET_LOCATION + ":" + BlockInfo.FIREWORK_LAUNCHER_BLOCK_TEXTURE_BOTTOM);
		
		sideIcons = new Icon[getNumberOfTypes()];
		for (int i = 0; i < getNumberOfTypes(); i++) {
			sideIcons[i] = register.registerIcon(ModInfo.MAIN_ASSET_LOCATION + ":" + BlockInfo.FIREWORK_LAUNCHER_BLOCK_TEXTURE_SIDES + (i+1));
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		switch (side) {
			case 0: return bottomIcon;
			case 1: return blockIcon;
			default: return sideIcons[meta];
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < getNumberOfTypes(); i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	
	/**
	 * This probably could have been better implemented with a constant
	 */
	private int getNumberOfTypes() {
		return 3;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFireworkLauncher();
	}
}
