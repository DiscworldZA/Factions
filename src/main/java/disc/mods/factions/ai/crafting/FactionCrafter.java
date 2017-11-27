package disc.mods.factions.ai.crafting;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import disc.mods.core.util.FileIOHelper;
import disc.mods.factions.ai.crafting.recipes.JsonRecipe;
import disc.mods.factions.ai.crafting.recipes.JsonRecipeFactory;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.References;

public class FactionCrafter
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
            List<JsonRecipe> jsonRecipes = JsonRecipeFactory.fromListJson(json);
            for (JsonRecipe recipe : jsonRecipes)
            {
                recipes.put(recipe.result, recipe);
            }
        }
    }

    public boolean hasRecipe(String Id)
    {
        return recipes.containsKey(Id);
    }
    
    public JsonRecipe getRecipe(String Id)
    {
        return recipes.get(Id);
    }
}
