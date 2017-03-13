package disc.mods.factions.block;

import disc.mods.factions.tileentity.TileEntityPostOffice;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPostOffice extends BlockBuildable
{

    public BlockPostOffice(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityPostOffice();
    }

}
