package ru.mrktoto.rebr.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.blocks.doorL1;
import ru.mrktoto.rebr.blocks.doorL2;
import ru.mrktoto.rebr.blocks.doorL3;
import ru.mrktoto.rebr.tiles.TileCodeDoor;

public class code_change implements code_packets {

	int x, y, z;
	int oldcode, newcode;
	
	public code_change() {
	}

	public code_change(TileEntity tile, int oldcode, int newcode) {
		x = tile.xCoord;
		y = tile.yCoord;
		z = tile.zCoord;
		this.oldcode = oldcode;
		this.newcode = newcode;
	}

	@Override
	public void readBytes(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		oldcode = buffer.readInt();
		newcode = buffer.readInt();
	}

	@Override
	public void writeBytes(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(oldcode);
		buffer.writeInt(newcode);
	}

	@Override
	public void executeServer(EntityPlayerMP player) {
		TileEntity t = player.worldObj.getTileEntity(x, y, z);
		if ((t != null) && ((t instanceof TileCodeDoor))) {
			TileCodeDoor tile = (TileCodeDoor) t;

			if ((tile.code != oldcode) && (tile.code >= 0)) {
				return;
			}
			tile.code = newcode;
			tile.isSet = true;

			if (player.worldObj.getBlock(x, y, z) == BlocksRegistry.doorL1)
			{
				((doorL1) BlocksRegistry.doorL1).flipDoor(player.worldObj, x, y, z, player);
			}else if (player.worldObj.getBlock(x, y, z) == BlocksRegistry.doorL2)
			{
				((doorL2) BlocksRegistry.doorL2).flipDoor(player.worldObj, x, y, z, player);
			}else if (player.worldObj.getBlock(x, y, z) == BlocksRegistry.doorL3)
			{
				((doorL3) BlocksRegistry.doorL3).flipDoor(player.worldObj, x, y, z, player);
			}
			player.playerNetServerHandler.sendPacket(tile.getDescriptionPacket());
		}
	}
	
	@Override
	public void executeClient() {
		return;
	}
}
