package easymessages.utils;

import cn.nukkit.utils.TextFormat;
import java.util.List;
import java.util.Random;

public class Utils{
    public static String next(String message){
        return message.substring(1)+message.substring(0, 1);
    }
    public static String replaceSymbols(String message){
        return Utils.replaceSymbols(message, false);
    }
    public static String replaceSymbols(String message, boolean revert){
        String[] defaultFormat = {
            TextFormat.BLACK,
            TextFormat.DARK_BLUE,
            TextFormat.DARK_GREEN,
            TextFormat.DARK_AQUA,
            TextFormat.DARK_RED,
            TextFormat.DARK_PURPLE,
            TextFormat.GOLD,
            TextFormat.GRAY,
            TextFormat.DARK_GRAY,
            TextFormat.BLUE,
            TextFormat.GREEN,
            TextFormat.AQUA,
            TextFormat.RED,
            TextFormat.LIGHT_PURPLE,
            TextFormat.YELLOW,
            TextFormat.WHITE,
            TextFormat.OBFUSCATED,
            TextFormat.BOLD,
            TextFormat.STRIKETHROUGH,
            TextFormat.UNDERLINE,
            TextFormat.ITALIC,
            TextFormat.RESET
        };
        String[] newFormat = {
            "&0", "&1", "&2", "&3",
            "&4", "&5", "&6", "&7",
            "&8", "&9", "&a", "&b",
            "&c", "&d", "&e", "&f",
            "&k", "&l", "&m", "&n",
            "&o", "&r"
        };
        if(revert){
            return TextFormat.clean(message);
        }
        else{
            for(int i = 0; i < newFormat.length; i++){
                message = message.replaceAll(newFormat[i], defaultFormat[i]);
            }
            return message;
        }
    }
    public static String getRandom(List<String> stack){
        return stack.get(new Random().nextInt(stack.size()));
    }
    public static int getInterval(String type, int interval){
        switch(type.toLowerCase()){
            case "auto":
                return interval * 20;
            case "blinking":
                return 30;
            case "infinite":
                return 7;
            case "scrolling":
                return 8;
            default:
                return 1;
        }
    }
}