package org.fuin.dsl.ddd.gen.base

import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.fuin.dsl.ddd.tests.DomainDrivenDesignDslInjectorProvider
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate
import org.fuin.dsl.ddd.domainDrivenDesignDsl.DomainModel
import org.fuin.srcgen4j.core.emf.SimpleCodeReferenceRegistry
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext
import org.junit.Test
import org.junit.runner.RunWith

import static org.assertj.core.api.Assertions.*

import static extension org.fuin.dsl.ddd.extensions.DddDomainModelExtensions.*

@InjectWith(typeof(DomainDrivenDesignDslInjectorProvider))
@RunWith(typeof(XtextRunner))
class SrcMethodTest {

	@Inject
	ParseHelper<DomainModel> parser

	@Inject 
	ValidationTestHelper validationTester

	@Test
	def void testCreate() {

		// PREPARE
		val refReg = new SimpleCodeReferenceRegistry()
		refReg.putReference("x.a.MyAggregateId", "a.b.c.MyAggregateId")
		refReg.putReference("x.a.MyValueObject", "a.b.c.MyValueObject")
		refReg.putReference("x.a.ConstraintViolatedException", "a.b.c.ConstraintViolatedException")
		val ctx = new SimpleCodeSnippetContext(refReg)
		val Aggregate aggregate = model().find(Aggregate, "MyAggregate")
		val method = aggregate.methods.get(0)
		val annotations = #["@One", "@Two(\"2\")"]
		val SrcMethod testee = new SrcMethod(ctx, annotations, "public", false, GenerateOptions.empty(), method)

		// TEST
		val result = testee.toString

		// VERIFY
		assertThat(result).isEqualTo(
			'''
				/**
				 * Does some cool things.
				 *
				 * @param id Unique aggregate identifier.
				 * @param vo Example value object.
				 *
				 * @throws ConstraintViolatedException The constraint was violated.
				 */
				@One
				@Two("2")
				public void doSomething(@NotNull final MyAggregateId id, final MyValueObject vo) throws ConstraintViolatedException {
					// TODO Implement!
				}
			'''.toString)
		assertThat(ctx.imports).containsOnly("javax.validation.constraints.NotNull", "a.b.c.MyAggregateId",
			"a.b.c.MyValueObject", "a.b.c.ConstraintViolatedException")

	}

	@Test
	def void testCreateAbstract() {

		// PREPARE
		val refReg = new SimpleCodeReferenceRegistry()
		refReg.putReference("x.a.MyAggregateId", "a.b.c.MyAggregateId")
		refReg.putReference("x.a.MyValueObject", "a.b.c.MyValueObject")
		refReg.putReference("x.a.ConstraintViolatedException", "a.b.c.ConstraintViolatedException")
		val ctx = new SimpleCodeSnippetContext(refReg)
		val Aggregate aggregate = model().find(Aggregate, "MyAggregate")
		val method = aggregate.methods.get(0)
		val SrcMethod testee = new SrcMethod(ctx, null, "public", true, GenerateOptions.empty(), method)

		// TEST
		val result = testee.toString

		// VERIFY
		assertThat(result).isEqualTo(
			'''
				/**
				 * Does some cool things.
				 *
				 * @param id Unique aggregate identifier.
				 * @param vo Example value object.
				 *
				 * @throws ConstraintViolatedException The constraint was violated.
				 */
				public abstract void doSomething(@NotNull final MyAggregateId id, final MyValueObject vo) throws ConstraintViolatedException;
			'''.toString)
		assertThat(ctx.imports).containsOnly("javax.validation.constraints.NotNull", "a.b.c.MyAggregateId",
			"a.b.c.MyValueObject", "a.b.c.ConstraintViolatedException")

	}

	def model() {
		val DomainModel model = parser.parse(Utils.readAsString(class.getResource("/example1.ddd")))
		validationTester.assertNoIssues(model)
		return model
	}


}
