package disc.mods.factions.command;

import disc.mods.factions.capabilities.IFactionCapability;
import disc.mods.factions.utils.CapabilityHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class RequestCommand extends FactionsCommand
{

    @Override
    public String getName()
    {
        return "request";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/request {modid:registryname}";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            IFactionCapability cap = CapabilityHelper.getFactionCapability((EntityPlayer) sender);
            if(cap.hasFaction())
            {
                cap.getFaction().RequestHandler.requestItem(args[0]);
            }
        }
    }

}
