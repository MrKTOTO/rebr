package ru.mrktoto.rebr.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.entity.EntityDStick;

public class bickford_fuse extends Item {
	boolean mode = false;
	private static final String __OBFID = "CL_00000023";
	
    public bickford_fuse(String name, String texture, int maxStackSize)
    {
    	this.canRepair = false;
        this.setUnlocalizedName(name);
    	this.setTextureName(rebr.MODID + ":" + texture);
    	this.maxStackSize = maxStackSize;
        GameRegistry.registerItem(this, name);
    }
}
