package disc.mods.factions.ai;

import disc.mods.factions.ai.actions.AIAction;
import disc.mods.factions.ai.task.AITask;
import disc.mods.factions.entity.EntityLivingAI;
import net.minecraft.entity.ai.EntityAIBase;

public class FactionAI extends EntityAIBase
{
    private EntityLivingAI handler;
    private AITask currentTask;

    public FactionAI(EntityLivingAI entity)
    {
        handler = entity;
    }

    @Override
    public boolean shouldExecute()
    {
        if (handler.isServerWorld())
        {
            return handler.factionTasks.hasTasks() && handler.factionTasks.getFirstTask().shouldExecute();
        }
        return false;
    }

    @Override
    public void startExecuting()
    {
        if (handler.isServerWorld())
        {
            currentTask = handler.factionTasks.getFirstTask();
        }
    }

    @Override
    public void updateTask()
    {
        if (handler.isServerWorld())
        {
            currentTask.updateTask();
        }
    }

    @Override
    public boolean continueExecuting()
    {
        if (handler.isServerWorld())
        {
            
        }
        return false;
    }

    @Override
    public void resetTask()
    {
        if (handler.isServerWorld())
        {
            handler.factionTasks.removeTask(currentTask);
        }
    }

}
