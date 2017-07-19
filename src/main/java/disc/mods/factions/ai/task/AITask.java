package disc.mods.factions.ai.task;

import disc.mods.factions.ai.action.AIAction;
import disc.mods.factions.ai.action.FactionTaskActions;
import disc.mods.factions.entity.EntityLivingAI;

public abstract class AITask
{
    protected EntityLivingAI handler;
    public final FactionTaskActions taskActions;

    public AITask(EntityLivingAI entity)
    {
        handler = entity;
        taskActions = new FactionTaskActions(entity);
    }

    public abstract boolean shouldExecute();

    public abstract void queueActions();

    public void startExecuting()
    {
        taskActions.currentAction = taskActions.getFirstAction();
    }

    public boolean continueExecuting()
    {
        return taskActions.hasNextAction();
    }

    public boolean updateTask()
    {
        if (!taskActions.currentAction.continueExecuting())
        {
            taskActions.currentAction = taskActions.getNextAction();
        }
        return taskActions.currentAction.updateAction();
    }

    public void resetTask()
    {
        taskActions.resetActions();
    }
}