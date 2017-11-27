package disc.mods.factions.ai.hooks;

import disc.mods.factions.Factions;
import disc.mods.factions.ai.action.AIAction;
import disc.mods.factions.ai.task.AITask;
import disc.mods.factions.entity.EntityLivingAI;
import net.minecraft.entity.ai.EntityAIBase;

public class FactionAIHook extends EntityAIBase
{
    private EntityLivingAI handler;

    public FactionAIHook(EntityLivingAI entity)
    {
        handler = entity;
    }

    @Override
    public boolean shouldExecute()
    {
        if (handler.isServerWorld())
        {
            return handler.factionTasks.hasNext();
        }
        return false;
    }

    @Override
    public void startExecuting()
    {
        if (handler.isServerWorld())
        {
            Factions.logger.info("startExecuting Hook");
            handler.factionTasks.pop().startExecuting();
        }
    }

    @Override
    public void updateTask()
    {
        if (handler.isServerWorld())
        {
            Factions.logger.info("updateTask Hook");
            if(!handler.factionTasks.current.updateTask())
            {
                handler.factionTasks.current.paused = true;
                handler.factionTasks.pushBack();
                handler.factionTasks.pop();
            }
        }
    }

    @Override
    public boolean continueExecuting()
    {
        if (handler.isServerWorld())
        {
            Factions.logger.info("continueExecuting Hook");
            return handler.factionTasks.current.continueExecuting();
        }
        return false;
    }

    @Override
    public void resetTask()
    {
        if (handler.isServerWorld())
        {
            Factions.logger.info("rsetTask Hook");
            handler.factionTasks.current.resetTask();
        }
    }

}
