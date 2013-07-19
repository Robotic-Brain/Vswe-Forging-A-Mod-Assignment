package de.roboticbrain.vswe.forgingamod.assignment.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import de.roboticbrain.vswe.forgingamod.assignment.ModEntryPoint;

public class Entities {

	public static void init() {
		EntityRegistry.registerModEntity(EntityRocket.class, "EntityRocket", 0, ModEntryPoint.instance, 80, 3, true);
	}
	
}
