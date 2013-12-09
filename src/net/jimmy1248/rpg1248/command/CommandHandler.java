package net.jimmy1248.rpg1248.command;

import java.util.HashMap;

import org.bukkit.command.CommandSender;

import net.jimmy1248.rpg1248.RPG1248;

public class CommandHandler {
	RPG1248 plugin;
	public CommandHandler(RPG1248 plugin){
		this.plugin = plugin;
	}	

	@Command(name = "clan",
			sub = "<a>")
	public void a(CommandSender sender,HashMap<String, String> args){
		sender.sendMessage(args.get("funca"));
	}
	
	@Command(name = "clan",
			sub = "a <a>")
	public void b(CommandSender sender,HashMap<String, String> args){
		sender.sendMessage("funcb");
	}
}
