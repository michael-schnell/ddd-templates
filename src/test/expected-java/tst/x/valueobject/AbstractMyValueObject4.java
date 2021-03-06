/**
 * Copyright (C) 2015 Michael Schnell. All rights reserved. 
 * http://www.fuin.org/
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see http://www.gnu.org/licenses/.
 */
package tst.x.valueobject;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.vo.ValueObject;

/**
 * Value object multiple attribute and without base.
 */
public abstract class AbstractMyValueObject4 implements ValueObject, Serializable {

	private static final long serialVersionUID = 1000L;
	
	@NotNull
	private String a;
	
	@NotNull
	private String b;
	
	/**
	 * Default constructor.
	 */
	protected AbstractMyValueObject4() {
		super();
	}
	
	/**
	 * Constructor with all data.
	 *
	 * @param a Persistent value A.
	 * @param b Persistent value B.
	 */
	public AbstractMyValueObject4(@NotNull final String a, @NotNull final String b) {
		super();
		Contract.requireArgNotNull("a", a);
		Contract.requireArgNotNull("b", b);
		
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Returns: Persistent value A.
	 *
	 * @return Current value.
	 */
	@NotNull
	public final String getA() {
		return a;
	}
	
	/**
	 * Returns: Persistent value B.
	 *
	 * @return Current value.
	 */
	@NotNull
	public final String getB() {
		return b;
	}
	
}
