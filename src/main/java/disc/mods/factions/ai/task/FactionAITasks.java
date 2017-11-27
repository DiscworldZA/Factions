package disc.mods.factions.ai.task;

import disc.mods.factions.ai.queue.IQueueHandler;
import disc.mods.factions.ai.queue.SimpleObjectQueue;
import disc.mods.factions.entity.EntityLivingAI;

public class FactionAITasks extends SimpleObjectQueue<AITask, EntityLivingAI>
{
    public FactionAITasks(IQueueHandler handler)
    {
        super(handler);
    }
    
    @Override
    public void add(AITask t)
    {
        super.add(t);
        t.queueActions();
    }

}
