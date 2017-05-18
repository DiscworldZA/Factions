package disc.mods.factions.ai.action;

import disc.mods.factions.entity.EntityLivingAI;

public abstract class AIAction
{
    protected EntityLivingAI handler;

    public AIAction(EntityLivingAI entity)
    {
        handler = entity;
    }

    public abstract boolean shouldExecute();

    public void startExecuting()
    {

    }

    public abstract boolean updateAction();

    public boolean continueExecuting()
    {
        return false;
    }

    public void resetAction()
    {
        
    }
}
