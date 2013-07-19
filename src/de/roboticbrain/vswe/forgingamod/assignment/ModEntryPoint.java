package de.roboticbrain.vswe.forgingamod.assignment;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import de.roboticbrain.vswe.forgingamod.assignment.blocks.Blocks;
import de.roboticbrain.vswe.forgingamod.assignment.config.ConfigHandler;
import de.roboticbrain.vswe.forgingamod.assignment.entities.Entities;
import de.roboticbrain.vswe.forgingamod.assignment.items.Items;
import de.roboticbrain.vswe.forgingamod.assignment.network.PacketHandler;
import de.roboticbrain.vswe.forgingamod.assignment.proxies.CommonProxy;

@Mod(	modid = ModInfo.ID,
		 name = ModInfo.NAME,
	  version = ModInfo.VERSION)
@NetworkMod(	serverSideRequired = false,
				clientSideRequired = true,
						  channels = {ModInfo.CHANNEL},
		   		     packetHandler = PacketHandler.class)
public class ModEntryPoint {
	
	@Instance
	public static ModEntryPoint instance;
	
	@SidedProxy(clientSide = "de.roboticbrain.vswe.forgingamod.assignment.proxies.ClientProxy",
				serverSide = "de.roboticbrain.vswe.forgingamod.assignment.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.loadConfigs(event.getSuggestedConfigurationFile());
		
		Blocks.init();
		Items.init();
		
		proxy.initRenderers();
		proxy.initSounds();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Blocks.addNames();
		Items.addNames();
		
		//Items.registerRecipes();
		Blocks.registerTileEntities();
		
		Entities.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
