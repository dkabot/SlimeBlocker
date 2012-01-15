package com.dkabot.SlimeBlocker;

import org.bukkit.entity.CreatureType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityListener;

public class SBListener extends EntityListener {
	public SlimeBlocker plugin;
	public  SBListener (SlimeBlocker plugin) {
	    this.plugin = plugin;
	}
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.isCancelled() | event.getCreatureType() != CreatureType.SLIME) return;
		Persistance SBLClass = plugin.getDatabase().find(Persistance.class).where().ieq("chunk", event.getLocation().getChunk().toString()).findUnique();
		if(SBLClass == null) return;
		else event.setCancelled(true);
	}
}
