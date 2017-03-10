package disc.mods.factions.ref;

import disc.mods.core.config.ConfigBoolean;
import disc.mods.core.config.ConfigCategory;
import disc.mods.core.config.ConfigFloat;
import disc.mods.core.config.ConfigProperty;
import net.minecraftforge.common.config.Configuration;

public class FactionsSettings
{
    public static class AI
    {
        public static ConfigCategory AI = new ConfigCategory("AI");
        public static ConfigProperty<Float> AIMovementSpeed = new ConfigFloat("AIMovementSpeed", 0.42f, AI).SetComment("Movement Speed of the AI Entities");
        public static ConfigProperty<Boolean> CanAIMove = new ConfigBoolean("CanAiMove", true, AI).SetComment("Allows AI to Move");

        public static void Load(Configuration config)
        {
            AIMovementSpeed.load(config);
            CanAIMove.load(config);
        }
    }

    public static class Edit
    {
        public static ConfigCategory Edit = new ConfigCategory("Edit");
        public static ConfigProperty<Boolean> ExportJsonJEI = new ConfigBoolean("JEIJsonExport", false, Edit).SetComment("Allow JEI to export Json recipe?");

        public static void Load(Configuration config)
        {
            ExportJsonJEI.load(config);
        }
    }

    public static void Load(Configuration config)
    {
        AI.Load(config);
        Edit.Load(config);
    }

}
