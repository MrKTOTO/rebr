package ru.mrktoto.rebr.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.blocks.tile.BlockTileEntity;
import ru.mrktoto.rebr.tiles.TileCodeDoor;

public class doorL3 extends BlockTileEntity<TileCodeDoor>{
    private IIcon[] field_150017_a;
    private IIcon[] field_150016_b;
    private static final String __OBFID = "CL_00000230";

    public doorL3(String name, Material material, float hardness, float resistanse, SoundType soundType)
        {
    		super(name, material, hardness, resistanse, soundType);
            float var2 = 0.5F;
            float var3 = 1.0F;
            this.setHarvestLevel("axe", 10);	
    		this.setBlockUnbreakable();
    		this.setResistance(resistanse);
    		this.setBlockTextureName(rebr.MODID + ":" + "forstess_door/forstess_doorL3");
            this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var3, 0.5F + var2);
        }

        public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
        {
            if (side != 1 && side != 0)
            {
                int var6 = this.func_150012_g(access, x, y, z);
                int var7 = var6 & 3;
                boolean var8 = (var6 & 4) != 0;
                boolean var9 = false;
                boolean var10 = (var6 & 8) != 0;

                if (var8)
                {
                    if (var7 == 0 && side == 2)
                    {
                        var9 = !var9;
                    }
                    else if (var7 == 1 && side == 5)
                    {
                        var9 = !var9;
                    }
                    else if (var7 == 2 && side == 3)
                    {
                        var9 = !var9;
                    }
                    else if (var7 == 3 && side == 4)
                    {
                        var9 = !var9;
                    }
                }
                else
                {
                    if (var7 == 0 && side == 5)
                    {
                        var9 = !var9;
                    }
                    else if (var7 == 1 && side == 3)
                    {
                        var9 = !var9;
                    }
                    else if (var7 == 2 && side == 4)
                    {
                        var9 = !var9;
                    }
                    else if (var7 == 3 && side == 2)
                    {
                        var9 = !var9;
                    }

                    if ((var6 & 16) != 0)
                    {
                        var9 = !var9;
                    }
                }

                return var10 ? this.field_150017_a[var9 ? 1 : 0] : this.field_150016_b[var9 ? 1 : 0];
            }
            else
            {
                return this.field_150016_b[0];
            }
        }

        public void registerBlockIcons(IIconRegister icon)
        {
            this.field_150017_a = new IIcon[2];
            this.field_150016_b = new IIcon[2];
            this.field_150017_a[0] = icon.registerIcon(this.getTextureName() + "_upper");
            this.field_150016_b[0] = icon.registerIcon(this.getTextureName() + "_lower");
            this.field_150017_a[1] = new IconFlipped(this.field_150017_a[0], true, false);
            this.field_150016_b[1] = new IconFlipped(this.field_150016_b[0], true, false);
        }

        public boolean isOpaqueCube()
        {
            return false;
        }

        public boolean getBlocksMovement(IBlockAccess access, int x, int y, int z)
        {
            int var5 = this.func_150012_g(access, x, y, z);
            return (var5 & 4) != 0;
        }

        public boolean renderAsNormalBlock()
        {
            return false;
        }

        public int getRenderType()
        {
            return 7;
        }

        public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
        {
            this.setBlockBoundsBasedOnState(world, x, y, z);
            return super.getSelectedBoundingBoxFromPool(world, x, y, z);
        }

        public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
        {
            this.setBlockBoundsBasedOnState(world, x, y, z);
            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }

        public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
        {
            this.func_150011_b(this.func_150012_g(access, x, y, z));
        }

        public int func_150013_e(IBlockAccess access, int x, int y, int z)
        {
            return this.func_150012_g(access, x, y, z) & 3;
        }

        public boolean func_150015_f(IBlockAccess access, int x, int y, int z)
        {
            return (this.func_150012_g(access, x, y, z) & 4) != 0;
        }

        private void func_150011_b(int par)
        {
            float var2 = 0.1875F;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
            int var3 = par & 3;
            boolean var4 = (par & 4) != 0;
            boolean var5 = (par & 16) != 0;

            if (var3 == 0)
            {
                if (var4)
                {
                    if (!var5)
                    {
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
                    }
                    else
                    {
                        this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
                    }
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
                }
            }
            else if (var3 == 1)
            {
                if (var4)
                {
                    if (!var5)
                    {
                        this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    }
                    else
                    {
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
                    }
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
                }
            }
            else if (var3 == 2)
            {
                if (var4)
                {
                    if (!var5)
                    {
                        this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
                    }
                    else
                    {
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
                    }
                }
                else
                {
                    this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
            }
            else if (var3 == 3)
            {
                if (var4)
                {
                    if (!var5)
                    {
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
                    }
                    else
                    {
                        this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    }
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
                }
            }
        }

        public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        }

        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
        {
        	if (player.isSneaking())
    			return false;
    		int b = getFullMetadata(world, x, y, z);
    		if ((b & 0x8) != 0) {
    			return onBlockActivated(world, x, y - 1, z, player, par6, par6, par8, par9);
    		}
    		world.getTileEntity(x, y, z);

    		if (world.isRemote) {
    			if ((player.getCurrentEquippedItem() != null) && (player.getCurrentEquippedItem().getItem() == BlocksRegistry.door_remover))
    				return true;
    			if (isOpen(world, x, y, z)) {
    				return true;
    			}
    			player.openGui(rebr.instance, rebr.codeGUI, world, x, y, z);
    			return true;
    		}

    		if ((player.getCurrentEquippedItem() != null) && (player.getCurrentEquippedItem().getItem() == BlocksRegistry.door_remover)) {
    			if (isOpen(world, x, y, z)) {
    				dropBlockAsItem(world, x, y, z, (ItemStack) getDrops(world, x, y, z, 0, 1).get(0));
    				world.setBlockToAir(x, y, z);
    			} else {
    				player.addChatMessage(new ChatComponentTranslation("tile.door.openpls"));
    			}
    			return true;
    		}

    		if (isOpen(world, x, y, z)) {
    			flipDoor(world, x, y, z, player);
    			return true;
    		}

    		return true;
        }
        
        public void flipDoor(World world, int i, int j, int k, EntityPlayer player) {
    		int i1 = this.getFullMetadata(world, i, j, k);
    		int j1 = i1 & 7;
    		j1 ^= 4;

    		if ((i1 & 8) == 0) {
    			world.setBlockMetadataWithNotify(i, j, k, j1, 2);
    			world.markBlockRangeForRenderUpdate(i, j, k, i, j, k);
    		} else {
    			world.setBlockMetadataWithNotify(i, j - 1, k, j1, 2);
    			world.markBlockRangeForRenderUpdate(i, j - 1, k, i, j, k);
    		}

    		world.playAuxSFXAtEntity(player, 1003, i, j, k, 0);
    	}
        
        public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    		int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    		boolean flag = (l & 8) != 0;
    		int i1;
    		int j1;

    		if (flag) {
    			i1 = par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4);
    			j1 = l;
    		} else {
    			i1 = l;
    			j1 = par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4);
    		}

    		boolean flag1 = (j1 & 1) != 0;
    		return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
    	}
        
        public boolean isOpen(IBlockAccess iblockaccess, int i, int j, int k) {
    		int x = getFullMetadata(iblockaccess, i, j, k);
    		if ((x & 0x8) != 0)
    			return isOpen(iblockaccess, i, j - 1, k);
    		return iblockaccess.getBlockMetadata(i, j, k) > 3;
    	}

        public void func_150014_a(World world, int x, int y, int z, boolean p_150014_5_)
        {
            int var6 = this.func_150012_g(world, x, y, z);
            boolean var7 = (var6 & 4) != 0;

            if (var7 != p_150014_5_)
            {
                int var8 = var6 & 7;
                var8 ^= 4;

                if ((var6 & 8) == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, var8, 2);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
                else
                {
                    world.setBlockMetadataWithNotify(x, y - 1, z, var8, 2);
                    world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
                }

                world.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
            }
        }

        public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
        {
            int var6 = world.getBlockMetadata(x, y, z);

            if ((var6 & 8) == 0)
            {
                boolean var7 = false;

                if (world.getBlock(x, y + 1, z) != this)
                {
                    world.setBlockToAir(x, y, z);
                    var7 = true;
                }

                if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
                {
                    world.setBlockToAir(x, y, z);
                    var7 = true;

                    if (world.getBlock(x, y + 1, z) == this)
                    {
                        world.setBlockToAir(x, y + 1, z);
                    }
                }

                if (var7)
                {
                    if (!world.isRemote)
                    {
                        this.dropBlockAsItem(world, x, y, z, var6, 0);
                    }
                }
                else
                {

                }
            }
            else
            {
                if (world.getBlock(x, y - 1, z) != this)
                {
                    world.setBlockToAir(x, y, z);
                }

                if (block != this)
                {
                    this.onNeighborBlockChange(world, x, y - 1, z, block);
                }
            }
        }

        public Item getItemDropped(int par2, Random par3, int par4)
        {
            return (par2 & 8) != 0 ? null : (BlocksRegistry.doorL3_item);
        }

        public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 vec1, Vec3 vec2)
        {
            this.setBlockBoundsBasedOnState(world, x, y, z);
            return super.collisionRayTrace(world, x, y, z, vec1, vec2);
        }

        public boolean canPlaceBlockAt(World world, int x, int y, int z)
        {
            return y >= 255 ? false : World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
        }

        public int getMobilityFlag()
        {
            return 1;
        }

        public int func_150012_g(IBlockAccess access, int x, int y, int z)
        {
            int var5 = access.getBlockMetadata(x, y, z);
            boolean var6 = (var5 & 8) != 0;
            int var7;
            int var8;

            if (var6)
            {
                var7 = access.getBlockMetadata(x, y - 1, z);
                var8 = var5;
            }
            else
            {
                var7 = var5;
                var8 = access.getBlockMetadata(x, y + 1, z);
            }

            boolean var9 = (var8 & 1) != 0;
            return var7 & 7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
        }

        public Item getItem(World world, int x, int y, int z)
        {
            return BlocksRegistry.doorL3_item;
        }

        public void onBlockHarvested(World world, int x, int y, int z, int par5, EntityPlayer player)
        {
            if (player.capabilities.isCreativeMode && (par5 & 8) != 0 && world.getBlock(x, y - 1, z) == this)
            {
                world.setBlockToAir(x, y - 1, z);
            }
        }
        
        
        
        
        @Override
    	public Class<TileCodeDoor> getTileEntityClass() {
    		
    		return TileCodeDoor.class;
    	}

    	@Override
    	public TileCodeDoor createTileEntity(World world, int meta) {
    		
    		return new TileCodeDoor();
    	}
    }


