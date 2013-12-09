package net.jimmy1248.rpg1248.command;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandListener implements CommandExecutor{
	@SuppressWarnings("unused")
	private JavaPlugin plugin;
	private Object runner;
	private HashMap<String, LinkedList<Method>> methods = new HashMap<String ,LinkedList<Method>>();
	public CommandListener(JavaPlugin plugin,CommandHandler runner){
		this.plugin = plugin;
		this.runner = runner;
		for (Method method : runner.getClass().getDeclaredMethods()) {
            net.jimmy1248.rpg1248.command.Command annos = method.getAnnotation(net.jimmy1248.rpg1248.command.Command.class);
            if(annos != null && annos.name() != ""){
            	String name = annos.name().toLowerCase();
              	plugin.getCommand(name).setExecutor(this);
              	LinkedList<Method> m = methods.get(name);
              	if(m == null) m = new LinkedList<Method>();
              	m.add(method);
              	methods.put(name, m);
            }	
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		LinkedList<Method> m = methods.get(cmd.getName().toLowerCase());
		if(m == null) return false;
	
		for(Method method : m){
			HashMap<String, String> a = new HashMap<String,String>();
	        net.jimmy1248.rpg1248.command.Command annos = method.getAnnotation(net.jimmy1248.rpg1248.command.Command.class);
	        String[] sub = annos.sub().split(" ");
	        sender.sendMessage(sub);
	        for(int i = 0;i<args.length;i++){
	        	if(sub[i] == null) break;
	        	if(sub[i].startsWith("<")){
	        		//sender.sendMessage(sub[i]);
	        		a.put(sub[i].replace("<", "").replace(">", ""),args[i]);
	        		continue;
	        	}
	        	if(sub[i].startsWith("[") && sub[i].endsWith("]")){
	        		sub[i].replace("[", "");
	        		sub[i].replace("]", "");
	        		a.put(sub[i],args[i]);
	        		continue;
	        	}
	        
	        	
	        	if(args[i] != sub[i]) break;
	        	
	        }
	        if(annos.playeronly() && !(sender instanceof Player)){
	        	sender.sendMessage("This command can only be run by a player");
	        	return true;
	        }
	        if(sender.hasPermission(annos.permission())){
	        	try {
	        		method.invoke(runner,sender,a);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
	        }else sender.sendMessage("You do not have permission " + annos.permission());
	        return true;
		}
		return false;
	}
}
