package de.roboticbrain.vswe.forgingamod.assignment.client.sounds;

import de.roboticbrain.vswe.forgingamod.assignment.ModInfo;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler {
	
	// See ClientProxy.java for event bus register
	
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		for (Sounds sound : Sounds.values()) {
			addSound(event, sound);
		}
	}
	
	private void addSound(SoundLoadEvent event, Sounds sound) {
		event.manager.soundPoolSounds.addSound(ModInfo.MAIN_ASSET_LOCATION + ":" + sound.getName() + ".ogg");
	}
	
}
