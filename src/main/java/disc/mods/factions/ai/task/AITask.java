package disc.mods.factions.ai.task;

import disc.mods.factions.ai.actions.FactionTaskActions;
import disc.mods.factions.entity.EntityLivingAI;

public abstract class AITask
{
    protected EntityLivingAI handler;
    public FactionTaskActions taskActions;
    
    public AITask(EntityLivingAI entity)
    {
        handler = entity;
        queueActions();
    }
    
    public abstract boolean shouldExecute();
    
    public abstract void queueActions();
    
    public abstract void updateTask();
}