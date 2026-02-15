package ru.akydev;

import org.bukkit.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
    
    private static final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
    
    public static String colorize(String message) {
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer();
        
        while (matcher.find()) {
            String color = matcher.group(1);
            try {
                java.lang.reflect.Method ofMethod = ChatColor.class.getMethod("of", String.class);
                ChatColor hexColor = (ChatColor) ofMethod.invoke(null, "#" + color);
                matcher.appendReplacement(buffer, hexColor.toString());
            } catch (Exception e) {
                matcher.appendReplacement(buffer, "&" + matcher.group(0));
            }
        }
        matcher.appendTail(buffer);
        
        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }
}
