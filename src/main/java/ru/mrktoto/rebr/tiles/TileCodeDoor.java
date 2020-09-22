package ru.mrktoto.rebr.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileCodeDoor extends TileEntity {
	public int code = -1;

	public boolean isSet;
	
	private int count;
	
	public int getCount() {
		
		return this.count;
	}
	
	public void incrementCount() {
		
		this.count++;
		
		this.markDirty();
	}
	
	public void decrementCount() {
		
		this.count--;
		
		this.markDirty();
	}
	
	@Override
    public boolean canUpdate() {
    	
        return false;
    }

	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		nbttagcompound.removeTag("code");
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		NBTTagCompound nbt = pkt.func_148857_g();
		readFromNBT(nbt);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		this.count = nbt.getInteger("count");
		super.readFromNBT(nbt);
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
			code = nbt.getInteger("code");
		isSet = nbt.getBoolean("isSet");
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && code > 0)
			isSet = true;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("count", this.count);
		super.writeToNBT(nbt);
		if ((FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER))
			nbt.setInteger("code", code);
		nbt.setBoolean("isSet", isSet);
	}

}
