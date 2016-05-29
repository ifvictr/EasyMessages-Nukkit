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
        switch(this.type){
            case "auto":
                this.plugin.broadcastPopup(Utils.getRandom(this.plugin.getConfig().getSection("popup").getStringList("autoMessages")));
                break;
            case "blinking":
                this.plugin.broadcastPopup(this.plugin.getConfig().getSection("popup").getString("blinkingMessage"));
                break;
            case "infinite":
                this.plugin.broadcastPopup(this.plugin.getConfig().getSection("popup").getString("infiniteMessage"));
                break;
            case "scrolling":
                String popup = this.plugin.getScrollingPopup();
                this.plugin.broadcastPopup(popup);
                this.plugin.setScrollingPopup(Utils.next(popup));
                break;
            default:
                //For some reason the task doesn't cancel, I'll comment out the log message to prevent console spam
                //this.plugin.getServer().getLogger().notice("Invalid type set in popup.displayType, stopping task...");
                this.plugin.getServer().getScheduler().cancelTask(this.getTaskId());
                break;
        }
    }
}