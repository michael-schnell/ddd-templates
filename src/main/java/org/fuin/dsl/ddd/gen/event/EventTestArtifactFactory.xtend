package org.fuin.dsl.ddd.gen.event

import java.util.Map
import org.eclipse.emf.ecore.EObject
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractEntity
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Event
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace
import org.fuin.dsl.ddd.gen.base.AbstractSource
import org.fuin.dsl.ddd.gen.base.SrcImports
import org.fuin.srcgen4j.commons.GenerateException
import org.fuin.srcgen4j.commons.GeneratedArtifact
import org.fuin.srcgen4j.core.emf.CodeReferenceRegistry
import org.fuin.srcgen4j.core.emf.CodeSnippetContext
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext

import static org.fuin.dsl.ddd.gen.base.Utils.*

import static extension org.fuin.dsl.ddd.gen.extensions.CollectionExtensions.*
import static extension org.fuin.dsl.ddd.gen.extensions.EventExtensions.*

class EventTestArtifactFactory extends AbstractSource<Event> {

	override getModelType() {
		typeof(Event)
	}

	override create(Event event, Map<String, Object> context, boolean preparationRun) throws GenerateException {
		val EObject method = event.eContainer();
		val EObject container = method.eContainer();
		if (container instanceof AbstractEntity) {
			val AbstractEntity entity = container as AbstractEntity;

			val className = event.getName() + "Test"
			val Namespace ns = entity.eContainer() as Namespace;
			val pkg = ns.asPackage
			val fqn = pkg + "." + event.getName()
			val filename = fqn.replace('.', '/') + ".java";

			val CodeReferenceRegistry refReg = getCodeReferenceRegistry(context)
			refReg.putReference(event.uniqueName + "Test", fqn)

			if (preparationRun) {

				// No code generation during preparation phase
				return null
			}

			val SimpleCodeSnippetContext ctx = new SimpleCodeSnippetContext()
			ctx.addImports
			ctx.addReferences(event)
			ctx.resolve(refReg)

			return new GeneratedArtifact(artifactName, filename,
				create(ctx, event, pkg, className).toString().getBytes("UTF-8"));
		}
	}

	def addImports(CodeSnippetContext ctx) {
		ctx.requiresImport("javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter")
	}

	def addReferences(CodeSnippetContext ctx, Event event) {
	}

	def create(SimpleCodeSnippetContext ctx, Event event, String pkg, String className) {
		val String src = ''' 
			// CHECKSTYLE:OFF
			public final class «event.name»Test extends AbstractBaseTest {
			
				@Test
				public final void testSerializeDeserialize() {
			
					// PREPARE
					final «event.name» original = createTestee();
			
					// TEST
					final «event.name» copy = deserialize(serialize(original));
			
					// VERIFY
					assertThat(original).isEqualTo(copy);
			
				}
			
				@Test
				public final void testMarshalUnmarshal() {
			
					// PREPARE
					final «event.name» original = createTestee();
			
					// TEST
					final String xml = marshal(original, createAdapter(), «event.name».class);
					final «event.name» copy = unmarshal(xml, createAdapter(), «event.name».class);
			
					// VERIFY
					assertThat(original).isEqualTo(copy);
			
				}		
			
				private «event.name» createTestee() {
				final AId aId = null;
				«FOR v : event.variables.nullSafe»
					final «asJavaType(v)» «v.name» = null;
				«ENDFOR»
			
					return new «event.name»(new EntityIdPath(aId), «_methodCall(event.variables)»);
				}				
				
			  protected final XmlAdapter<?, ?>[] createAdapter() {
					final EntityIdPathConverter entityIdPathConverter = new EntityIdPathConverter(new EmsEntityIdFactory());
					return new XmlAdapter[] { entityIdPathConverter };
			  }
				
			}
			// CHECKSTYLE:ON
		'''

		// Source code creation is splitted into two parts because imports are 
		// added to the "ctx" during creation of above "src" variable
		''' 
			«copyrightHeader» 
			package «pkg»;
			
			«new SrcImports(ctx.imports)»
			
			«src»
		'''

	}

}
