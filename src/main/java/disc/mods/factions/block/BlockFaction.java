package disc.mods.factions.block;

import disc.mods.core.utils.PlayerUtils;
import disc.mods.factions.ai.faction.FactionHandler;
import disc.mods.factions.ref.Names;
import disc.mods.factions.ref.Names.NBT;
import disc.mods.factions.tileentity.TileEntityBuilding;
import disc.mods.factions.tileentity.TileEntityFaction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFaction extends BlockBuildable
{
    public BlockFaction(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityFaction();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if (placer instanceof EntityPlayer)
        {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityFaction)
            {
                TileEntityFaction tileFaction = (TileEntityFaction) tile;
                tileFaction.faction = FactionHandler.newFaction(tileFaction, "Test Faction");
                PlayerUtils.setPresistedData((EntityPlayer) placer, NBT.FactionName, "Test Faction");
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND)
        {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityFaction)
            {
                TileEntityFaction building = (TileEntityFaction) tile;
                PlayerUtils.sendMessage(playerIn, building.faction.Name);
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
