package simple.proxy.commands;

import simple.proxy.loader.Main;

import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand
        extends Command {
    public static HashMap<String, Long> confirm = new HashMap();

    public LobbyCommand() {
        super("hub", "", new String[]{"lobby"});
    }

    public void execute(CommandSender sender, String[] args) {
        final ProxiedPlayer p = ProxyServer.getInstance().getPlayer(sender.getName());
        ServerInfo server = ProxyServer.getInstance().getServerInfo("Lobby");
        if (p.getServer().getInfo() == server) {
            sender.sendMessage("§cVocê já está no Lobby.");
            return;
        }
        if (!confirm.containsKey(sender.getName())) {
            confirm.put(sender.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5L));
            sender.sendMessage("§eVocê tem certeza? Digite §f§n/lobby§e novamente para voltar ao lobby§e.");
            Main.getInstance().getProxy().getScheduler().schedule(Main.getInstance(), new Runnable(){

                @Override
                public void run() {
                    confirm.remove(p.getName());
                }
            }, 10L, TimeUnit.SECONDS);
            return;
        }
        try {
            Socket s = new Socket(server.getAddress().getAddress().getHostAddress(), server.getAddress().getPort());
            s.close();
            p.connect(server);
            confirm.remove(p.getName());
            p.sendMessage("§aVocê está sendo conectado ao Lobby...");
        }
        catch (Exception e) {
            sender.sendMessage("§cAparentemente o servidor está inativo.");
            return;
        }
    }
}
