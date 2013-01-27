package org.minr.MinrWho;

class WhoThread extends Thread {

    private final Who plugin;

    public WhoThread(Who plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (plugin.isWhoEnabled()) {
        	plugin.whoisonline("derp");
        }
    }
}