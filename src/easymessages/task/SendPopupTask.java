package easymessages.task;

import cn.nukkit.scheduler.PluginTask;
import easymessages.utils.Utils;
import easymessages.EasyMessages;

public class SendPopupTask extends PluginTask<EasyMessages>{
    private EasyMessages plugin;
    private String type;
    public SendPopupTask(EasyMessages plugin, String type){
        super(plugin);
        this.plugin = plugin;
        this.type = type.toLowerCase();
    }
    @Override
    public void onRun(int currentTick){
        switch(type){
            case "auto":
                plugin.broadcastPopup(Utils.getRandom(plugin.getConfig().getSection("popup").getStringList("autoMessages")));
                break;
            case "blinking":
                plugin.broadcastPopup(plugin.getConfig().getSection("popup").getString("blinkingMessage"));
                break;
            case "infinite":
                plugin.broadcastPopup(plugin.getConfig().getSection("popup").getString("infiniteMessage"));
                break;
            case "scrolling":
                String popup = plugin.getScrollingPopup();
                plugin.broadcastPopup(popup);
                plugin.setScrollingPopup(Utils.next(popup));
                break;
            default:
                //For some reason the task doesn't cancel, I'll comment out the log message to prevent console spam
                //plugin.getServer().getLogger().notice("Invalid type set in popup.displayType, stopping task...");
                plugin.getServer().getScheduler().cancelTask(getTaskId());
                break;
        }
    }
}