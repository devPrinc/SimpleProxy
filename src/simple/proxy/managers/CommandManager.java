package simple.proxy.managers;

import simple.proxy.commands.LobbyCommand;
import simple.proxy.loader.Main;
import simple.proxy.commands.OnlineCommand;

public class CommandManager {
    private LobbyCommand lobby = new LobbyCommand();
    private OnlineCommand online = new OnlineCommand();

    public CommandManager(Main m) {
        Main.getInstance().getProxy().getPluginManager().registerCommand(m, this.lobby);
        Main.getInstance().getProxy().getPluginManager().registerCommand(m, this.online);
    }
}