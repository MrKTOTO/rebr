package ru.mrktoto.rebr.entity;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.tiles.TileCodeDoor;
import ru.mrktoto.rebr.tiles.TileEntityCounterPersonal;

public class c4 extends EntityThrowable
{
	
    public c4(World world)
    {
        super(world);
    }

    public c4(World world, EntityLivingBase thrower)
    {
        super(world, thrower);
        //this.setVelocity(this.motionX/3, this.motionY/3, this.motionZ/3);
        motionX /= 3;
        motionY /= 3;
        motionZ /= 3;
    }

    public c4(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }
    
    protected void onImpact(MovingObjectPosition pos)
    {
        if (pos.entityHit != null)
        {
            pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if(pos.typeOfHit == MovingObjectType.BLOCK && (!this.worldObj.isRemote)){
            TileEntity tile = worldObj.getTileEntity(pos.blockX, pos.blockY, pos.blockZ);
            if(tile instanceof TileEntityCounterPersonal){
            	net.minecraft.block.Block block = worldObj.getBlock(pos.blockX, pos.blockY, pos.blockZ);
            	if (block == BlocksRegistry.forstess_block) {
            		if (((TileEntityCounterPersonal) tile).getCount() >= 4) {
                		worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
    					float var1 = 0.5F;
    					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    				} else {
    					int count_damage_last = (4-((TileEntityCounterPersonal) tile).getCount());
    					int count_damage = 5;
    					if (count_damage_last < 5) {
    						worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
        					float var1 = 0.5F;
        					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    					}else {
	    					for (int x = 0; x < count_damage-1; x = x + 1) {
	    						((TileEntityCounterPersonal)tile).incrementCount();
	    					}
	    					int param = ((TileEntityCounterPersonal) tile).getCount();
	    					worldObj.setBlockMetadataWithNotify(pos.blockX, pos.blockY, pos.blockZ, param, 2); 
    					}
    				}
            	} else if (block == BlocksRegistry.forstess_blockL2) {
            		if (((TileEntityCounterPersonal) tile).getCount() >= 7) {
                		worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
    					float var1 = 0.5F;
    					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    				} else {
    					int count_damage_last = (7-((TileEntityCounterPersonal) tile).getCount());
    					int count_damage = 5;
    					if (count_damage_last < 5) {
    						worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
        					float var1 = 0.5F;
        					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    					}else {
	    					for (int x = 0; x < count_damage-1; x = x + 1) {
	    						((TileEntityCounterPersonal)tile).incrementCount();
	    					}
	    					int param = ((TileEntityCounterPersonal) tile).getCount();
	    					worldObj.setBlockMetadataWithNotify(pos.blockX, pos.blockY, pos.blockZ, param, 2); 
    					}
    				}
            	} else if (block == BlocksRegistry.forstess_blockL3) {
            		if (((TileEntityCounterPersonal) tile).getCount() >= 11) {
                		worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
    					float var1 = 0.5F;
    					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    				} else {
    					int count_damage_last = (11-((TileEntityCounterPersonal) tile).getCount());
    					int count_damage = 5;
    					if (count_damage_last < 5) {
    						worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
        					float var1 = 0.5F;
        					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    					}else {
	    					for (int x = 0; x < count_damage-1; x = x + 1) {
	    						((TileEntityCounterPersonal)tile).incrementCount();
	    					}
	    					int param = ((TileEntityCounterPersonal) tile).getCount();
	    					worldObj.setBlockMetadataWithNotify(pos.blockX, pos.blockY, pos.blockZ, param, 2); 
    					}
    				}
            	} 
            	
            } else if (tile instanceof TileCodeDoor) {
            	net.minecraft.block.Block block = worldObj.getBlock(pos.blockX, pos.blockY, pos.blockZ);
            	if (block == BlocksRegistry.doorL1) {
            		if (((TileCodeDoor) tile).getCount() >= 0) {
                		worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
    					float var1 = 0.5F;
    					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    				} else {
    					((TileCodeDoor)tile).incrementCount();
    					if (worldObj.getBlock(pos.blockX, pos.blockY+1, pos.blockZ) == BlocksRegistry.doorL1) {
    						TileEntity tile2 = worldObj.getTileEntity(pos.blockX, pos.blockY+1, pos.blockZ);
    						((TileCodeDoor)tile2).incrementCount();
    					} else if (worldObj.getBlock(pos.blockX, pos.blockY-1, pos.blockZ) == BlocksRegistry.doorL1) {
    						TileEntity tile2 = worldObj.getTileEntity(pos.blockX, pos.blockY-1, pos.blockZ);
    						((TileCodeDoor)tile2).incrementCount();
    					}
    					//int param = ((TileCodeDoor) tile).getCount();
    					//worldObj.setBlockMetadataWithNotify(pos.blockX, pos.blockY, pos.blockZ, param, 2); 
    				}         			
            	} else if (block == BlocksRegistry.doorL2) {
            		if (((TileCodeDoor) tile).getCount() >= 0) {
                		worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
    					float var1 = 0.5F;
    					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    				} else {
    					((TileCodeDoor)tile).incrementCount();
    					if (worldObj.getBlock(pos.blockX, pos.blockY+1, pos.blockZ) == BlocksRegistry.doorL2) {
    						TileEntity tile2 = worldObj.getTileEntity(pos.blockX, pos.blockY+1, pos.blockZ);
    						((TileCodeDoor)tile2).incrementCount();
    					} else if (worldObj.getBlock(pos.blockX, pos.blockY-1, pos.blockZ) == BlocksRegistry.doorL2) {
    						TileEntity tile2 = worldObj.getTileEntity(pos.blockX, pos.blockY-1, pos.blockZ);
    						((TileCodeDoor)tile2).incrementCount();
    					}
    				}
            	} else if (block == BlocksRegistry.doorL3) {
            		if (((TileCodeDoor) tile).getCount() >= 1) {
                		worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
    					float var1 = 0.5F;
    					worldObj.createExplosion(this, pos.blockX+0.5, pos.blockY+0.5, pos.blockZ+0.5, var1, true);
    				} else {
    					((TileCodeDoor)tile).incrementCount();
    					if (worldObj.getBlock(pos.blockX, pos.blockY+1, pos.blockZ) == BlocksRegistry.doorL3) {
    						TileEntity tile2 = worldObj.getTileEntity(pos.blockX, pos.blockY+1, pos.blockZ);
    						((TileCodeDoor)tile2).incrementCount();
    					} else if (worldObj.getBlock(pos.blockX, pos.blockY-1, pos.blockZ) == BlocksRegistry.doorL3) {
    						TileEntity tile2 = worldObj.getTileEntity(pos.blockX, pos.blockY-1, pos.blockZ);
    						((TileCodeDoor)tile2).incrementCount();
    					}
    				}
            	}
            }
        }
        
        if (!this.worldObj.isRemote)
        {
        	
            this.explode();
            this.setDead();
        }

        for (int var5 = 0; var5 < 8; ++var5)
        {
        	this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }

    }
    
    private void explode()
    {
        float var1 = 2.0F;
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, var1, true);
    }
    
}
