package de.roboticbrain.vswe.forgingamod.assignment.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ParticleHelper {
	
	/**
	 * Returns true or false depending on render settings. True = spawn particle
	 */
	public static boolean shouldSpawnParticle(World world, double x, double y, double z) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
			int particleSetting = mc.gameSettings.particleSetting;
			
			if (particleSetting == 2 || (particleSetting == 1 && world.rand.nextInt(3) == 0)) {
				return false;
			}
			
			double distanceX = mc.renderViewEntity.posX - x;
			double distanceY = mc.renderViewEntity.posY - y;
			double distanceZ = mc.renderViewEntity.posZ - z;
			
			double maxDistance = 16;
			if (getDistance2(distanceX, distanceY, distanceZ) > maxDistance * maxDistance) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Helper to calculate lenght of vector squared
	 * @param x of Vector
	 * @param y of Vector
	 * @param z of Vector
	 * @return |vec| * |vec|
	 */
	private static double getDistance2(double x, double y, double z) {
		return x*x + y*y + z*z;
	}
	
}
