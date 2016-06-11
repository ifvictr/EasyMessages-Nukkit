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
    private String scrollingPopup;
    @Override
    public void onEnable(){
        saveDefaultConfig();
        saveResource("values.txt");
    	getServer().getCommandMap().register("easymessages", new EasyMessagesCommand(this));
    	getServer().getPluginManager().registerEvents(new EasyMessagesListener(this), this);
        if(getConfig().getSection("message").getBoolean("autoBroadcast")){
            getServer().getScheduler().scheduleRepeatingTask(new SendMessageTask(this), (getConfig().getSection("message").getInt("interval") * 20));
        }
        String pType = getConfig().getSection("popup").getString("displayType");
        getServer().getScheduler().scheduleRepeatingTask(new SendPopupTask(this, pType), Utils.getInterval(pType, getConfig().getSection("popup").getInt("interval")));
        if(getConfig().getSection("motd").getString("displayType").equalsIgnoreCase("dynamic")){
            getServer().getScheduler().scheduleRepeatingTask(new UpdateMotdTask(this), (getConfig().getSection("motd").getInt("interval") * 20));
        }
        else{
            getServer().getNetwork().setName(getConfig().getSection("motd").getString("staticMotd"));
        }
        setScrollingPopup(getConfig().getSection("popup").getString("scrollingMessage"));
    }
    public void broadcastPopup(String message){
        for(Player player : getServer().getOnlinePlayers().values()){
            player.sendPopup(message);
        }
    }
    public String getScrollingPopup(){
        return scrollingPopup;
    }
    public void setScrollingPopup(String message){
        this.scrollingPopup = message;
    }
}