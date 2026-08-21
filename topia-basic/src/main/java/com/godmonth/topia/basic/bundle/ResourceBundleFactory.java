package com.godmonth.topia.basic.bundle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleFactory {

	private ResourceBundleFactory() {
	}

	public static ResourceBundle createResourceBundle(String path,
			String basename, Locale locale) throws MalformedURLException {
		URL[] urls = { new File(path).toURI().toURL() };
		ClassLoader loader = new URLClassLoader(urls);
		return ResourceBundle.getBundle(basename, locale, loader);
	}

	public static ResourceBundle createResourceBundle(String path,
			String basename) throws MalformedURLException {
		return createResourceBundle(path, basename, Locale.getDefault());
	}
	
}
