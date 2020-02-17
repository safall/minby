package com.afi.minby.core;

import android.content.Context;

import androidx.core.util.ObjectsCompat;

import java.util.Arrays;
import java.util.Iterator;

public class StringUtils {

	private static final String LINE_SEPARATOR = "\n";

	public static boolean equals(String stringOne, String stringTwo) {
		return ObjectsCompat.equals(stringOne, stringTwo);
	}

	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}

	public static boolean isNotTrimEmpty(String string) {
		return !isTrimEmpty(string);
	}

	public static boolean isTrimEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

	public static String toNullSafeString(String string) {
		if (string != null) {
			return string;
		}
		return "";
	}

	public static String format(Context context, int formatResId, Object... formatArgs) {
		return format(context.getString(formatResId), formatArgs);
	}

	public static String format(String format, Object... formatArgs) {
		return String.format(format, formatArgs);
	}

	public static String getString(int resId, Context context) {
		return context.getString(resId);
	}

	public static String[] getStringArray(int resId, Context context) {
		return context.getResources().getStringArray(resId);
	}

	public static String join(String separator, Object[] parts) {
		return join(separator, Arrays.asList(parts));
	}

	public static String join(String separator, Iterable<?> parts) {
		StringBuilder stringBuilder = new StringBuilder();

		Iterator<?> iterator = parts.iterator();
		if (iterator.hasNext()) {
			stringBuilder.append(iterator.next());
			while (iterator.hasNext()) {
				stringBuilder.append(separator);
				stringBuilder.append(iterator.next());
			}
		}

		return stringBuilder.toString();
	}

	public static String joinBelow(String textAbove, String textBelow) {
		return textAbove + LINE_SEPARATOR + textBelow;
	}

	public static String joinBelow(Iterable<?> parts) {
		return join(LINE_SEPARATOR, parts);
	}
}