package disc.mods.factions.ai.crafting;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import disc.mods.core.utils.FileIOHelper;
import disc.mods.factions.ai.crafting.recipes.JsonRecipe;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.References;
import disc.mods.factions.registry.IRegistryProperty;

public class FactionCrafter implements IRegistryProperty
{
    private File jsonFile;

    private Map<String, JsonRecipe> recipes = new HashMap<String, JsonRecipe>();

    public FactionCrafter(Class<? extends EntityLivingAI> entityClass)
    {
        jsonFile = new File("config/" + References.Mod.Id + "/crafters/" + entityClass.getSimpleName().replace("Entity", "") + ".json");
        if (!jsonFile.exists())
        {
            FileIOHelper.CreateFile(jsonFile);
            FileIOHelper.WriteFile(jsonFile, "{}");
        }
        loadRecipes();
    }

    public void loadRecipes()
    {
        String json = FileIOHelper.ReadFile(jsonFile);
        if (!json.isEmpty())
        {
            List<JsonRecipe> jsonRecipes = JsonRecipe.fromListJson(json);
            for (JsonRecipe recipe : jsonRecipes)
            {
                recipes.put(recipe.result, recipe);
            }
        }
    }

    public boolean hasResult(String Id)
    {
        return recipes.containsKey(Id);
    }
}
