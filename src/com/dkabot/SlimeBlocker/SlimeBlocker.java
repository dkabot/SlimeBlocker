package com.dkabot.SlimeBlocker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimeBlocker extends JavaPlugin {
	private Command Com;
	Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		setupDatabase();
		Com = new Command(this);
		getCommand("slimeblocker").setExecutor(Com);
		EntityListener elistener = new SBListener(this);
	    this.getServer().getPluginManager().registerEvent(Event.Type.CREATURE_SPAWN,elistener, Event.Priority.Low, this);
		this.log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled.");
	}
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info(pdfFile.getName() + " is now disabled.");
	}
    private void setupDatabase() {
        try {
            getDatabase().find(Persistance.class).findRowCount();
        } catch (PersistenceException ex) {
            this.log.info("Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
    }
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Persistance.class);
        return list;
    }
}
