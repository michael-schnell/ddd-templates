package org.fuin.dsl.ddd.gen.base

import org.fuin.dsl.ddd.domainDrivenDesignDsl.Variable
import org.fuin.srcgen4j.core.emf.CodeSnippet
import org.fuin.srcgen4j.core.emf.CodeSnippetContext

import static extension org.fuin.dsl.ddd.extensions.DddStringExtensions.*
import static extension org.fuin.dsl.ddd.extensions.DddVariableExtensions.*
import static extension org.fuin.dsl.ddd.gen.extensions.VariableExtensions.*

/**
 * Creates source code for a single setter.
 */
class SrcSetter implements CodeSnippet {

	val CodeSnippetContext ctx
	val String modifiers
	val Variable variable

	new(CodeSnippetContext ctx, GenerateOptions options, String modifiers, Variable variable) {
		this.ctx = ctx
		this.modifiers = modifiers
		this.variable = variable
		if (variable.nullable === null) {
			ctx.requiresImport("javax.validation.constraints.NotNull")
			ctx.requiresImport("org.fuin.objects4j.common.Contract")		
		} else {
			ctx.requiresImport("javax.annotation.Nullable")
		}
		addRequiredReferences(variable, ctx)
	}

	override toString() {
		'''	
			/**
			 * Sets: «variable.superDoc.text»
			 *
			 * @param «variable.name» Value to set.
			 */
			«modifiers» void set«variable.name.toFirstUpper»(«IF variable.nullable === null»@NotNull «ELSE»@Nullable «ENDIF»final «variable.
				type(ctx)» «variable.name») {
				«IF variable.nullable === null»
					Contract.requireArgNotNull("«variable.name»", «variable.name»);
				«ENDIF»
				this.«variable.name» = «variable.name»;
			}
		'''
	}

}
