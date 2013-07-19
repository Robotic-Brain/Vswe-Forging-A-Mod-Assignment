package de.roboticbrain.vswe.forgingamod.assignment.client.sounds;

import de.roboticbrain.vswe.forgingamod.assignment.ModInfo;
import net.minecraft.client.Minecraft;

public enum Sounds {
	
	ROCKET_LAUNCH ("fire");
	
	private String name;
	
	Sounds(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void play(double x, double y, double z, float volume, float pitch) {
		Minecraft.getMinecraft().sndManager.playSound(ModInfo.MAIN_ASSET_LOCATION + ":" + name, (float)x, (float)y, (float)z, volume, pitch);
	}
}
