package disc.mods.factions.ai.request;

import disc.mods.factions.ai.queue.SimpleObjectQueue;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.Faction;
import disc.mods.factions.registry.Registries;

public class FactionRequestHandler extends SimpleObjectQueue<Request, Faction>
{
    private Faction faction;

    public FactionRequestHandler(Faction faction)
    {
        super(faction);
    }

    public EntityLivingAI findCrafterFor(String Id)
    {
        for (EntityLivingAI ai : faction.getAIs())
        {
            if (Registries.CrafterRegistry.get(ai).hasRecipe(Id))
            {
                return ai;
            }
        }
        return null;
    }

}
