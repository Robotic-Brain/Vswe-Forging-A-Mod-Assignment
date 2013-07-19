package de.roboticbrain.vswe.forgingamod.assignment.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import de.roboticbrain.vswe.forgingamod.assignment.client.RenderRocket;
import de.roboticbrain.vswe.forgingamod.assignment.client.sounds.SoundHandler;
import de.roboticbrain.vswe.forgingamod.assignment.entities.EntityRocket;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	public void initSounds() {
		// Creating an Object and assigning it to nothing seems strange to me so i moved the registering call out here
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}

	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
	}
	
}
