package disc.mods.factions.client.renderer.entity;

import disc.mods.core.utils.ResourceLocationHelper;
import disc.mods.factions.entity.EntityBlacksmith;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.References;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderHumanoid extends RenderLivingBase<EntityLivingAI>
{
    float scale = 0.9375F;

    public RenderHumanoid(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPlayer(0.0f, true), 0.5f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerArrow(this));
        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
        this.addLayer(new LayerElytra(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLivingAI entity)
    {
        return entity.getTexture();
    }

    public ModelPlayer getMainModel()
    {
        return (ModelPlayer) super.getMainModel();
    }

    @Override
    protected void preRenderCallback(EntityLivingAI entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(scale, scale, scale);
    }

    @Override
    protected boolean canRenderName(EntityLivingAI entity)
    {
        return false;
    }
}
