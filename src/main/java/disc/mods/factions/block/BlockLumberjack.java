package disc.mods.factions.block;

import disc.mods.factions.tileentity.TileEntityLumberjack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLumberjack extends BlockBuildable
{

    public BlockLumberjack(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityLumberjack();
    }

}
