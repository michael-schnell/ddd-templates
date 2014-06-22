package org.fuin.dsl.ddd.gen.extensions

import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractVO
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AggregateId
import org.fuin.dsl.ddd.domainDrivenDesignDsl.EntityId
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ExternalType
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ValueObject

/**
 * Provides extension methods for AbstractVO.
 */
class AbstractVOExtensions {

	/**
	 * Returns the base type for a value object.
	 * 
	 * @param vo Value object.
	 * 
	 * @return Base type or null.
	 */	
	def static ExternalType baseType(AbstractVO vo) {
		if (vo instanceof AggregateId) {
			return (vo as AggregateId).base
		}
		if (vo instanceof EntityId) {
			return (vo as EntityId).base
		}
		if (vo instanceof ValueObject) {
			return (vo as ValueObject).base
		}
		return null
	}

}