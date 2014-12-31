package org.fuin.dsl.ddd.gen.valueobject

import java.util.HashMap
import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.fuin.dsl.ddd.DomainDrivenDesignDslInjectorProvider
import org.fuin.dsl.ddd.domainDrivenDesignDsl.DomainModel
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ValueObject
import org.fuin.dsl.ddd.gen.base.AbstractSource
import org.fuin.dsl.ddd.gen.base.Utils
import org.fuin.srcgen4j.commons.ArtifactFactoryConfig
import org.fuin.srcgen4j.commons.DefaultContext
import org.fuin.srcgen4j.commons.Variable
import org.junit.Test
import org.junit.runner.RunWith

import static org.fest.assertions.Assertions.*

import static extension org.fuin.dsl.ddd.gen.base.TestExtensions.*
import static extension org.fuin.dsl.ddd.gen.extensions.DomainModelExtensions.*
import static extension org.fuin.dsl.ddd.gen.extensions.MapExtensions.*

@InjectWith(typeof(DomainDrivenDesignDslInjectorProvider))
@RunWith(typeof(XtextRunner))
class FinalValueObjectArtifactFactoryTest {

	@Inject
	private ParseHelper<DomainModel> parser

	@Test
	def void testCreateMyValueObject() {

		// PREPARE
		val context = new HashMap<String, Object>()
		val refReg = context.codeReferenceRegistry
		refReg.putReference("x.types.String", "java.lang.String")
		refReg.putReference("x.valueobject.MyValueObjectConverter", EXAMPLES_CONCRETE + ".x.valueobject.MyValueObjectConverter")
		refReg.putReference("x.valueobject.AbstractMyValueObject", EXAMPLES_ABSTRACT + ".x.valueobject.AbstractMyValueObject")

		val FinalValueObjectArtifactFactory testee = createTestee()
		val ValueObject vo = model.find(typeof(ValueObject), "MyValueObject")

		// TEST
		val result = new String(testee.create(vo, context, false).data)

		// VERIFY
		assertThat(result).isEqualTo("x/valueobject/MyValueObject.java".loadAbstractExample)

	}
	@Test
	def void testCreateMyValueObject2() {
		
		// PREPARE
		val context = new HashMap<String, Object>()
		val refReg = context.codeReferenceRegistry
		refReg.putReference("x.types.String", "java.lang.String")
		refReg.putReference("x.valueobject.AbstractMyValueObject2", EXAMPLES_ABSTRACT + ".x.valueobject.AbstractMy2ValueObject")

		val FinalValueObjectArtifactFactory testee = createTestee()
		val ValueObject vo = model.find(typeof(ValueObject), "MyValueObject2")

		// TEST
		val result = new String(testee.create(vo, context, false).data)

		// VERIFY
		assertThat(result).isEqualTo("x/valueobject/MyValueObject2.java".loadAbstractExample)
		
	}	

	@Test
	def void testCreateMyValueObject3() {

		// PREPARE
		val context = new HashMap<String, Object>()
		val refReg = context.codeReferenceRegistry
		refReg.putReference("x.types.String", "java.lang.String")
		refReg.putReference("x.valueobject.MyValueObject3Converter", EXAMPLES_CONCRETE + ".x.valueobject.MyValueObject3Converter")
		refReg.putReference("x.valueobject.AbstractMyValueObject3", EXAMPLES_ABSTRACT + ".x.valueobject.AbstractMyValueObject3")

		val FinalValueObjectArtifactFactory testee = createTestee()
		val ValueObject vo = model.find(typeof(ValueObject), "MyValueObject3")

		// TEST
		val result = new String(testee.create(vo, context, false).data)

		// VERIFY
		assertThat(result).isEqualTo("x/valueobject/MyValueObject3.java".loadAbstractExample)

	}
	@Test
	def void testCreateMyValueObject4() {
		
		// PREPARE
		val context = new HashMap<String, Object>()
		val refReg = context.codeReferenceRegistry
		refReg.putReference("x.types.String", "java.lang.String")
		refReg.putReference("x.valueobject.AbstractMyValueObject4", EXAMPLES_ABSTRACT + ".x.valueobject.AbstractMy4ValueObject")

		val FinalValueObjectArtifactFactory testee = createTestee()
		val ValueObject vo = model.find(typeof(ValueObject), "MyValueObject4")

		// TEST
		val result = new String(testee.create(vo, context, false).data)

		// VERIFY
		assertThat(result).isEqualTo("x/valueobject/MyValueObject4.java".loadAbstractExample)
		
	}
		
	private def createTestee() {
		val factory = new FinalValueObjectArtifactFactory()
		val ArtifactFactoryConfig config = new ArtifactFactoryConfig("vo", FinalValueObjectArtifactFactory.name)
		config.addVariable(new Variable(AbstractSource.KEY_BASE_PKG, EXAMPLES_ABSTRACT))
		config.addVariable(new Variable(AbstractSource.KEY_COPYRIGHT_HEADER, Utils.readAsString("required-header.txt")))
		config.init(new DefaultContext(), null)
		factory.init(config)
		return factory
	}

	private def model() {
		return parser.parse(Utils.readAsString(class.getResource("/valueobject.ddd")))
	}

}