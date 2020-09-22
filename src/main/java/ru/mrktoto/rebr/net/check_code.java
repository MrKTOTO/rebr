package ru.mrktoto.rebr.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.blocks.doorL1;
import ru.mrktoto.rebr.blocks.doorL2;
import ru.mrktoto.rebr.blocks.doorL3;
import ru.mrktoto.rebr.tiles.TileCodeDoor;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;

public class check_code implements code_packets {

	int x, y, z;
	int code;
	boolean open; 
	
	public check_code() {
	}

	public check_code(TileEntity tile, int code, boolean open) {
		x = tile.xCoord;
		y = tile.yCoord;
		z = tile.zCoord;
		this.code = code;
		this.open = open;
	}

	@Override
	public void readBytes(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		code = buffer.readInt();
		open = buffer.readBoolean();
	}

	@Override
	public void writeBytes(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(code);
		buffer.writeBoolean(open);
	}

	@Override
	public void executeServer(EntityPlayerMP player) {
		TileEntity t = player.worldObj.getTileEntity(x, y, z);
		if ((t != null) && ((t instanceof TileCodeDoor))) {
			TileCodeDoor tile = (TileCodeDoor) t;
			if (tile.code == code) {
				sendCodeCheckAnswer(x, y, z, true, player);

				if (open) {
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
				}
				player.playerNetServerHandler.sendPacket(tile.getDescriptionPacket());
			} else {
				sendCodeCheckAnswer(x, y, z, false, player);
			}
		}
	}

	private void sendCodeCheckAnswer(int x, int y, int z, boolean answer, EntityPlayer player) {
		rebr.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		rebr.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		rebr.channels.get(Side.SERVER).writeOutbound(new check_answer(x, y, z, answer));
	}

	@Override
	public void executeClient() {
	}
}
