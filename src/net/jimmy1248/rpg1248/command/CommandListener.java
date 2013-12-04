package net.jimmy1248.rpg1248.command;

import java.lang.reflect.Method;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandListener implements CommandExecutor{
	@SuppressWarnings("unused")
	private JavaPlugin plugin;
	private Object runner;
	public CommandListener(JavaPlugin plugin,CommandHandler runner){
		this.plugin = plugin;
		this.runner = runner;
		plugin.getCommand("clan").setExecutor(this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		Method[] methods = runner.getClass().getMethods();
        for (Method method : methods) {
            net.jimmy1248.rpg1248.command.Command annos = method.getAnnotation(net.jimmy1248.rpg1248.command.Command.class);
            if (annos != null && annos.name().equalsIgnoreCase(cmd.getName())) {
                
            	try {
                    method.invoke(runner,sender,args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return true;
            }
        }
		
		
		return false;
	}
}
