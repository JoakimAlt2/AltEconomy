package ru.altspace;

import org.bukkit.configuration.file.FileConfiguration;

public class Language {

    public static String df = getDefaultLanguage();
    private static FileConfiguration cfg = AltEconomy.INSTANCE.getConfig();
    private static String getDefaultLanguage () { return  cfg.getString("Language.default"); }

    public static String getTCurCheckExeption () { return cfg.getString("Language." + df + ".CurCheckExeption"); }
    public static String getTPlayerIsNotOnline () { return cfg.getString("Language." + df + ".PlayerIsNotOnline"); }
    public static String getTCurList () { return  cfg.getString("Language." + df + ".CurList"); }
    public static String getTUse () { return cfg.getString("Language." + df + ".Use"); }

    public static String getPlayerBalance (int nm) {
        if(nm == 0) {
            return cfg.getString("Language." + df + ".PlayerBalance.another_player");
        } else if(nm == 1) {
            return cfg.getString("Language." + df + ".PlayerBalance.you");
        }
        return "%LanguageExeption%";
    }
}
