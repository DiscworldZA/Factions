package disc.mods.factions.entity;

import disc.mods.factions.ai.FactionAI;
import disc.mods.factions.ai.InventoryAI;
import disc.mods.factions.ai.task.FactionAITasks;
import disc.mods.factions.faction.buildings.FactionBuilding;
import disc.mods.factions.ref.Names;
import disc.mods.factions.tileentity.TileEntityBuilding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityLivingAI extends EntityLiving
{
    public BlockPos buildingBlockPos;
    public FactionAITasks factionTasks;
    public InventoryAI inventory;

    public EntityLivingAI(World worldIn)
    {
        super(worldIn);
        factionTasks = new FactionAITasks();
        inventory = new InventoryAI(this, getInventorySize());
    }

    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        tasks.addTask(1, new FactionAI(this));
    }

    public double getMovementSpeed()
    {
        return 0.42D;
    }

    public abstract ResourceLocation getTexture();

    public abstract int getInventorySize();

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        inventory.readFromNBT(compound);
        if (compound.hasKey(Names.NBT.TileEntityPosition))
        {
            int[] pos = compound.getIntArray(Names.NBT.TileEntityPosition);
            buildingBlockPos = new BlockPos(pos[0], pos[1], pos[2]);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        inventory.writeToNBT(compound);
        if (buildingBlockPos != null)
        {
            int[] pos =
            { buildingBlockPos.getX(), buildingBlockPos.getY(), buildingBlockPos.getZ() };
            compound.setIntArray(Names.NBT.TileEntityPosition, pos);
        }
    }

    public FactionBuilding getFactionBuilding()
    {
        return ((TileEntityBuilding) world.getTileEntity(buildingBlockPos)).getFactionBuilding();
    }
}
