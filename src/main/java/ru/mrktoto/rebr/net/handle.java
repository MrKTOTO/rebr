package ru.mrktoto.rebr.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.NetworkRegistry;

public class handle extends FMLIndexedMessageToMessageCodec<code_packets> {
	public handle() {
		addDiscriminator(0, check_code.class);
		addDiscriminator(1, check_answer.class);
		addDiscriminator(2, code_change.class);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, code_packets packet, ByteBuf data) throws Exception {
		packet.writeBytes(data);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data, code_packets packet) {
		packet.readBytes(data);
		switch (FMLCommonHandler.instance().getEffectiveSide()) {
		case CLIENT:
			packet.executeClient();
			break;
		case SERVER:
			INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
			packet.executeServer(((NetHandlerPlayServer) netHandler).playerEntity);
			break;
		}
	}
}
