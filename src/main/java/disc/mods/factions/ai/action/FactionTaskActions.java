package disc.mods.factions.ai.action;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.utils.queue.SimpleObjectQueue;

public class FactionTaskActions extends SimpleObjectQueue<AIAction, EntityLivingAI>
{
    protected EntityLivingAI handler;

    public FactionTaskActions(EntityLivingAI entity)
    {
        super(entity);
    }
}
