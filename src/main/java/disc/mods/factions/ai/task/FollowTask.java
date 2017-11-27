package disc.mods.factions.ai.task;

import disc.mods.factions.ai.action.FollowAction;
import disc.mods.factions.entity.EntityLivingAI;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class FollowTask extends AITask
{

    public EntityPlayer entityToFollow;

    public FollowTask(EntityPlayer entityLiving)
    {
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
        taskActions.add(new FollowAction().setPlayer(entityToFollow));
    }

    @Override
    public boolean canBeFullfilled()
    {
        return true;
    }

}
