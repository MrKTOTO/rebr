package ru.mrktoto.rebr.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.entity.EntityDStick;

public class detonator extends Item {
	boolean mode = false;
	private static final String __OBFID = "CL_00000023";
	
    public detonator(String name, String texture, int maxStackSize)
    {
    	this.canRepair = false;
        this.setUnlocalizedName(name);
    	this.setTextureName(rebr.MODID + ":" + texture);
    	this.maxStackSize = maxStackSize;
        GameRegistry.registerItem(this, name);
    }
}
