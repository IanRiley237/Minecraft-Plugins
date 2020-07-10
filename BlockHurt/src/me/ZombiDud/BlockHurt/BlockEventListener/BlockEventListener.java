package me.ZombiDud.BlockHurt.BlockEventListener;

import me.ZombiDud.BlockHurt.Main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BlockEventListener implements CommandExecutor, Listener{

	int defaultBreakBlockDamageAmount = 1;
	int defaultPlaceBlockDamageAmount = 1;

	// Sets to store player names who have a certain effect on them
	HashMap<String, Integer> breakBlock = new HashMap<String, Integer>();
	HashMap<String, Integer> placeBlock = new HashMap<String, Integer>();
	
	byte deathcause = 0;
	// 0: Not
	// 1: Break
	// 2: Place

	public BlockEventListener(Main plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
    public void onBlockBreakEvent(final BlockBreakEvent e)
    {
		Player p = e.getPlayer();

		if(breakBlock.containsKey(p.getName()))
		{
			if (p.getHealth() <= breakBlock.get(p.getName()))
				deathcause = 1;
			p.damage(breakBlock.get(p.getName()));
		}
    }
	@EventHandler
    public void onBlockPlaceEvent(final BlockPlaceEvent e)
    {
		Player p = e.getPlayer();
		
		if(placeBlock.containsKey(p.getName()))
		{
			if (p.getHealth() <= placeBlock.get(p.getName()))
				deathcause = 2;
			p.damage(placeBlock.get(p.getName()));
		}
    }
	@EventHandler
    public void PlayerDeathEvent(final PlayerDeathEvent e)
    {
		if (deathcause == 1)
		{
			e.setDeathMessage(e.getEntity().getDisplayName() + " broke too many blocks.");
		}
		else if (deathcause == 2)
		{
			e.setDeathMessage(e.getEntity().getDisplayName() + " placed too many blocks.");
		}
		deathcause = 0;
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player && !sender.isOp())
		{
			sender.sendMessage("You do not have permission to use this command. (You are not an Op)");
			return true;
		}
		Player p;
		int damage = 0;
		if (cmd.getName().equalsIgnoreCase("BlockHurt"))
		{
			if(args.length == 0)
			{
				sender.sendMessage("Please indicate \"BlockHurt Break\" or \"BlockHurt Place\"");
				return false;
			}
			else
			{
				
				// Set player
				if(!(sender instanceof Player) && args.length <= 1)
				{
					sender.sendMessage("You must specify a player.");
					return false;
				}
				else if (args.length <= 1)
				{
					p = (Player) sender;
				}
				else
				{
					p = Bukkit.getPlayerExact(args[1]);
					if (p == null)
					{
						sender.sendMessage(args[1] + " does not exist.");
						return false;
					}
				}
				
				// Set damage
				if (args.length >= 3)
					damage =  Integer.parseInt(args[2]);
				
				switch(args[0].toLowerCase())
				{
					case "break":
						if (args.length < 3)
							damage = defaultBreakBlockDamageAmount;
						if (damage <= 0)
						{
							breakBlock.remove(p.getDisplayName());
							sender.sendMessage(p.getDisplayName() + " will now recieve no damage when they break blocks.");
						}
						else
						{
							breakBlock.put(p.getDisplayName(), damage);
							sender.sendMessage(p.getDisplayName() + " will now recieve " + damage + " damage when they break blocks.");
						}
						return true;
					case "place":
						if (args.length < 3)
							damage = defaultPlaceBlockDamageAmount;
						if (damage <= 0)
						{
							placeBlock.remove(p.getDisplayName());
							sender.sendMessage(p.getDisplayName() + " will now recieve no damage when they place blocks.");
						}
						else
						{
							placeBlock.put(p.getDisplayName(), damage);
							sender.sendMessage(p.getDisplayName() + " will now recieve " + damage + " damage when they place blocks.");
						}
						return true;
					default:
						sender.sendMessage("Please indicate \"BlockHurt Break\" or \"BlockHurt Place\"");
						return false;
				}
			}
			
		}
		return false;
	}
}