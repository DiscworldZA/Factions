package disc.mods.factions.ai.task;

import disc.mods.factions.ai.action.FollowAction;
import disc.mods.factions.entity.EntityLivingAI;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class FollowTask extends AITask
{

    public EntityPlayer entityToFollow;
    
    public FollowTask(EntityLivingAI entity, EntityPlayer entityLiving)
    {
        super(entity);
        entityToFollow = entityLiving;
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    @Override
    public void queueActions()
    {
        taskActions.addAction(new FollowAction().setPlayer(entityToFollow));
    }

}
