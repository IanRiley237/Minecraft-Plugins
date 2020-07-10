package me.ZombiDud.FireballWand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;




public class FireballWand extends JavaPlugin implements Listener
{
	static final String wandName = "Wand of Fireballs";
	@Override
	public void onEnable()
	{
		ItemStack wand = new ItemStack(Material.STICK);
		ItemMeta wandMeta = wand.getItemMeta();
		
		wandMeta.setDisplayName(wandName);
		wand.setItemMeta(wandMeta);
		
		NamespacedKey key = new NamespacedKey(this, "wand_of_fireballs");
		
		ShapedRecipe recipe = new ShapedRecipe(key, wand);


		// M = Magma cream, S = Stick
		recipe.shape(" M ", " S ", " S ");
		recipe.setIngredient('M', Material.MAGMA_CREAM);
		recipe.setIngredient('S', Material.STICK);
		Bukkit.addRecipe(recipe);
		
	    getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void fireball(PlayerInteractEvent e)
	{
	    Player player = e.getPlayer();
	    Action action = e.getAction();
	    ItemStack item = e.getItem(); 
	    
	     if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))
	     {
	         if ( item != null && item.getItemMeta().getDisplayName().equals(wandName))
	         {
	             Location pLocation = player.getLocation();
	             pLocation.getWorld().spawnEntity(pLocation.add(pLocation.getDirection()).add(new Vector(0, 1.3, 0)), EntityType.FIREBALL);
	         } 
	     }

	}
	
	@Override
	public void onDisable()
	{
		
	}
}