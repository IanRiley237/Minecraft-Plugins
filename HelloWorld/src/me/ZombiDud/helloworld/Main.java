package me.ZombiDud.helloworld;

import org.bukkit.plugin.java.JavaPlugin;

import me.ZombiDud.helloworld.commands.HelloCommand;

public class Main extends JavaPlugin{
	@Override
	public void onEnable()
	{
		new HelloCommand(this);
	}
}
