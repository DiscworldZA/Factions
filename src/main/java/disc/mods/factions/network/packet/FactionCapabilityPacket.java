package disc.mods.factions.network.packet;

import disc.mods.factions.capabilities.IFactionCapability;
import disc.mods.factions.utils.CapabilityHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FactionCapabilityPacket implements IMessage, IMessageHandler<FactionCapabilityPacket, IMessage>
{
    private String factionName;

    public FactionCapabilityPacket()
    {
        factionName = "";
    }

    public FactionCapabilityPacket(String FactionName)
    {
        factionName = FactionName;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        factionName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, factionName);
    }

    @Override
    public IMessage onMessage(FactionCapabilityPacket message, MessageContext ctx)
    {
        IFactionCapability cap = CapabilityHelper.getFactionCapability((EntityPlayer) Minecraft.getMinecraft().player);
        if (!cap.hasFaction())
        {
            cap.setFactionName(message.factionName);
        }
        return null;
    }

}
