package ru.mrktoto.rebr;

import java.util.Date;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "ExtendedPlayer";
	
	private final EntityPlayer player;

	private int currentTimer, maxTimer;
	Date date = new Date();
	
	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
		this.currentTimer = this.maxTimer = 50;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}
	
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("CurrentTimer", this.currentTimer);
		properties.setInteger("MaxTimer", this.maxTimer);
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.currentTimer = properties.getInteger("CurrentTimer");
		this.maxTimer = properties.getInteger("MaxTimer");
	}
	
	@Override
	public void init(Entity entity, World world)
	{
	}

	public boolean consumeTimer(int amount)
	{
		boolean sufficient = amount <= this.currentTimer;
		this.currentTimer -= (amount < this.currentTimer ? amount : this.currentTimer);
		return sufficient;
	}
	

	public int getTimer() {
		Date date = new Date();
		this.currentTimer = this.maxTimer-(int) date.getTime();
		return this.currentTimer;
	}
	
	public void runTimer(int delay) {
		Date date = new Date();
		this.maxTimer = (int) date.getTime() + delay;
	}
	
	public boolean checkTimer() {
		Date date = new Date();
		if (this.maxTimer != 0) {
			if (this.maxTimer-(int) date.getTime() < 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}