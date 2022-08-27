package simple.proxy.commands;

import simple.proxy.loader.Main;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class OnlineCommand extends Command {
    public OnlineCommand() {
        super("online");
    }

    public void execute(CommandSender sender, String[] args) {
        int all = ProxyServer.getInstance().getOnlineCount();
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("");
            sender.sendMessage("§eJogadores em toda a rede: §f" + all);
            sender.sendMessage("");
            return;
        }
        int list = 0;
        sender.sendMessage("§eInformações sobre servidores e jogadores online.");
        sender.sendMessage("");
        sender.sendMessage("§r §6§l❤ §fTotal: §7" + all);
        sender.sendMessage("");
        for (final String servers : Main.getInstance().getProxy().getServers().keySet()) {
            final ServerInfo serverinfo = Main.getInstance().getProxy().getServers().get(servers);
            sender.sendMessage("§r §7■ §f" + serverinfo.getName().replaceAll("lobby", "Lobby #1").replaceAll("rankupop", "R. OP").replaceAll("login", "Login") + ": §7" + serverinfo.getPlayers().size());
            ++list;
        }
        if (list > 0) {
            sender.sendMessage("");

        }
    }
}
