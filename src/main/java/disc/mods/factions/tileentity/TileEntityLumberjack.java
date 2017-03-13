package disc.mods.factions.tileentity;

import disc.mods.factions.faction.buildings.BuildingBlacksmith;
import disc.mods.factions.faction.buildings.BuildingLumberJack;

public class TileEntityLumberjack extends TileEntityBuilding
{
    public TileEntityLumberjack()
    {
        building = new BuildingLumberJack(this);
    }
}
