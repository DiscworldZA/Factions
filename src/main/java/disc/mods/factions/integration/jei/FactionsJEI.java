package disc.mods.factions.integration.jei;

import disc.mods.factions.ref.FactionsSettings;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.ContainerWorkbench;

@JEIPlugin
public class FactionsJEI extends BlankModPlugin
{
    private static FactionsJEI instance;

    public static FactionsJEI GetInstance()
    {
        return instance;
    }

    private IJeiRuntime runtime;

    @Override
    public void register(IModRegistry registry)
    {
        instance = this;
        if (FactionsSettings.Edit.ExportJsonJEI.Value)
        {
            registry.getRecipeTransferRegistry().addRecipeTransferHandler(new JsonRecipeExportHandler(ContainerWorkbench.class), VanillaRecipeCategoryUid.CRAFTING);
            registry.getRecipeTransferRegistry().addRecipeTransferHandler(new JsonRecipeExportHandler(ContainerPlayer.class), VanillaRecipeCategoryUid.CRAFTING);
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime)
    {
        this.runtime = runtime;
    }

    public IJeiRuntime getRuntime()
    {
        return runtime;
    }
}
