package org.aml.typesystem.meta.restrictions.minmax;

import org.aml.typesystem.BuiltIns;
import org.aml.typesystem.values.IKnowsPropertyCount;
import org.aml.typesystem.values.ObjectAccess;

/**
 * <p>MaxProperties class.</p>
 *
 * @author kor
 * @version $Id: $Id
 */
public class MaxProperties extends MinMaxRestriction {

	/**
	 * <p>Constructor for MaxProperties.</p>
	 *
	 * @param max a {@link java.lang.Number} object.
	 */
	public MaxProperties(Number max) {
		super(max,true,MinProperties.class,BuiltIns.OBJECT,true);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "object should have not more then " + (int)doubleValue() + " properties";
	}

	/** {@inheritDoc} */
	@Override
	protected Object extractValue(Object o) {
		if (o instanceof IKnowsPropertyCount) {
			IKnowsPropertyCount m=(IKnowsPropertyCount) o;
			return m.propertyCount();
		}
		return ObjectAccess.properties(o).size();
	}

}
