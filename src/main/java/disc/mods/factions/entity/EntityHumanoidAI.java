package disc.mods.factions.entity;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityHumanoidAI extends EntityLivingAI
{
    public EntityHumanoidAI(World worldIn)
    {
        super(worldIn);
        setSize(0.6F, 1.95F);
        enablePersistence();
    }
}
