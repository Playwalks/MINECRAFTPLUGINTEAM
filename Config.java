package us.someteamname.tabedit;

import java.io.File;

public class Config {

public void createConfig() {
	try {
		if (!tab.pl.getDataFolder().exists())
			tab.pl.getDataFolder().mkdirs();
		File file = new File(tab.pl.getDataFolder(), "config.yml");
		if (!file.exists()) {
			tab.pl.getLogger().info("Error!: No config found... Making one!");
		} else {
			tab.pl.getLogger().info("Config found!");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
