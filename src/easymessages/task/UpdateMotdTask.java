package easymessages.task;

import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.Server;
import easymessages.EasyMessages;

public class UpdateMotdTask extends PluginTask<EasyMessages>{
    private EasyMessages plugin;
    public UpdateMotdTask(EasyMessages plugin){
        super(plugin);
        this.plugin = plugin;
    }
    @Override
    public void onRun(int currentTick){
        Server server = plugin.getServer();
        server.getNetwork().setName(plugin.getConfig().getSection("motd").getString("dynamicMotd")
            .replaceAll("\\{SERVER_API_VERSION}", server.getApiVersion())
            .replaceAll("\\{SERVER_API_VERSION}", server.getCodename())
            .replaceAll("\\{SERVER_DEFAULT_LEVEL}", server.getDefaultLevel().getName())
            .replaceAll("\\{SERVER_IP}", server.getIp())
            .replaceAll("\\{SERVER_LANGUAGE}", server.getLanguage().getName())
            .replaceAll("\\{SERVER_MAX_PLAYER_COUNT}", Integer.toString(server.getMaxPlayers()))
            .replaceAll("\\{SERVER_MOTD}", server.getMotd())
            .replaceAll("\\{SERVER_NAME}", server.getName())
            .replaceAll("\\{SERVER_NUKKIT_VERSION}", server.getNukkitVersion())
            .replaceAll("\\{SERVER_PLAYER_COUNT}", Integer.toString(server.getOnlinePlayers().size()))
            .replaceAll("\\{SERVER_NUKKIT_VERSION}", server.getNukkitVersion())
            .replaceAll("\\{SERVER_PORT}", Integer.toString(server.getPort()))
            .replaceAll("\\{SERVER_TICK_USAGE}", Float.toString(server.getTickUsage()))
            .replaceAll("\\{SERVER_TICK_USAGE_AVERAGE}", Float.toString(server.getTickUsageAverage()))
            .replaceAll("\\{SERVER_TPS}", Float.toString(server.getTicksPerSecond()))
            .replaceAll("\\{SERVER_TPS_AVERAGE}", Float.toString(server.getTicksPerSecondAverage()))
            .replaceAll("\\{SERVER_VERSION}", server.getVersion())
        );
    }
}