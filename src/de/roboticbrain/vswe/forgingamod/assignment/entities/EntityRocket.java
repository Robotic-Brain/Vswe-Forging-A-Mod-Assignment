package de.roboticbrain.vswe.forgingamod.assignment.entities;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityRocket extends Entity implements IEntityAdditionalSpawnData {
	
	/**
	 * NBT tag(s) for starting point
	 * Used to determine height of rocket
	 */
	private static final String START_X_NBT = "startX";
	private static final String START_Y_NBT = "startY";
	private static final String START_Z_NBT = "startZ";
	
	/**
	 * NBT tag to store rocket type
	 */
	private static final String FIREWORK_TYPE_NBT = "fireworkType";
	
	/**
	 * Datawatcher ID to store rocket type
	 */
	private static final int TYPE_ID = 15;
	
	/**
	 * After what distance should the rocket "explode"
	 */
	public static final double LAUNCH_HEIGHT_SQUARED = 10 * 10;
	
	/**
	 * Used to determine travel distance
	 */
	public double startX;
	public double startY;
	public double startZ;
	
	public EntityRocket(World world) {
		super(world);
		motionY = 0.6;
	}
	
	/*public EntityRocket(World world, int x, int y, int z, int type) {
		super(world);
		
		fireworkType = type;
		
		posX = startX = x;
		posY = startY = y;
		posZ = startZ = z;
		
		motionY = 2;
	}*/
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!worldObj.isRemote) {
			double dx = posX - startX;
			double dy = posY - startY;
			double dz = posZ - startZ;
			
			double distance2 = dx * dx + dy * dy + dz * dz;
			
			if (distance2 > LAUNCH_HEIGHT_SQUARED) {
				setDead();
			}
		}
		
		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(TYPE_ID, (byte)0);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		startX = compound.getDouble(START_X_NBT);
		startY = compound.getDouble(START_Y_NBT);
		startZ = compound.getDouble(START_Z_NBT);
		
		setFWType(compound.getByte(FIREWORK_TYPE_NBT));
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setDouble(START_X_NBT, startX);
		compound.setDouble(START_Y_NBT, startY);
		compound.setDouble(START_Z_NBT, startZ);
		
		compound.setByte(FIREWORK_TYPE_NBT, getFWType());
	}
	
	/**
	 * Set Firework Type (1-3)
	 * @param type
	 */
	public void setFWType(int type) {
		dataWatcher.updateObject(TYPE_ID, (byte)type);
	}
	
	/**
	 * Get Firework Type
	 * @return (1-3)
	 */
	public byte getFWType() {
		return (byte)dataWatcher.getWatchableObjectByte(TYPE_ID);
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeDouble(startX);
		data.writeDouble(startY);
		data.writeDouble(startZ);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		startX = data.readDouble();
		startY = data.readDouble();
		startZ = data.readDouble();
	}
}
