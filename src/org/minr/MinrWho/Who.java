package org.minr.MinrWho;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class Who extends JavaPlugin {

    protected long whoInterval;

    protected boolean enabled;

    private WhoThread whoThread;

    private Logger logger;

    public Who() {
        super();

        whoThread = new WhoThread(this);
    }

    public void onEnable() {
        logger = getServer().getLogger();

        // Load configuration.
        reloadConfiguration();

        // Register the schedule.
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler
            .scheduleSyncRepeatingTask(this, whoThread, whoInterval * 20, whoInterval * 20);

        // Logging.
        logger.info(String.format("%s is enabled!\n", getDescription().getFullName()));
    }

    public void onDisable() {
        // Logging.
        logger.info(String.format("%s is disabled!\n", getDescription().getFullName()));
    }

    public void whoisonline() {
        whoThread.run();
    }

    public void whoisonline(String line) {
      	if (getServer().getOnlinePlayers().length > 0) {
      		String plist = "ZERO=zero.minr.org,25565,60,3600,";
      		String prank = ",";
           	Player[] list = getServer().getOnlinePlayers();
        	for(Player p : list){
        		plist = plist + p.getName() + "|";
        		prank = prank + "|";
        	}
          	String pupdate = plist + prank + ",on";
          	System.out.println("[MinrWho] : " + pupdate);
          	logToFile(pupdate);
        }
    }

    public void reloadConfiguration() {
        reloadConfig();
        whoInterval = getConfig().getInt("interval", 1000);
        enabled = getConfig().getBoolean("enabled", true);
    }

    public long getWhoInterval() {
        return whoInterval;
    }

    public boolean isWhoEnabled() {
        return enabled;
    }

    public void logToFile(String message) {
    	try
        {
            File dataFolder = getDataFolder();
            if(!dataFolder.exists())
            {
                dataFolder.mkdir();
            }
 
            File saveTo = new File(getDataFolder(), "data.txt");
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }
  
            FileWriter fw = new FileWriter(saveTo, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.flush();
            pw.close();
 
        } catch (IOException e)
        {
        	e.printStackTrace();
        }
 
    }    
    
}