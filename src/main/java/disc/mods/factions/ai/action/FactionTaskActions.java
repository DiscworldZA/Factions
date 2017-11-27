package disc.mods.factions.ai.action;

import disc.mods.factions.ai.queue.SimpleObjectQueue;
import disc.mods.factions.entity.EntityLivingAI;

public class FactionTaskActions extends SimpleObjectQueue<AIAction, EntityLivingAI>
{
    protected EntityLivingAI handler;

    public FactionTaskActions(EntityLivingAI entity)
    {
        super(entity);
    }
}
