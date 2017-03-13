package disc.mods.factions.faction.buildings;

import disc.mods.factions.entity.EntityBlacksmith;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.Faction;
import disc.mods.factions.tileentity.TileEntityBuilding;

public class BuildingBlacksmith extends FactionBuilding
{
    public BuildingBlacksmith(TileEntityBuilding building)
    {
        super(building);
    }

    @Override
    public void update()
    {
    }

    @Override
    public EntityLivingAI getAIType()
    {
        return new EntityBlacksmith(tile.getWorld());
    }

}
