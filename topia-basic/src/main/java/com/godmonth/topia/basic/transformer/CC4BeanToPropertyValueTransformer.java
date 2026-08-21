package com.godmonth.topia.basic.transformer;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.Transformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CC4BeanToPropertyValueTransformer<I, O> implements
		Transformer<I, O> {

	/**
	 * The name of the property that will be used in the transformation of the
	 * object.
	 */
	private String propertyName;

	/**
	 * <p>
	 * Should null objects on the property path throw an
	 * <code>IllegalArgumentException</code>?
	 * </p>
	 * <p>
	 * Determines whether <code>null</code> objects in the property path will
	 * genenerate an <code>IllegalArgumentException</code> or not. If set to
	 * <code>true</code> then if any objects in the property path evaluate to
	 * <code>null</code> then the <code>IllegalArgumentException</code> throw by
	 * <code>PropertyUtils</code> will be logged but not rethrown and
	 * <code>null</code> will be returned. If set to <code>false</code> then if
	 * any objects in the property path evaluate to <code>null</code> then the
	 * <code>IllegalArgumentException</code> throw by <code>PropertyUtils</code>
	 * will be logged and rethrown.
	 * </p>
	 */
	private boolean ignoreNull;

	/**
	 * Constructs a Transformer which does not ignore nulls. Constructor which
	 * takes the name of the property that will be used in the transformation
	 * and assumes <code>ignoreNull</code> to be <code>false</code>.
	 *
	 * @param propertyName
	 *            The name of the property that will be used in the
	 *            transformation.
	 * @throws IllegalArgumentException
	 *             If the <code>propertyName</code> is <code>null</code> or
	 *             empty.
	 */
	public CC4BeanToPropertyValueTransformer(String propertyName) {
		this(propertyName, false);
	}

	/**
	 * Constructs a Transformer and sets ignoreNull. Constructor which takes the
	 * name of the property that will be used in the transformation and a
	 * boolean which determines whether <code>null</code> objects in the
	 * property path will genenerate an <code>IllegalArgumentException</code> or
	 * not.
	 *
	 * @param propertyName
	 *            The name of the property that will be used in the
	 *            transformation.
	 * @param ignoreNull
	 *            Determines whether <code>null</code> objects in the property
	 *            path will genenerate an <code>IllegalArgumentException</code>
	 *            or not.
	 * @throws IllegalArgumentException
	 *             If the <code>propertyName</code> is <code>null</code> or
	 *             empty.
	 */
	public CC4BeanToPropertyValueTransformer(String propertyName,
			boolean ignoreNull) {
		super();

		if ((propertyName != null) && (propertyName.length() > 0)) {
			this.propertyName = propertyName;
			this.ignoreNull = ignoreNull;
		} else {
			throw new IllegalArgumentException(
					"propertyName cannot be null or empty");
		}
	}

	/**
	 * Returns the value of the property named in the transformer's constructor
	 * for the object provided. If any object in the property path leading up to
	 * the target property is <code>null</code> then the outcome will be based
	 * on the value of the <code>ignoreNull</code> attribute. By default,
	 * <code>ignoreNull</code> is <code>false</code> and would result in an
	 * <code>IllegalArgumentException</code> if an object in the property path
	 * leading up to the target property is <code>null</code>.
	 *
	 * @param object
	 *            The object to be transformed.
	 * @return The value of the property named in the transformer's constructor
	 *         for the object provided.
	 * @throws IllegalArgumentException
	 *             If an IllegalAccessException, InvocationTargetException, or
	 *             NoSuchMethodException is thrown when trying to access the
	 *             property specified on the object provided. Or if an object in
	 *             the property path provided is <code>null</code> and
	 *             <code>ignoreNull</code> is set to <code>false</code>.
	 */
	public O transform(I object) {

		Object propertyValue = null;

		try {
			propertyValue = PropertyUtils.getProperty(object, propertyName);
		} catch (IllegalArgumentException e) {
			final String errorMsg = "Problem during transformation. Null value encountered in property path...";

			if (ignoreNull) {
				log.warn("WARNING: " + errorMsg + e);
			} else {
				IllegalArgumentException iae = new IllegalArgumentException(
						errorMsg);
				if (!BeanUtils.initCause(iae, e)) {
					log.error(errorMsg, e);
				}
				throw iae;
			}
		} catch (IllegalAccessException e) {
			final String errorMsg = "Unable to access the property provided.";
			IllegalArgumentException iae = new IllegalArgumentException(
					errorMsg);
			if (!BeanUtils.initCause(iae, e)) {
				log.error(errorMsg, e);
			}
			throw iae;
		} catch (InvocationTargetException e) {
			final String errorMsg = "Exception occurred in property's getter";
			IllegalArgumentException iae = new IllegalArgumentException(
					errorMsg);
			if (!BeanUtils.initCause(iae, e)) {
				log.error(errorMsg, e);
			}
			throw iae;
		} catch (NoSuchMethodException e) {
			final String errorMsg = "No property found for name ["
					+ propertyName + "]";
			IllegalArgumentException iae = new IllegalArgumentException(
					errorMsg);
			if (!BeanUtils.initCause(iae, e)) {
				log.error(errorMsg, e);
			}
			throw iae;
		}

		return (O) propertyValue;
	}

	/**
	 * Returns the name of the property that will be used in the transformation
	 * of the bean.
	 *
	 * @return The name of the property that will be used in the transformation
	 *         of the bean.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Returns the flag which determines whether <code>null</code> objects in
	 * the property path will genenerate an
	 * <code>IllegalArgumentException</code> or not. If set to <code>true</code>
	 * then if any objects in the property path evaluate to <code>null</code>
	 * then the <code>IllegalArgumentException</code> throw by
	 * <code>PropertyUtils</code> will be logged but not rethrown and
	 * <code>null</code> will be returned. If set to <code>false</code> then if
	 * any objects in the property path evaluate to <code>null</code> then the
	 * <code>IllegalArgumentException</code> throw by <code>PropertyUtils</code>
	 * will be logged and rethrown.
	 *
	 * @return The flag which determines whether <code>null</code> objects in
	 *         the property path will genenerate an
	 *         <code>IllegalArgumentException</code> or not.
	 */
	public boolean isIgnoreNull() {
		return ignoreNull;
	}
}
