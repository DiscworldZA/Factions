package disc.mods.factions.ai.task;

import java.util.Set;

import com.google.common.collect.Sets;

import disc.mods.factions.ai.action.AIAction;
import net.minecraft.util.NonNullList;

public class FactionAITasks
{
    public final NonNullList<AITask> tasks = NonNullList.<AITask> create();

    public void addTask(AITask task)
    {
        tasks.add(task);
    }

    public boolean hasTasks()
    {
        return tasks.size() > 0;
    }

    public AITask getFirstTask()
    {
        if (hasTasks())
        {
            for (AITask task : tasks)
            {
                return task;
            }
        }
        return null;
    }

    public void removeTask(AITask task)
    {
        tasks.remove(task);
    }
}
