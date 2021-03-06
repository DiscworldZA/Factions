package disc.mods.factions.init;

import disc.mods.factions.ref.Names;
import disc.mods.factions.tileentity.TileEntityBlacksmith;
import disc.mods.factions.tileentity.TileEntityBuilding;
import disc.mods.factions.tileentity.TileEntityFaction;
import disc.mods.factions.tileentity.TileEntityHouse;
import disc.mods.factions.tileentity.TileEntityLumberjack;
import disc.mods.factions.tileentity.TileEntityPostOffice;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FactionsTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityBuilding.class, Names.TileEntity.Building);
        GameRegistry.registerTileEntity(TileEntityFaction.class, Names.TileEntity.Faction);
        GameRegistry.registerTileEntity(TileEntityHouse.class, Names.TileEntity.House);
        GameRegistry.registerTileEntity(TileEntityBlacksmith.class, Names.TileEntity.Blacksmith);
        GameRegistry.registerTileEntity(TileEntityLumberjack.class, Names.TileEntity.Lumberjack);
        GameRegistry.registerTileEntity(TileEntityPostOffice.class, Names.TileEntity.PostOffice);
    }
}
