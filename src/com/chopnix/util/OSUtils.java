package com.chopnix.util;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.CodeSource;

import com.chopnix.log.Logger;

import net.ftb.gui.LaunchFrame;

public class OSUtils {
	/**
	 * Gets the default installation path for the current OS.
	 * 
	 * @return a string containing the default install path for the current OS.
	 */
	public static String getDefInstallPath() {
		try {
			CodeSource codeSource = LaunchFrame.class.getProtectionDomain()
					.getCodeSource();
			File jarFile;
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			return jarFile.getParentFile().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out
				.println("Failed to get path for current directory - falling back to user's home directory.");
		return System.getProperty("user.dir") + "//FTB Pack Install";
	}

	public static String getJavaDelimiter() {
		if (getCurrentOS() == OS.WINDOWS) {
			return ";";
		} else if (getCurrentOS() == OS.UNIX) {
			return ":";
		} else if (getCurrentOS() == OS.MACOSX) {
			return ":";
		} else {
			return ";";
		}
	}

	public static void browse(String url) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			} else if (getCurrentOS() == OS.UNIX) {
				// Work-around to support non-GNOME Linux desktop environments
				// with xdg-open installed
				if (new File("/usr/bin/xdg-open").exists()
						|| new File("/usr/local/bin/xdg-open").exists()) {
					new ProcessBuilder("xdg-open", url).start();
				}
			}
		} catch (Exception e) {
			Logger.logError("Could not open link", e);
		}
	}

	public static OS getCurrentOS() {
		String osString = System.getProperty("os.name").toLowerCase();
		if (osString.contains("win")) {
			return OS.WINDOWS;
		} else if (osString.contains("nix") || osString.contains("nux")) {
			return OS.UNIX;
		} else if (osString.contains("mac")) {
			return OS.MACOSX;
		} else {
			return OS.OTHER;
		}
	}

	public static enum OS {
		WINDOWS, UNIX, MACOSX, OTHER,
	}
}
