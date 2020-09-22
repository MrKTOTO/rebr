package ru.mrktoto.rebr.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public interface code_packets {
	public void readBytes(ByteBuf bytes);

	public void writeBytes(ByteBuf bytes);

	public void executeServer(EntityPlayerMP player);

	public void executeClient();
}
