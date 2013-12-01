package org.fuin.dsl.ddd.gen.vogen

import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ValueObject
import org.fuin.dsl.ddd.gen.base.AbstractSource
import org.fuin.srcgen4j.commons.ArtifactFactory
import org.fuin.srcgen4j.commons.ArtifactFactoryConfig
import org.fuin.srcgen4j.commons.GenerateException
import org.fuin.srcgen4j.commons.GeneratedArtifact

class ValueObjectAbstractSource extends AbstractSource implements ArtifactFactory<ValueObject> {

	override getModelType() {
		typeof(ValueObject)
	}

	override init(ArtifactFactoryConfig config) {
		// Not used
	}

	override isIncremental() {
		true
	}

	override create(ValueObject valueObject) throws GenerateException {
		val Namespace ns = valueObject.eContainer() as Namespace;
        val String filename = (ns.getName() + ".Abstract" + valueObject.getName()).replace('.', '/') + ".java";
		return new GeneratedArtifact("AbstractValueObject", filename, create(valueObject, ns).toString());
	}
	
	def create(ValueObject vo, Namespace ns) {
		''' 
		package «ns.name»;
		
		«_imports(vo)»
		
		/** «vo.doc.text» */
		public abstract class Abstract«vo.name» {
		
			«_varsDecl(vo.variables)»
		
			public Abstract«vo.name»(«_paramsDecl(vo.variables)») {
				super();
				«_paramsAssignment(vo.variables)»
			}
			
		}
		'''	
	}
	
}