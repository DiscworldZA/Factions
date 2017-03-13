package disc.mods.factions.tileentity;

import disc.mods.factions.faction.buildings.BuildingPostOffice;

public class TileEntityPostOffice extends TileEntityBuilding
{
    public TileEntityPostOffice()
    {
        building = new BuildingPostOffice(this);
    }

    @Override
    public void update()
    {

    }

}
