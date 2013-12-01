package org.fuin.dsl.ddd.gen.aggregategen

import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace
import org.fuin.dsl.ddd.gen.base.AbstractSource
import org.fuin.srcgen4j.commons.ArtifactFactory;
import org.fuin.srcgen4j.commons.GenerateException
import org.fuin.srcgen4j.commons.ArtifactFactoryConfig
import org.fuin.srcgen4j.commons.GeneratedArtifact

/**
 * Generates an abstract aggregate Java class.
 */
class AggregateAbstractSource extends AbstractSource implements ArtifactFactory<Aggregate> {
	
	override getModelType() {
		return typeof(Aggregate)
	}
	
	override init(ArtifactFactoryConfig config) {
		// Not used
	}
	
	override isIncremental() {
		true
	}
	
	override create(Aggregate aggregate) throws GenerateException {
        val Namespace ns = aggregate.eContainer() as Namespace;
        val filename = (ns.getName() + ".Abstract" + aggregate.getName()).replace('.', '/') + ".java"
        return new GeneratedArtifact("AbstractAggregate", filename, create(aggregate, ns).toString());
	}
	
	/**
	 * Creates the actual source code.
	 */
	def create(Aggregate aggregate, Namespace ns) {
		''' 
		package «ns.name»;
		
		«_imports(aggregate)»
		
		/** «aggregate.doc.text» */
		public abstract class Abstract«aggregate.name» {
		
			«_varsDecl(aggregate.variables)»
		
			«_settersGetters("protected final", aggregate.variables)»
		
			«_abstractMethodsDecl(aggregate.methods)»
		
			«_eventAbstractMethodsDecl(aggregate.methods)»
		
		}
	'''	
	}
	
}