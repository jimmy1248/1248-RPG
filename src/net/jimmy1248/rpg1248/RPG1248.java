package net.jimmy1248.rpg1248;

import org.bukkit.plugin.java.JavaPlugin;

public class RPG1248 extends JavaPlugin{
	@Override
	public void onEnable() {
		new CommandListener(this);
		new PlayerListener(this);
		
	}
}
