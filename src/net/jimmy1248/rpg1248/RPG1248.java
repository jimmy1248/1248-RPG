package net.jimmy1248.rpg1248;

import net.jimmy1248.rpg1248.command.CommandHandler;
import net.jimmy1248.rpg1248.command.CommandListener;

import org.bukkit.plugin.java.JavaPlugin;

public class RPG1248 extends JavaPlugin{
	CommandListener handler;
	@Override
	public void onEnable() {
		handler = new CommandListener(this,new CommandHandler(this));
	    			
		//new PlayerListener(this);
		getLogger().info("1248 RPG loaded.");
		
	}
	
}
