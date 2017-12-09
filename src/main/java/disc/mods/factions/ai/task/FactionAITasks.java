package disc.mods.factions.ai.task;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.utils.queue.IQueueHandler;
import disc.mods.factions.utils.queue.SimpleObjectQueue;

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
