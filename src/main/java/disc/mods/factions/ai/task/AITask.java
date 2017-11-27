package disc.mods.factions.ai.task;

import disc.mods.factions.Factions;
import disc.mods.factions.ai.action.FactionTaskActions;
import disc.mods.factions.ai.queue.IQueueHandler;
import disc.mods.factions.ai.queue.IQueueable;
import disc.mods.factions.entity.EntityLivingAI;

public abstract class AITask implements IQueueable<EntityLivingAI>
{
    protected EntityLivingAI handler;
    public FactionTaskActions taskActions;

    public boolean paused = false;

    public abstract boolean shouldExecute();

    public abstract void queueActions();

    public abstract boolean canBeFullfilled();

    public void startExecuting()
    {
        Factions.logger.info("startExecuting Task");
        Factions.logger.info("startExecuting Action");
        taskActions.pop().startExecuting();
    }

    public boolean continueExecuting()
    {
        Factions.logger.info("continueExecuting Task");
        if (paused)
        {
            if (canBeFullfilled())
            {
                paused = false;
                return false;
            }
            else
                return true;
        }
        return taskActions.hasNext();
    }

    public boolean updateTask()
    {
        Factions.logger.info("updateTask Task");
        Factions.logger.info("updateAction Action");
        boolean updated = taskActions.current.updateAction(); 
        Factions.logger.info("continueExecuting Action");
        if (!taskActions.current.continueExecuting())
        {
            if (taskActions.hasNext())
                Factions.logger.info("startExecuting Action");
                taskActions.pop().startExecuting();
        }
        return updated;
    }

    public void resetTask()
    {
        Factions.logger.info("resetTask Task");
        taskActions.all().forEach(x -> x.resetAction());
    }

    @Override
    public void setHandler(EntityLivingAI handler)
    {
        this.handler = handler;
        taskActions = new FactionTaskActions(handler);
    }
}