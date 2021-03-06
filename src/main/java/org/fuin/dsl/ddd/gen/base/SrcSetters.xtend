package org.fuin.dsl.ddd.gen.base

import java.util.List
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Attribute
import org.fuin.srcgen4j.core.emf.CodeSnippet
import org.fuin.srcgen4j.core.emf.CodeSnippetContext

/**
 * Creates source code for one or more setters.
 */
class SrcSetters implements CodeSnippet {

	val CodeSnippetContext ctx
	val GenerateOptions options
	val String modifiers
	val List<Attribute> attributes

	new(CodeSnippetContext ctx, GenerateOptions options, String modifiers, List<Attribute> attributes) {
		this.ctx = ctx		
		this.modifiers = modifiers
		this.options = options
		this.attributes = attributes
	}

	override toString() {
		'''	
			«FOR attribute : attributes»
				«new SrcSetter(ctx, options, modifiers, attribute)»
				
			«ENDFOR»
		'''
	}

}
