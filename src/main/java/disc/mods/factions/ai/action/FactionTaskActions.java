package disc.mods.factions.ai.action;

import java.util.Set;

import com.google.common.collect.Sets;

import disc.mods.factions.ai.task.AITask;

public class FactionTaskActions
{
    public final Set<AIAction> actions = Sets.<AIAction> newLinkedHashSet();

    public void addAction(AIAction action)
    {
        actions.add(action);
    }

    public boolean hasActions()
    {
        return actions.size() > 0;
    }

    public AIAction getFirstAction()
    {
        if (hasActions())
        {
            for (AIAction action : actions)
            {
                return action;
            }
        }
        return null;
    }

    public void removeAction(AIAction action)
    {
        actions.remove(action);
    }
}
