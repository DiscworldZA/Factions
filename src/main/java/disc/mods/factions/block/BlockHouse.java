package disc.mods.factions.block;

import disc.mods.factions.tileentity.TileEntityHouse;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHouse extends BlockBuildable
{
    public BlockHouse(String Name)
    {
        super(Name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityHouse();
    }

}
