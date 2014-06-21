package org.fuin.dsl.ddd.gen.base

import org.fuin.dsl.ddd.domainDrivenDesignDsl.Method
import org.fuin.srcgen4j.core.emf.CodeSnippet
import org.fuin.srcgen4j.core.emf.CodeSnippetContext

/**
 * Creates source code for a single method.
 */
class SrcMethod implements CodeSnippet {

	val CodeSnippetContext ctx
	val String modifiers
	val boolean makeAbstract
	val Method method

	/**
	 * Constructor with all mandatory data.
	 * 
	 * @param ctx Context.
	 * @param modifiers Modifiers (Don't include "abstract" - Use next argument instead).
	 * @param makeAbstract TRUE for an abstract method or FALSE for a non-abstract method with "// TODO Implement!".
	 * @param method Method to create the source for.
	 */
	new(CodeSnippetContext ctx, String modifiers, boolean makeAbstract, Method method) {
		this.ctx = ctx
		this.modifiers = modifiers
		this.makeAbstract = makeAbstract
		this.method = method
	}

	override toString() {
		if (makeAbstract) {
			'''	
				«new SrcMethodJavaDoc(ctx, method)»
				«new SrcMethodSignature(ctx, modifiers, makeAbstract, method)»;
			'''
		} else {
			'''	
				«new SrcMethodJavaDoc(ctx, method)»
				«new SrcMethodSignature(ctx, modifiers, makeAbstract, method)» {
					// TODO Implement!
				}
			'''
		}
	}

}
