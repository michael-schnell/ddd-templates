package org.fuin.dsl.ddd.gen.enumobject

import java.util.HashMap
import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.fuin.dsl.ddd.domainDrivenDesignDsl.DomainModel
import org.fuin.dsl.ddd.domainDrivenDesignDsl.EnumObject
import org.fuin.dsl.ddd.gen.base.GenerateOptions
import org.fuin.dsl.ddd.gen.base.Utils
import org.fuin.dsl.ddd.tests.DomainDrivenDesignDslInjectorProvider
import org.fuin.srcgen4j.commons.ArtifactFactoryConfig
import org.fuin.srcgen4j.commons.DefaultContext
import org.fuin.xmlcfg4j.Variable
import org.junit.Test
import org.junit.runner.RunWith

import static org.assertj.core.api.Assertions.*

import static extension org.fuin.dsl.ddd.extensions.DddDomainModelExtensions.*
import static extension org.fuin.dsl.ddd.gen.base.TestExtensions.*
import static extension org.fuin.dsl.ddd.gen.extensions.MapExtensions.*

@InjectWith(typeof(DomainDrivenDesignDslInjectorProvider))
@RunWith(typeof(XtextRunner))
class FinalEnumArtifactFactoryTest {

	@Inject
	ParseHelper<DomainModel> parser

	@Inject 
	ValidationTestHelper validationTester

	@Test
	def void testFinalEnumA() {
		testEnum("EnumA")
	}

	@Test
	def void testFinalEnumB() {
		testEnum("EnumB")
	}

	@Test
	def void testFinalEnumC() {
		testEnum("EnumC")
	}

	@Test
	def void testFinalEnumD() {
		testEnum("EnumD")
	}
	
	private def testEnum(String enumName) {

		val context = new HashMap<String, Object>()
		val refReg = context.codeReferenceRegistry
		refReg.putReference("x.types.String", "java.lang.String")
		refReg.putReference("x.types.Integer", "java.lang.Integer")
		refReg.putReference("x.enumobject.Abstract" + enumName, "tst.x.enumobject.Abstract" + enumName)

		val FinalEnumArtifactFactory testee = createTestee()
		val EnumObject enu = model.find(typeof(EnumObject), enumName)

		// TEST
		val result = new String(testee.create(enu, context, false).data)

		// VERIFY
		assertThat(result).isEqualTo(("x/enumobject/" + enumName + ".java").loadAbstractExample)

	}

	private def createTestee() {
		val factory = new FinalEnumArtifactFactory()
		val ArtifactFactoryConfig config = new ArtifactFactoryConfig("finalEnumObject", FinalEnumArtifactFactory.name)
		config.addVariable(new Variable(GenerateOptions.KEY_BASE_PKG, EXAMPLES_ABSTRACT))
		config.addVariable(new Variable(GenerateOptions.KEY_COPYRIGHT_HEADER, Utils.readAsString("required-header.txt")))
		config.init(new DefaultContext(), null)
		factory.init(config)
		return factory
	}

	private def model() {
		val DomainModel model = parser.parse(Utils.readAsString(class.getResource("/enumobject.ddd")))
		validationTester.assertNoIssues(model)
		return model
	}

}
