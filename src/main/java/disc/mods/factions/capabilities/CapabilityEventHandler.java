package disc.mods.factions.capabilities;

import disc.mods.core.util.ResourceLocationHelper;
import disc.mods.factions.ref.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityEventHandler
{
    public static final ResourceLocation FACTIONCAP = ResourceLocationHelper.getResourceLocation(References.Mod.Id, "factioncap");

    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(FACTIONCAP, new FactionCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void cloneEvent(PlayerEvent.Clone event)
    {
        NBTTagCompound compound = event.getOriginal().getCapability(FactionCapabilityProvider.FACTIONCAP, null).serializeNBT();
        event.getEntityPlayer().getCapability(FactionCapabilityProvider.FACTIONCAP, null).deserializeNBT(compound);

    }

    @SubscribeEvent
    public static void respawnEvent(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event)
    {
        event.player.getCapability(FactionCapabilityProvider.FACTIONCAP, null).sync((EntityPlayerMP) event.player);

    }

    @SubscribeEvent
    public static void playerChangeDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event)
    {
        event.player.getCapability(FactionCapabilityProvider.FACTIONCAP, null).sync((EntityPlayerMP) event.player);
    }

}
