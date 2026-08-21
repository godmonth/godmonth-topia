package com.godmonth.topia.basic.enums;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class EnumResourceBundleUtils {
	public static String getDescription(Enum<?> enumValue, Locale locale) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(enumValue.getDeclaringClass().getName(), locale,
					enumValue.getDeclaringClass().getClassLoader());
			return bundle.getString(enumValue.name());
		} catch (MissingResourceException e) {
			return enumValue.name();
		}
	}

	public static String getEnumDescription(Class<? extends Enum<?>> enumClass, Locale locale) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(enumClass.getName(), locale, enumClass.getClassLoader());
			return bundle.getString("");
		} catch (MissingResourceException e) {
			return enumClass.getSimpleName();
		}
	}
}
