package me.ZombiDud.BlockHurt;

import org.bukkit.plugin.java.JavaPlugin;

import me.ZombiDud.BlockHurt.BlockEventListener.BlockEventListener;


public class Main extends JavaPlugin{
	@Override
	public void onEnable()
	{
		getCommand("BlockHurt").setExecutor(new BlockEventListener(this));
	}
}
