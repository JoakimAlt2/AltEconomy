package ru.altspace;

public class Language {

    private AltEconomy altEconomy = new AltEconomy();
    public String df = getDefaultLanguage();

    private String getDefaultLanguage () { return  altEconomy.getConfig().getString("Language.default"); }
    public String getPlayerBalance (Integer nm) {
        if(nm == 0) {
            return altEconomy.getConfig().getString("Language." + df + ".PlayerBalance.another_player");
        } else if(nm == 1) {
            return altEconomy.getConfig().getString("Language." + df + ".PlayerBalance.you");
        }
        return "%LanguageExeption%";
    }
    public String getTCurCheckExeption () { return altEconomy.getConfig().getString("Language." + df + ".CurCheckExeption"); }
    public String getTPlayerIsNotOnline () { return altEconomy.getConfig().getString("Language." + df + ".PlayerIsNotOnline"); }
    public String getTCurList () { return  altEconomy.getConfig().getString("Language." + df + ".CurList"); }
    public String getTUse () { return altEconomy.getConfig().getString("Language." + df + ".Use"); }
}
