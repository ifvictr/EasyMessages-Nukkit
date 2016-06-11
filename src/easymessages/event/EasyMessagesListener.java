package easymessages.event;

import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import easymessages.utils.Utils;
import easymessages.EasyMessages;

public class EasyMessagesListener implements Listener{
    private EasyMessages plugin;
    public EasyMessagesListener(EasyMessages plugin){
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(PlayerChatEvent event){
        if(!plugin.getConfig().getSection("chat").getBoolean("color", true) && !event.getPlayer().hasPermission("easymessages.action.color")){
            event.setMessage(Utils.replaceSymbols(event.getMessage(), true));
        }
    }
}