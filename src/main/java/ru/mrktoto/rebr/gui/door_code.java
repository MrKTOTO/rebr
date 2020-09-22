package ru.mrktoto.rebr.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.net.check_code;
import ru.mrktoto.rebr.net.code_change;
import ru.mrktoto.rebr.tiles.TileCodeDoor;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;

public class door_code extends GuiScreen {
	protected int xSize = 127;

	protected int ySize = 166;

	ResourceLocation background = new ResourceLocation(rebr.MODID + ":" + "textures/gui/code.png");
	World world;
	int i;
	int j;
	int k;
	TileCodeDoor tile;
	EntityPlayer player;
	String current = "";
	/** current code */
	int code;
	int oldcode;
	int counter;
	int change = 0;
	int resetTick = 0;
	boolean checking;

	public door_code(World w, int i1, int j1, int k1, EntityPlayer p) {
		this.world = w;
		this.i = i1;
		this.j = j1;
		this.k = k1;
		this.tile = ((TileCodeDoor) this.world.getTileEntity(this.i, this.j, this.k));
		this.player = p;

		if (!tile.isSet)
			change = 2;
	}

	public void initGui() {
		this.buttonList.clear();
		int x = (this.width - this.xSize) / 2 + 16;
		int y = (this.height - this.ySize) / 2 + 55;
		if ((this.width <= 0) || (this.height <= 0) || (x <= 0) || (y <= 0)) {
			ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
			System.err.println(String.format("GuiCode: MC said gui is %dx%d but I don't trust 'em so I tried to calculate it... result: %dx%d", Integer.valueOf(this.width),
					Integer.valueOf(this.height), Integer.valueOf(scaledresolution.getScaledWidth()), Integer.valueOf(scaledresolution.getScaledHeight())));
			this.width = scaledresolution.getScaledWidth();
			this.height = scaledresolution.getScaledHeight();
		}
		x = (this.width - this.xSize) / 2 + 22;
		y = (this.height - this.ySize) / 2 + 55;
		if ((x < 0) || (x > this.width))
			x = 0;
		if ((y < 0) || (y > this.width))
			y = 0;
		int x2 = 35;
		int y2 = 25;
		this.buttonList.add(new GuiButton(11, x, y, 18, 18, "1"));
		this.buttonList.add(new GuiButton(12, x + x2, y, 18, 18, "2"));
		this.buttonList.add(new GuiButton(13, x + x2 * 2, y, 18, 18, "3"));
		this.buttonList.add(new GuiButton(14, x, y + y2, 18, 18, "4"));
		this.buttonList.add(new GuiButton(15, x + x2, y + y2, 18, 18, "5"));
		this.buttonList.add(new GuiButton(16, x + x2 * 2, y + y2, 18, 18, "6"));
		this.buttonList.add(new GuiButton(17, x, y + y2 * 2, 18, 18, "7"));
		this.buttonList.add(new GuiButton(18, x + x2, y + y2 * 2, 18, 18, "8"));
		this.buttonList.add(new GuiButton(19, x + x2 * 2, y + y2 * 2, 18, 18, "9"));
		this.buttonList.add(new GuiButton(0, x + x2 - 6, y + y2 * 3, 30, 20, "ACCEPT"));
	}

	public void keyTyped(char c, int i) {
		if ((i == 1) || (i == 18)) {
			this.mc.displayGuiScreen(null);
			this.mc.setIngameFocus();
		}

		if ((!this.checking) && (i == 14) && (this.current.length() > 0) && (this.resetTick == 0)) {
			this.current = this.current.substring(0, this.current.length() - 1);
		}

		if (i == 28) {
			buttonPressed(0);
		}

		try {
			int j = Integer.parseInt(Character.toString(c));
			buttonPressed(10 + j);
		} catch (Exception e) {
		}
	}

	public void updateScreen() {
		this.counter += 1;
		if (this.resetTick > 0) {
			this.resetTick -= 1;
			if (this.resetTick == 0)
				this.current = "";
		}
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	protected void actionPerformed(GuiButton button) {
		buttonPressed(button.id);
	}

	public void buttonPressed(int id) {
		if ((this.checking) && (id != 1)) {
			return;
		}

		if (this.resetTick > 0) {
			this.resetTick = 0;
			this.current = "";
		}

		if (id == 0) {
			if ((this.current.length() > 0) && (this.change == 0)) {
				sendCodeCheckPacket(Integer.parseInt(this.current), true);
				this.checking = true;
			} else if (this.change == 1) {
				if (this.current.length() > 0) {
					sendCodeCheckPacket(Integer.parseInt(this.current), false);
					this.checking = true;
				} else {
					this.change = 0;
				}
			} else if ((this.change == 2) && (this.current.length() > 0)) {
				sendCodeChangePacket(this.oldcode, Integer.parseInt(this.current));
				this.change = 0;
				this.current = "";
				this.mc.displayGuiScreen(null);
			}
		} else if (id == 1) {
			this.current = "";
			this.change = 0;
			this.checking = false;
		} else if (id == 2) {
			if (!tile.isSet) {
				this.current = "";
				this.change = 2;
			} else {
				this.current = "";
				this.change = 1;
			}
		} else if ((id >= 10) && (id < 20)) {
			int num = id - 10;
			if (this.current.length() <= 4 && !(current.length() == 0 && num == 0)) // max length 5, no trailing 0
				this.current += num;
		}
	}

	public void handleCodeCheckAnswer(boolean answer) {
		if (this.change == 0) {
			if (!answer) {
				this.checking = false;
				this.current = "Access denied!";
				this.resetTick = 20;
			} else {
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
		} else if (this.change == 1) {
			if (!answer) {
				this.checking = false;
				this.current = "Access denied!";
				this.resetTick = 20;
			} else {
				this.checking = false;
				this.oldcode = Integer.parseInt(this.current);
				this.change = 2;
				this.current = "";
			}
		}
	}

	// GuiChest
	public void drawScreen(int par1, int par2, float par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
		this.mc.getTextureManager().bindTexture(this.background);
		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, 176, 166);

		drawString(this.fontRendererObj, (this.change == 1 ? "Old code: " : this.change == 2 ? "Installation code: " : "")
				+ (this.checking ? "..." : new StringBuilder().append(this.current).append(this.counter % 16 > 8 ? "_" : " ").toString()), j + 19, k + 27, 65280);

		super.drawScreen(par1, par2, par3);
	}

	private void sendCodeCheckPacket(int code, boolean open) {
		rebr.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		rebr.channels.get(Side.CLIENT).writeOutbound(new check_code(this.tile, code, open));
	}

	public void sendCodeChangePacket(int oldcode, int newcode) {
		rebr.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		rebr.channels.get(Side.CLIENT).writeOutbound(new code_change(this.tile, oldcode, newcode));
	}
}