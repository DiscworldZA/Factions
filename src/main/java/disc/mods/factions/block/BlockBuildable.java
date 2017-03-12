package disc.mods.factions.block;

import disc.mods.core.block.CoreTileEntityBlock;
import disc.mods.core.utils.PlayerUtils;
import disc.mods.factions.Factions;
import disc.mods.factions.creativetab.Tabs;
import disc.mods.factions.tileentity.TileEntityBuilding;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockBuildable extends CoreTileEntityBlock
{
    public BlockBuildable(String Name)
    {
        super(Name);
        this.setCreativeTab(Tabs.Factions);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND)
        {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityBuilding)
            {
                TileEntityBuilding building = (TileEntityBuilding) tile;
                // building.getFactionBuilding().spawnTestEntity();
            }
        }
        if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND)
        {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityBuilding)
            {
                TileEntityBuilding building = (TileEntityBuilding) tile;
                PlayerUtils.sendMessage(playerIn, building.getFactionBuilding().faction.Name);
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
    }
    
    public void registerItemModel(ItemBlock itemBlock)
    {
        Factions.proxy.registerItemRenderer(itemBlock, 0);
    }
}
