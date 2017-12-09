package disc.mods.factions.ai.request;

import disc.mods.factions.Factions;
import disc.mods.factions.faction.Faction;
import disc.mods.factions.utils.queue.IQueueHandler;
import disc.mods.factions.utils.queue.IQueueable;

public class Request implements IQueueable
{
    private Faction faction;

    @Override
    public void setHandler(IQueueHandler handler)
    {
        if (handler instanceof Faction)
            faction = (Faction) handler;
        else
            Factions.logger.error("Invalid Handler Set on Factions");
    }
}
