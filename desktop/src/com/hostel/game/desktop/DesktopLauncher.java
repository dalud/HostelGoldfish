package com.hostel.game.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hostel.game.Hostel;

public class DesktopLauncher {
	public static void main (String[] arg) {
            DisplayMode desktopMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
            DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.setFromDisplayMode(displayMode);
            config.title = "Hostel Goldfish";
            config.width = 330;
            config.height = 186;
            new LwjglApplication(new Hostel(), config);
	}
}