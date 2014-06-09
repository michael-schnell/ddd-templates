package org.fuin.dsl.ddd.gen.aggregate

import java.util.Map
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace
import org.fuin.dsl.ddd.gen.base.AbstractSource
import org.fuin.srcgen4j.commons.GenerateException
import org.fuin.srcgen4j.commons.GeneratedArtifact

import static extension org.fuin.dsl.ddd.gen.extensions.StringExtensions.*

class AggregateDocArtifactFactory extends AbstractSource<Aggregate> {

	override getModelType() {
		return typeof(Aggregate)
	}
	
	override create(Aggregate aggregate, Map<String, Object> context, boolean preparationRun) throws GenerateException {
        val Namespace ns = aggregate.eContainer() as Namespace;
        val filename = (ns.asPackage + "." + aggregate.getName()).replace('.', '/') + ".html"
        return new GeneratedArtifact(artifactName, filename, create(aggregate, ns).toString().getBytes("UTF-8"));
	}
	
	def create(Aggregate aggregate, Namespace ns) {
		'''
		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<title>«ns.asPackage».«aggregate.name»</title>
			</head>
			<body>
				«ns.asPackage».«aggregate.name»
				<table border="1">
				«FOR variable : aggregate.variables»
					<tr><td>«variable.name»</td><td>«variable.type.name»</td><td>«variable.doc.text»</td></tr>
				«ENDFOR»
				</table>
			</body>
		</html>
		'''	
	}
	
}