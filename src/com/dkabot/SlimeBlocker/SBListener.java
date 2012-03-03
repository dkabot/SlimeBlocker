package com.dkabot.SlimeBlocker;

import org.bukkit.entity.CreatureType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class SBListener implements Listener {
	public SlimeBlocker plugin;
	public  SBListener (SlimeBlocker plugin) {
	    this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler(ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.isCancelled() | event.getCreatureType() != CreatureType.SLIME) return;
		Persistance SBLClass = plugin.getDatabase().find(Persistance.class).where().ieq("chunk", event.getLocation().getChunk().toString()).findUnique();
		if(SBLClass == null) return;
		else event.setCancelled(true);
	}
}
