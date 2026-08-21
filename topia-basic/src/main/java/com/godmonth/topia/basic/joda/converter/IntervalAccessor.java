package com.godmonth.topia.basic.joda.converter;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import jodd.bean.BeanUtil;

public class IntervalAccessor {
	private IntervalAccessor() {
	}

	public static Interval getValue(Object bean, Pair<String, String> propertyNamePair) {
		Date left = BeanUtil.silent.getProperty(bean, propertyNamePair.getLeft());
		Date right = BeanUtil.silent.getProperty(bean, propertyNamePair.getRight());

		if (left != null && right != null) {
			return new Interval(new DateTime(left), new DateTime(right));
		}
		return null;
	}

	public static void setValue(Object bean, Pair<String, String> propertyNamePair, Interval interval) {

		if (interval != null) {
			BeanUtil.silent.setProperty(bean, propertyNamePair.getLeft(), interval.getStart().toDate());
			BeanUtil.silent.setProperty(bean, propertyNamePair.getRight(), interval.getEnd().toDate());
		} else {
			BeanUtil.silent.setProperty(bean, propertyNamePair.getLeft(), null);
			BeanUtil.silent.setProperty(bean, propertyNamePair.getRight(), null);
		}
	}
}
