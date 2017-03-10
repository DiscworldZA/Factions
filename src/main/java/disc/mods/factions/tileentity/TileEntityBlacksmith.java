package disc.mods.factions.tileentity;

import disc.mods.factions.ai.faction.buildings.BuildingBlacksmith;

public class TileEntityBlacksmith extends TileEntityBuilding
{
    public TileEntityBlacksmith()
    {
        building = new BuildingBlacksmith(this);
    }
}
