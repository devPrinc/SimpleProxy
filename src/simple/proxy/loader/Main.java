package simple.proxy.loader;

import simple.proxy.managers.CommandManager;

import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    private static Main i;

    public Main() {
        i = this;
    }

    public static Main getInstance() {
        return i;
    }

    public void onEnable() {
        new CommandManager(this);
    }

    public void onDisable() {
    }
}
