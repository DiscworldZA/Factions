package disc.mods.factions.ai.task;

import disc.mods.factions.ai.action.LogAction;
import disc.mods.factions.entity.EntityLivingAI;

public class DebugTask extends AITask
{
    int timeouttries = 0;
    int count = 0;
    String msg;

    public DebugTask(String msg, int timeouttries)
    {
        this.timeouttries = timeouttries;
        this.msg = msg;
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    @Override
    public void queueActions()
    {
        this.taskActions.add(new LogAction(msg + " 1"));
    }

    @Override
    public boolean canBeFullfilled()
    {
        return count++ == timeouttries;
    }
}
