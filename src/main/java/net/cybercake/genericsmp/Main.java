package net.cybercake.genericsmp;

import net.cybercake.cyberapi.common.builders.settings.Settings;
import net.cybercake.cyberapi.spigot.CyberAPI;
import net.cybercake.cyberapi.spigot.chat.Log;

public final class Main extends CyberAPI {

    @Override
    public void onEnable() {
        long mss = System.currentTimeMillis();
        startCyberAPI(
                Settings.builder()
                        .name("GenericSMP")
                        .prefix("GenericSMP")
                        .mainPackage("net.cybercake.genericsmp")
                        .build()
        );

        Log.info("Loaded " + this.getPluginMeta().getDisplayName() + " in " + (System.currentTimeMillis() - mss) + "ms!");
    }

    @Override
    public void onDisable() {
        long mss = System.currentTimeMillis();

        Log.info("Unloaded " + this.getPluginMeta().getDisplayName() + " in " + (System.currentTimeMillis() - mss) + "ms!");
    }
}
