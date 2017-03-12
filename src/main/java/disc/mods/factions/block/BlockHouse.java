package disc.mods.factions.block;

import disc.mods.factions.tileentity.TileEntityHouse;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHouse extends BlockBuildable
{
    public BlockHouse(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityHouse();
    }

}
