package ru.mrktoto.rebr.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.mrktoto.rebr.gui.door_code;

public class check_answer implements code_packets {

	int x, y, z;
	boolean answer;

	public check_answer() {
	}

	public check_answer(int x, int y, int z, boolean answer) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.answer = answer;
	}

	@Override
	public void readBytes(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		answer = buffer.readBoolean();
	}

	@Override
	public void writeBytes(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeBoolean(answer);
	}

	@Override
	public void executeServer(EntityPlayerMP player) {
	}

	@Override
	public void executeClient() {
		GuiScreen gui = Minecraft.getMinecraft().currentScreen;

		if ((gui != null) && ((gui instanceof door_code))) {
			door_code guicode = (door_code) gui;
			guicode.handleCodeCheckAnswer(answer);
		} 
	}
}
