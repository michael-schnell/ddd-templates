package org.fuin.dsl.ddd.gen.base

import java.util.List
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Parameter
import org.fuin.srcgen4j.core.emf.CodeSnippet
import org.fuin.srcgen4j.core.emf.CodeSnippetContext

/**
 * Creates source code for zero or more parameter declarations.
 */
class SrcParamsDecl implements CodeSnippet {

	val CodeSnippetContext ctx
	val GenerateOptions options
	val List<Parameter> parameters

	/**
	 * Constructor with all mandatory data.
	 * 
	 * @param ctx Context.
	 * @param options Options to use.
	 * @param parameters Parameters.
	 */
	new(CodeSnippetContext ctx, GenerateOptions options, List<Parameter> parameters) {
		this.ctx = ctx
		this.options = options
		this.parameters = parameters
	}

	override toString() {
		if (parameters === null || parameters.size == 0) {
			return "";
		}
		'''«FOR parameter : parameters SEPARATOR ', '»«new SrcParamDecl(ctx, options, parameter)»«ENDFOR»'''
	}

}
