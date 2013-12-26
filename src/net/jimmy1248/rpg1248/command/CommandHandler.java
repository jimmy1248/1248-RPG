package net.jimmy1248.rpg1248.command;

import org.bukkit.command.CommandSender;

import net.jimmy1248.rpg1248.RPG1248;

public class CommandHandler {
	RPG1248 plugin;
	public CommandHandler(RPG1248 plugin){
		this.plugin = plugin;
	}	

	@Command(name = "clan")
	public void Clan(CommandSender sender,String[] args){
		if(args.length == 1) sender.sendMessage(args[0]);
	}
	

}
