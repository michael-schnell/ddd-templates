package org.fuin.dsl.ddd.gen.enumobject

import java.util.Map
import org.fuin.dsl.ddd.domainDrivenDesignDsl.EnumObject
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace
import org.fuin.dsl.ddd.gen.base.AbstractSource
import org.fuin.dsl.ddd.gen.base.SrcAll
import org.fuin.srcgen4j.commons.GenerateException
import org.fuin.srcgen4j.commons.GeneratedArtifact
import org.fuin.srcgen4j.core.emf.CodeReferenceRegistry
import org.fuin.srcgen4j.core.emf.CodeSnippetContext
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext

import static org.fuin.dsl.ddd.gen.base.Utils.*

import static extension org.fuin.dsl.ddd.gen.extensions.AbstractElementExtensions.*
import static extension org.fuin.dsl.ddd.gen.extensions.StringExtensions.*

class EnumArtifactFactory extends AbstractSource<EnumObject> {

	override getModelType() {
		typeof(EnumObject)
	}

	override create(EnumObject enu, Map<String, Object> context, boolean preparationRun) throws GenerateException {

		val className = enu.getName()
		val Namespace ns = enu.eContainer() as Namespace;
		val pkg = ns.asPackage
		val fqn = pkg + "." + enu.getName()
		val filename = fqn.replace('.', '/') + ".java";

		val CodeReferenceRegistry refReg = getCodeReferenceRegistry(context)
		refReg.putReference(enu.uniqueName, fqn)

		if (preparationRun) {

			// No code generation during preparation phase
			return null
		}

		val SimpleCodeSnippetContext ctx = new SimpleCodeSnippetContext(refReg)
		ctx.addImports
		ctx.addReferences(enu)

		return new GeneratedArtifact(artifactName, filename,
			create(ctx, enu, pkg, className).toString().getBytes("UTF-8"));
	}

	def addImports(CodeSnippetContext ctx) {
	}

	def addReferences(CodeSnippetContext ctx, EnumObject enu) {
	}

	def create(SimpleCodeSnippetContext ctx, EnumObject vo, String pkg, String className) {
		val String src = ''' 
			/** «vo.doc.text» */
			public enum «vo.name» {
			
			«FOR in : vo.instances SEPARATOR ','»
				«in.doc»
				«in.name»(«_methodCall(vo.variables, in.params)»)
			«ENDFOR»;
			
				«_varsDecl(ctx, vo, false)»
			
				private «vo.name»(«_paramsDecl(ctx, vo.variables)») {
					«_paramsAssignment(vo.variables)»
				}
			
			}
		'''

		new SrcAll(copyrightHeader, pkg, ctx.imports, src).toString

	}

}
