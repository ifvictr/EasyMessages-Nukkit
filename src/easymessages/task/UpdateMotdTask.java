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
            .replaceAll("\\{SERVER_DEFAULT_LEVEL}", this.plugin.getServer().getDefaultLevel().getName())
            .replaceAll("\\{SERVER_MAX_PLAYER_COUNT}", Integer.toString(this.plugin.getServer().getMaxPlayers()))
            .replaceAll("\\{SERVER_PLAYER_COUNT}", Integer.toString(this.plugin.getServer().getOnlinePlayers().size()))
            .replaceAll("\\{SERVER_NAME}", this.plugin.getServer().getMotd())
            .replaceAll("\\{SERVER_PORT}", Integer.toString(this.plugin.getServer().getPort()))
            .replaceAll("\\{SERVER_TPS}", Float.toString(this.plugin.getServer().getTicksPerSecond()))
        );
    }
}