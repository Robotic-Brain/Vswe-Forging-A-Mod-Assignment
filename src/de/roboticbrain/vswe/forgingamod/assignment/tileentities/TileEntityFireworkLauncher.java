package de.roboticbrain.vswe.forgingamod.assignment.tileentities;

import java.util.Random;

import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import de.roboticbrain.vswe.forgingamod.assignment.blocks.BlockInfo;
import de.roboticbrain.vswe.forgingamod.assignment.client.particles.ParticleHelper;
import de.roboticbrain.vswe.forgingamod.assignment.client.sounds.Sounds;
import de.roboticbrain.vswe.forgingamod.assignment.entities.EntityRocket;

public class TileEntityFireworkLauncher extends TileEntity {
	
	/**
	 * Ticks per Second - Is this stored somewhere else?
	 */
	private static final int TICKS_PER_SEC = 20;
	
	/**
	 * Intervals for Rocket Firing and Particle Spawning
	 */
	private static final int FIRE_INTERVAL = 12 * TICKS_PER_SEC;
	private static final int PARTICLE_SPAWN_INTERVAL = 3 * TICKS_PER_SEC;
	
	/**
	 * NBT tag(s) to store timer values
	 */
	private static final String FIRE_TIMER_NBT = "FireTimer";
	private static final String PARTICLE_TIMER_NBT = "ParticleTimer";
	
	/**
	 * Client Event ID for particle spawning
	 */
	private static final int PARTICLE_EVENT_ID = 1;
	
	/**
	 * rocket timer
	 */
	private int fireTimer;
	/**
	 * particle timer
	 */
	private int spawnTimer;
	
	public TileEntityFireworkLauncher() {
		fireTimer = FIRE_INTERVAL;
		spawnTimer = PARTICLE_SPAWN_INTERVAL;
	}
	
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			updateParticle();
			updateLauncher();
		}
	}
	
	/**
	 * updates rocket timer and shoots a rocket when ready
	 */
	private void updateLauncher() {
		if (fireTimer > 0) {
			fireTimer--;
		} else if (fireTimer == 0) {
			doLaunch();
			fireTimer = FIRE_INTERVAL;
		} else {
			// If timer is negative for whatever reason
			fireTimer = FIRE_INTERVAL;
		}
	}

	
	/**
	 * actually spawns the rocket and plays sound effect
	 */
	private void doLaunch() {
		int type = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		EntityRocket rocket = new EntityRocket(worldObj);
		
		rocket.posX = rocket.startX = xCoord + 0.5;
		rocket.posY = rocket.startY = yCoord;
		rocket.posZ = rocket.startZ = zCoord + 0.5;
		
		rocket.setFWType(type);
		
		worldObj.spawnEntityInWorld(rocket);
		
		Sounds.ROCKET_LAUNCH.play(xCoord, yCoord, zCoord, 1, 0);
	}

	
	/**
	 * updates the particle timer and creates a client event when ready
	 */
	private void updateParticle() {
		if (spawnTimer > 0) {
			spawnTimer--;
		} else if (spawnTimer == 0) {
			spawnParticle();
			spawnTimer = PARTICLE_SPAWN_INTERVAL;
		} else {
			// If timer is negative for whatever reason
			spawnTimer = PARTICLE_SPAWN_INTERVAL;
		}
	}

	/**
	 * just sends the client event to spawn a particle
	 */
	private void spawnParticle() {
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.FIREWORK_LAUNCHER_BLOCK_ID, PARTICLE_EVENT_ID, 0);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setShort(FIRE_TIMER_NBT, (short)fireTimer);
		compound.setShort(PARTICLE_TIMER_NBT, (short)spawnTimer);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		fireTimer = compound.getShort(FIRE_TIMER_NBT);
		spawnTimer = compound.getShort(PARTICLE_TIMER_NBT);
	}
	
	/**
	 * Spawns the particle on the client
	 */
	@Override
	public boolean receiveClientEvent(int id, int value) {
		if (worldObj.isRemote && id == PARTICLE_EVENT_ID) {
			Random rand = new Random();
			
			double px = xCoord + rand.nextDouble();
			double py = yCoord + rand.nextDouble();
			double pz = zCoord + rand.nextDouble();
			
			double mx = (rand.nextDouble() - 0.5) * 0.5;
			double my = (rand.nextDouble() - 0.5) * 0.5;
			double mz = (rand.nextDouble() - 0.5) * 0.5;
			
			if (ParticleHelper.shouldSpawnParticle(worldObj, px, py, pz)) {
				worldObj.spawnParticle("smoke", px, py, pz, mx, my, mz);
			}
		}
		
		return true;
	}
	
}
