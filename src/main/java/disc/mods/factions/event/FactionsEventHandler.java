package disc.mods.factions.event;

import disc.mods.core.utils.PlayerUtils;
import disc.mods.factions.ai.faction.FactionHandler;
import disc.mods.factions.block.BlockBuildable;
import disc.mods.factions.block.BlockFaction;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.Names;
import disc.mods.factions.ref.Names.NBT;
import disc.mods.factions.tileentity.TileEntityBuilding;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FactionsEventHandler
{
    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new FactionsEventHandler());
    }

    @SubscribeEvent
    public void Entity_EnterWorld(EntityJoinWorldEvent event)
    {
        if (event.getWorld().isRemote)
            return;
        if (event.getEntity() instanceof EntityLivingAI)
        {
            EntityLivingAI entity = (EntityLivingAI) event.getEntity();
            entity.getFactionBuilding().notifyAiLoaded(entity);
        }
    }

    @SubscribeEvent
    public void BlockRightClicked(PlayerInteractEvent.RightClickBlock event)
    {
        if (event.getItemStack().getItem() instanceof ItemBlock)
        {
            ItemBlock itemBlock = (ItemBlock) event.getItemStack().getItem();

            if (itemBlock.getBlock() instanceof BlockFaction)
            {
                if (PlayerUtils.hasPresistedData(event.getEntityPlayer(), NBT.FactionName))
                {
                    if (event.getWorld().isRemote)
                        PlayerUtils.sendMessage(event.getEntityPlayer(), "Already in a faction!");
                    event.setCanceled(true);
                }
            }
            else if (itemBlock.getBlock() instanceof BlockBuildable)
            {
                if (!PlayerUtils.hasPresistedData(event.getEntityPlayer(), NBT.FactionName))
                {
                    if (event.getWorld().isRemote)
                        PlayerUtils.sendMessage(event.getEntityPlayer(), "Not in a faction!");
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void BlockPlaced(BlockEvent.PlaceEvent event)
    {
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());

        if (tile instanceof TileEntityBuilding)
        {
            if (PlayerUtils.hasPresistedData(event.getPlayer(), NBT.FactionName))
            {
                TileEntityBuilding tileFaction = (TileEntityBuilding) tile;
                tileFaction.getFactionBuilding().faction = FactionHandler.getFaction(PlayerUtils.getPresistedData(event.getPlayer(), NBT.FactionName));
            }
        }
    }

}
