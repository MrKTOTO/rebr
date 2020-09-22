 package ru.mrktoto.rebr.items;

 import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
 import net.minecraft.creativetab.CreativeTabs;
 import net.minecraft.item.Item;
import ru.mrktoto.rebr.rebr;

 public class door_remover extends Item {
 	public door_remover(String name, String texture, int maxStackSize) {
 		super();
 		this.canRepair = false;
        this.setUnlocalizedName(name);
    	this.setTextureName(rebr.MODID + ":" + texture);
    	this.maxStackSize = maxStackSize;
        GameRegistry.registerItem(this, name);
 	}
 }
