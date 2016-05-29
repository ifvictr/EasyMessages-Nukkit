package easymessages;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.Player;
import easymessages.command.EasyMessagesCommand;
import easymessages.event.EasyMessagesListener;
import easymessages.task.SendMessageTask;
import easymessages.task.SendPopupTask;
import easymessages.task.UpdateMotdTask;
import easymessages.utils.Utils;

public class EasyMessages extends PluginBase{
    private String scrollingPopup = "";
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        this.saveResource("values.txt");
    	this.getServer().getCommandMap().register("easymessages", new EasyMessagesCommand(this));
    	this.getServer().getPluginManager().registerEvents(new EasyMessagesListener(this), this);
        if(this.getConfig().getSection("message").getBoolean("autoBroadcast")){
            this.getServer().getScheduler().scheduleRepeatingTask(new SendMessageTask(this), (this.getConfig().getSection("message").getInt("interval") * 20));
        }
        String pType = this.getConfig().getSection("popup").getString("displayType");
        this.getServer().getScheduler().scheduleRepeatingTask(new SendPopupTask(this, pType), Utils.getInterval(pType, this.getConfig().getSection("popup").getInt("interval")));
        if(this.getConfig().getSection("motd").getString("displayType").equalsIgnoreCase("dynamic")){
            this.getServer().getScheduler().scheduleRepeatingTask(new UpdateMotdTask(this), (this.getConfig().getSection("motd").getInt("interval") * 20));
        }
        else{
            this.getServer().getNetwork().setName(this.getConfig().getSection("motd").getString("staticMotd"));
        }
    }
    public void broadcastPopup(String message){
        for(Player player : this.getServer().getOnlinePlayers().values()){
            player.sendPopup(message);
        }
    }
    public String getScrollingPopup(){
        return this.scrollingPopup;
    }
    public void setScrollingPopup(String message){
        this.scrollingPopup = message;
    }
}