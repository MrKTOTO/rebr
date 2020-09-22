package ru.mrktoto.rebr.net;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.gui.door_code;

public class door_gui_handle implements IGuiHandler {
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		boolean door = (world.getBlock(i, j, k) == BlocksRegistry.doorL1) || (world.getBlock(i, j, k) == BlocksRegistry.doorL2) || (world.getBlock(i, j, k) == BlocksRegistry.doorL3);
		if (ID == rebr.codeGUI)
			return new door_code(world, i, j - ((world.getBlockMetadata(i, j, k) >= 8) && (door) ? 1 : 0), k, player);
		return null;
	}
}
