package easymessages.task;

import cn.nukkit.scheduler.PluginTask;
import easymessages.EasyMessages;

public class UpdateMotdTask extends PluginTask<EasyMessages>{
    private EasyMessages plugin;
    public UpdateMotdTask(EasyMessages plugin){
        super(plugin);
        this.plugin = plugin;
    }
    @Override
    public void onRun(int currentTick){
        this.plugin.getServer().getNetwork().setName(this.plugin.getConfig().getSection("motd").getString("dynamicMotd")
            .replaceAll("\\{SERVER_API_VERSION}", this.plugin.getServer().getApiVersion())
            .replaceAll("\\{SERVER_API_VERSION}", this.plugin.getServer().getCodename())
            .replaceAll("\\{SERVER_DEFAULT_LEVEL}", this.plugin.getServer().getDefaultLevel().getName())
            .replaceAll("\\{SERVER_IP}", this.plugin.getServer().getIp())
            .replaceAll("\\{SERVER_LANGUAGE}", this.plugin.getServer().getLanguage().getName())
            .replaceAll("\\{SERVER_MAX_PLAYER_COUNT}", Integer.toString(this.plugin.getServer().getMaxPlayers()))
            .replaceAll("\\{SERVER_MOTD}", this.plugin.getServer().getMotd())
            .replaceAll("\\{SERVER_NAME}", this.plugin.getServer().getName())
            .replaceAll("\\{SERVER_NUKKIT_VERSION}", this.plugin.getServer().getNukkitVersion())
            .replaceAll("\\{SERVER_PLAYER_COUNT}", Integer.toString(this.plugin.getServer().getOnlinePlayers().size()))
            .replaceAll("\\{SERVER_NUKKIT_VERSION}", this.plugin.getServer().getNukkitVersion())
            .replaceAll("\\{SERVER_PORT}", Integer.toString(this.plugin.getServer().getPort()))
            .replaceAll("\\{SERVER_TICK_USAGE}", Float.toString(this.plugin.getServer().getTickUsage()))
            .replaceAll("\\{SERVER_TICK_USAGE_AVERAGE}", Float.toString(this.plugin.getServer().getTickUsageAverage()))
            .replaceAll("\\{SERVER_TPS}", Float.toString(this.plugin.getServer().getTicksPerSecond()))
            .replaceAll("\\{SERVER_TPS_AVERAGE}", Float.toString(this.plugin.getServer().getTicksPerSecondAverage()))
            .replaceAll("\\{SERVER_VERSION}", this.plugin.getServer().getVersion())
        );
    }
}