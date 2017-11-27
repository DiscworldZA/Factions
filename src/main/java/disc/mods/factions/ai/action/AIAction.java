package disc.mods.factions.ai.action;

import disc.mods.factions.ai.queue.IQueueHandler;
import disc.mods.factions.ai.queue.IQueueable;
import disc.mods.factions.entity.EntityLivingAI;

public abstract class AIAction implements IQueueable<EntityLivingAI>
{
    protected EntityLivingAI handler;

    public abstract boolean shouldExecute();

    public abstract void startExecuting();

    public abstract boolean updateAction();

    public boolean continueExecuting()
    {
        return false;
    }

    public void resetAction()
    {

    }

    @Override
    public void setHandler(EntityLivingAI handler)
    {
        this.handler = handler;
    }
}
