package me.ZombiDud.helloworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.ZombiDud.helloworld.Main;

public class HelloCommand implements CommandExecutor, Listener{

		private Main plugin;
		
		public HelloCommand(Main plugin)
		{
			this.plugin = plugin;
			
			plugin.getCommand("hello").setExecutor(this);
		}

		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
		{
			System.out.println("Hello command issued");
			if (!(sender instanceof Player))
			{
				sender.sendMessage("Only players may execute this command!");
				return true;
			}
			
			if (cmd.getName().equalsIgnoreCase("hello"))
			{
				plugin.getCommand("hello").setExecutor(this);
				Player p = (Player) sender;
				
				if (p.hasPermission("hello.use"))
				{
					p.sendMessage("hi!");
					return true;
				}
				else
				{
					p.sendMessage("You are not properly permissed to perform this command dear sir!1!");
					return true;
				}
			}
			
			return false;
		}
		
		
}
