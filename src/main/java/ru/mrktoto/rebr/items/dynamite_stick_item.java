package ru.mrktoto.rebr.items;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import akka.actor.LightArrayRevolverScheduler.TimerTask;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import ru.mrktoto.rebr.ExtendedPlayer;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.entity.EntityDStick;
import ru.mrktoto.rebr.net.eventhandlertimer;

public class dynamite_stick_item extends Item{
	boolean mode = false;
	private static final String __OBFID = "CL_00000023";
	protected EntityDStick EntityDStick;
	
	
    public dynamite_stick_item(String name, String texture, int maxStackSize)
    {
    	super();
    	this.canRepair = false;
        this.setUnlocalizedName(name);
    	this.setTextureName(rebr.MODID + ":" + texture);
    	this.maxStackSize = maxStackSize;
        GameRegistry.registerItem(this, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    
    public ItemStack onItemRightClick(ItemStack p1, World p2, EntityPlayer p3)
    {
    	ExtendedPlayer props = ExtendedPlayer.get(p3);
    	if(props.checkTimer() && (!p2.isRemote)) {
    		if (!p3.capabilities.isCreativeMode)
            {
                --p1.stackSize;
            }

            p2.playSoundAtEntity(p3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            
            if (!p2.isRemote)
            {
            	EntityDStick = new EntityDStick(p2, p3);
            	
            	p2.spawnEntityInWorld(new EntityDStick(p2, p3));
            }
            props.runTimer(9000);
    	} else if (!p2.isRemote && !(props.checkTimer())) {
			double time2 = props.getTimer()/1000.00;
			p3.addChatMessage(new ChatComponentTranslation("tile.timer.current", time2));
    	}
    	
        return p1;
    }
}
