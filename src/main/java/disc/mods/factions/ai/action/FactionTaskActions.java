package disc.mods.factions.ai.action;

import java.util.Iterator;

import net.minecraft.util.NonNullList;

public class FactionTaskActions
{
    public final NonNullList<AIAction> actions = NonNullList.<AIAction> create();
    public AIAction currentAction;
    private Iterator<AIAction> actionIt;

    public void addAction(AIAction action)
    {
        actions.add(action);
    }

    public AIAction get(int index)
    {
        return actions.get(index);
    }

    public boolean hasActions()
    {
        return actions.size() > 0;
    }

    public AIAction getFirstAction()
    {
        if (hasActions())
        {
            return (actionIt = actions.iterator()).next();
        }
        return null;
    }

    public boolean hasNextAction()
    {
        return actionIt.hasNext();
    }

    public AIAction getNextAction()
    {
        if (actionIt.hasNext())
        {
            return actionIt.next();
        }
        return null;
    }

    public void removeAction(AIAction action)
    {
        actions.remove(action);
    }

    public void resetActions()
    {
        for (AIAction action : actions)
        {
            action.resetAction();
        }
    }
}
