package disc.mods.factions.block;

import disc.mods.factions.tileentity.TileEntityBlacksmith;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBlacksmith extends BlockBuildable
{
    public BlockBlacksmith(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityBlacksmith();
    }

}
