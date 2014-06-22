package org.fuin.dsl.ddd.gen.base

import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.fuin.dsl.ddd.DomainDrivenDesignDslInjectorProvider
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate
import org.fuin.dsl.ddd.domainDrivenDesignDsl.DomainModel
import org.fuin.srcgen4j.core.emf.SimpleCodeReferenceRegistry
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext
import org.junit.Test
import org.junit.runner.RunWith

import static org.fest.assertions.Assertions.*

import static extension org.fuin.dsl.ddd.gen.extensions.DomainModelExtensions.*

@InjectWith(typeof(DomainDrivenDesignDslInjectorProvider))
@RunWith(typeof(XtextRunner))
class SrcMethodSignatureSignatureTest {

	@Inject
	private ParseHelper<DomainModel> parser

	@Test
	def void testCreate() {

		// PREPARE
		val refReg = new SimpleCodeReferenceRegistry()
		refReg.putReference("x.a.MyAggregateId", "a.b.c.MyAggregateId")
		refReg.putReference("x.a.MyValueObject", "a.b.c.MyValueObject")
		refReg.putReference("x.a.ConstraintViolatedException", "a.b.c.ConstraintViolatedException")
		val ctx = new SimpleCodeSnippetContext(refReg)
		val Aggregate aggregate = createModel().find(Aggregate, "MyAggregate")
		val method = aggregate.methods.get(0)
		val SrcMethodSignature testee = new SrcMethodSignature(ctx, "public", false, method)

		// TEST
		val result = testee.toString

		// VERIFY
		assertThat(result).isEqualTo(
			'''public void doSomething(@NotNull final MyAggregateId id, final MyValueObject vo) throws ConstraintViolatedException''')
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
		val Aggregate aggregate = createModel().find(Aggregate, "MyAggregate")
		val method = aggregate.methods.get(0)
		val SrcMethodSignature testee = new SrcMethodSignature(ctx, "public", true, method)

		// TEST
		val result = testee.toString

		// VERIFY
		assertThat(result).isEqualTo(
			'''public abstract void doSomething(@NotNull final MyAggregateId id, final MyValueObject vo) throws ConstraintViolatedException''')
		assertThat(ctx.imports).containsOnly("javax.validation.constraints.NotNull", "a.b.c.MyAggregateId",
			"a.b.c.MyValueObject", "a.b.c.ConstraintViolatedException")

	}

	private def DomainModel createModel() {
		return parser.parse(Utils.readAsString(class.getResource("/example1.ddd")))
	}

}