package org.fuin.dsl.ddd.gen.base

import org.fuin.dsl.ddd.domainDrivenDesignDsl.ExternalType
import org.fuin.srcgen4j.core.emf.CodeSnippet
import org.fuin.srcgen4j.core.emf.CodeSnippetContext

/**
 * Creates source code 'extends X' where X is the 
 * appropriate base class for the value object.
 */
class SrcVoBaseOptionalExtends implements CodeSnippet {

	val CodeSnippetContext ctx;

	val String baseName;

	/**
	 * Constructor with value object.
	 * 
	 * @param ctx Context.
	 * @param base External base type: "String", "UUID", "Integer" or "Long" are currently supported.
	 */
	new(CodeSnippetContext ctx, ExternalType base) {
		this.ctx = ctx
		if (base == null) {
			this.baseName = null
		} else {
			this.baseName = base.name
			switch baseName {
				case "String": ctx.requiresImport("org.fuin.objects4j.vo.AbstractStringValueObject")
				case "UUID": ctx.requiresImport("org.fuin.ddd4j.ddd.AbstractUUIDVO")
				case "Integer": ctx.requiresImport("org.fuin.objects4j.vo.AbstractIntegerValueObject")
				case "Long": ctx.requiresImport("org.fuin.objects4j.vo.AbstractLongValueObject")
			}
		}
	}

	override toString() {
		if (baseName == null) {
			return ""
		}
		switch baseName {
			case "String": return "extends AbstractStringValueObject "
			case "UUID": return "extends AbstractUUIDVO "
			case "Integer": return "extends AbstractIntegerValueObject "
			case "Long": return "extends AbstractLongValueObject "
			default: return ""
		}
	}

}