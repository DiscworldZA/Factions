package disc.mods.factions.tileentity;

import disc.mods.core.tile.CoreTileEntity;
import disc.mods.factions.ref.References;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class FactionsTileEntity extends CoreTileEntity
{
    @Override
    public void Register()
    {
        GameRegistry.registerTileEntity(this.getClass(), References.Mod.Id + "_" + name);        
    }
}
