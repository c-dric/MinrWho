package org.minr.MinrWho;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Who extends JavaPlugin {
	/**
	 * The default minecraft log
	 */
	public static Logger msLog = Logger.getLogger("Minecraft");

	public static final String DATE_FORMAT_NOW = "dd-MM-yyy HH:mm:ss";

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	@Override
	public void onEnable() {
		writeLog("Plugin has been enabled", false);
	}

	@Override
	public void onDisable() {
		writeLog("Plugin has been disabled", false);
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("log")) {
			// Not using permissions for the moment
			// if(sender.hasPermission("LogCommand.log")) {
			// Write the message following the command to the log
			writeLog(join(args, " "), false);
			// }else{
			// sender.sendMessage("You do not have the correct permissions to execute this command.");
			// }
			return true;
		}

		// Show the usage info for this plugin
		return false;
	}

	public static String join(String[] inputArray, String delimiter) {
		if (inputArray.length == 0)
			return "";
		StringBuffer buffer = new StringBuffer(inputArray[0]);
		for (int i = 1; i < inputArray.length; ++i)
			buffer.append(delimiter).append(inputArray[i]);
		return buffer.toString();
	}

	private void writeLog(String msg, boolean addTimeStamp) {
		String logPrefix = "[LogCommand] ";
		if (addTimeStamp) logPrefix += "(" + now() +") ";
		msLog.info(logPrefix + msg);
	}
}
