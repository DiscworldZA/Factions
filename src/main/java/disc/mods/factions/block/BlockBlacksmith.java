package disc.mods.factions.block;

import disc.mods.factions.tileentity.TileEntityBlacksmith;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBlacksmith extends BlockBuildable
{
    public BlockBlacksmith(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityBlacksmith();
    }

}
