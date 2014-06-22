package org.fuin.dsl.ddd.gen.base

import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.fuin.dsl.ddd.DomainDrivenDesignDslInjectorProvider
import org.fuin.dsl.ddd.domainDrivenDesignDsl.DomainModel
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ValueObject
import org.fuin.srcgen4j.core.emf.SimpleCodeReferenceRegistry
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext
import org.junit.Test
import org.junit.runner.RunWith

import static org.fest.assertions.Assertions.*

import static extension org.fuin.dsl.ddd.gen.extensions.DomainModelExtensions.*

@InjectWith(typeof(DomainDrivenDesignDslInjectorProvider))
@RunWith(typeof(XtextRunner))
class SrcVarsDeclTest {

	@Inject
	private ParseHelper<DomainModel> parser

	@Test
	def void testCreate() {

		// PREPARE
		val refReg = new SimpleCodeReferenceRegistry()
		refReg.putReference("a.b.String", "java.lang.String")
		refReg.putReference("a.b.Integer", "java.lang.Integer")
		refReg.putReference("a.b.Boolean", "java.lang.Boolean")
		val ctx = new SimpleCodeSnippetContext(refReg)

		val ValueObject valueObject = createModel().find(ValueObject, "MyValueObject")
		val SrcVarsDecl testee = new SrcVarsDecl(ctx, "private", false, valueObject)

		// TEST
		val result = testee.toString

		// VERIFY
		assertThat(result).isEqualTo(
			'''
				@NotNull
				private String a;
				
				@NotNull
				private Integer b;
				
				private Boolean c;
				
			''')
		assertThat(ctx.imports).containsOnly("java.lang.String", "java.lang.Integer", "java.lang.Boolean",
			"javax.validation.constraints.NotNull")
	}

	private def DomainModel createModel() {
		parser.parse(
			'''
				context a {
					
					namespace b {
						
						type String
						type Integer
						type Boolean
				
						value-object MyValueObject {
							String a
							Integer b
							nullable Boolean c
						}
				
					}
				
				}
			'''
		)
	}

}