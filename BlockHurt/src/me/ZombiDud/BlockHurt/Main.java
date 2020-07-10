package me.ZombiDud.BlockHurt;

import org.bukkit.plugin.java.JavaPlugin;

import me.ZombiDud.BlockHurt.BlockBreakListener.BlockBreakListener;


public class Main extends JavaPlugin{
	@Override
	public void onEnable()
	{
		getCommand("BlockHurt").setExecutor(new BlockBreakListener(this));
	}
}
