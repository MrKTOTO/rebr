package ru.mrktoto.rebr.items;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.mrktoto.rebr.rebr;

public class buildingMallet_item2 extends Item {
	boolean mode = false;
	private static final String __OBFID = "CL_00000072";
	 
    public buildingMallet_item2(String name, String texture, int maxStackSize) {
        this.canRepair = false;
        this.setUnlocalizedName(name);
        this.setTextureName(rebr.MODID + ":" + texture);
        //this.setCreativeTab(REBR);
        this.maxStackSize = maxStackSize;
        GameRegistry.registerItem(this, name);
        
        
    }
    
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.epic;
    }
}
