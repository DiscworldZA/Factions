package disc.mods.factions.faction.buildings;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.entity.EntityLumberjack;
import disc.mods.factions.tileentity.TileEntityBuilding;

public class BuildingLumberJack extends FactionBuilding
{

    public BuildingLumberJack(TileEntityBuilding tile)
    {
        super(tile);
    }

    @Override
    public void update()
    {

    }

    @Override
    public EntityLivingAI getAIType()
    {
        return new EntityLumberjack(tile.getWorld());
    }

}
