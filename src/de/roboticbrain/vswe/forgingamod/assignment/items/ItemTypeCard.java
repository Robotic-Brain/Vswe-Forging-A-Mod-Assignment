package de.roboticbrain.vswe.forgingamod.assignment.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.roboticbrain.vswe.forgingamod.assignment.ModInfo;

public class ItemTypeCard extends Item {

	public ItemTypeCard(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		setHasSubtypes(true);
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return ItemInfo.TYPE_CARD_UNLOCALIZED_NAME + itemStack.getItemDamage();
	}
	
	@SideOnly(Side.CLIENT)
	private Icon icons[];
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		
		icons = new Icon[3];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = register.registerIcon(ModInfo.MAIN_ASSET_LOCATION + ":" + ItemInfo.TYPE_CARD_ICONS + (i+1));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int dmg) {
		return icons[dmg];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++) {
			ItemStack stack = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			// Just set the block meta to stack damage
			int meta = world.getBlockMetadata(x, y, z);
			
			// Don't use item if type does not change
			if (meta == stack.getItemDamage()) {
				return false;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 3);
			
			stack.stackSize--;
			return true;
		} else {
			return false;
		}
	}
}
