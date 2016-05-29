package easymessages.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.Player;
import easymessages.utils.Utils;
import easymessages.EasyMessages;
import java.util.Arrays;
import java.util.HashMap;

public class EasyMessagesCommand extends Command{
    private EasyMessages plugin;
    public EasyMessagesCommand(EasyMessages plugin){
        super("easymessages", "Shows all EasyMessages commands", null, new String[]{"em"});
        this.setPermission("easymessages.command.easymessages");
        this.plugin = plugin;
    }
    private void sendCommandHelp(CommandSender sender){
        HashMap<String, String> commands = new HashMap<>();
        commands.put("help", "Shows all EasyMessages commands");
        commands.put("message", "Sends a message");
        commands.put("motd", "Sets the server motd");
        commands.put("popup", "Sends a popup");
        sender.sendMessage("EasyMessages commands:");
        for(String name : commands.keySet()){
            sender.sendMessage("/easymessages "+name+": "+commands.get(name));
        }
    }
    @Override
    public boolean execute(CommandSender sender, String label, String[] args){
        if(!this.testPermission(sender)){
            return false;
        }
        if(args.length > 0){
            switch(args[0].toLowerCase()){
                case "help":
                    this.sendCommandHelp(sender);
                    break;
                case "m":
                case "message":
                    if(args.length > 1){
                        String message = Utils.replaceSymbols(String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
                        if(args[1].equalsIgnoreCase("@all")){
                            sender.getServer().broadcastMessage(message);
                            sender.sendMessage(TextFormat.GREEN+"Sent message to @all.");
                        }
                        else if(sender.getServer().getPlayer(args[1]) != null){
                            Player player = sender.getServer().getPlayer(args[1]);
                            player.sendMessage(message);
                            sender.sendMessage(TextFormat.GREEN+"Sent message to "+player.getName()+".");
                        }
                        else{
                            sender.sendMessage(TextFormat.RED+"Failed to send message due to invalid recipient(s) specified.");
                        }
                    }
                    else{
                        sender.sendMessage(TextFormat.RED+"Please specify a recipient.");
                    }
                    break;
                case "motd":
                    if(args.length > 1){
                        String motd = Utils.replaceSymbols(String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
                        sender.getServer().getNetwork().setName(motd);
                        sender.sendMessage(TextFormat.GREEN+"Set server motd to: "+TextFormat.RESET+motd+".");
                    }
                    else{
                        sender.sendMessage(TextFormat.GREEN+"Current server motd: "+TextFormat.RESET+sender.getServer().getNetwork().getName());
                    }
                    break;
                case "p":
                case "popup":
                    if(args.length > 1){
                        String popup = Utils.replaceSymbols(String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
                        if(args[1].equalsIgnoreCase("@all")){
                            this.plugin.broadcastPopup(popup);
                            sender.sendMessage(TextFormat.GREEN+"Sent popup to @all.");
                        }
                        else if(sender.getServer().getPlayer(args[1]) != null){
                            Player player = sender.getServer().getPlayer(args[1]);
                            player.sendPopup(popup);
                            sender.sendMessage(TextFormat.GREEN+"Sent popup to "+player.getName()+".");
                        }
                        else{
                            sender.sendMessage(TextFormat.RED+"Failed to send popup due to invalid recipient(s) specified.");
                        }
                    }
                    else{
                        sender.sendMessage(TextFormat.RED+"Please specify a recipient.");
                    }
                    break;
                default:
                    sender.sendMessage("Usage: /easymessages <sub-command> [parameters]");
                    break;
            }
        }
        else{
            this.sendCommandHelp(sender);
            return false;
        }
        return true;
    }
}
