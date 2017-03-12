package disc.mods.factions.capabilities;

import disc.mods.factions.ai.faction.Faction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IFactionCapability extends INBTSerializable<NBTTagCompound>
{
    public IFactionCapability setFactionName(String factionName);

    public String getFactionName();

    public Faction getFaction();

    public boolean hasFaction();

    public IFactionCapability sync(EntityPlayer player);

}
