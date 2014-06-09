package org.fuin.dsl.ddd.gen.base

import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractVO
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ExternalType
import org.fuin.srcgen4j.core.emf.CodeReferenceRegistry
import org.fuin.srcgen4j.core.emf.CodeSnippet
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext

import static extension org.fuin.dsl.ddd.gen.extensions.AbstractElementExtensions.*

/**
 * Creates the source code for a value object converter.
 */
class SrcValueObjectConverter implements CodeSnippet {

	private val CodeReferenceRegistry refReg;

	private val String copyrightHeader

	private val String pkg

	private val String voTypeName

	private val String targetTypeName

	private val boolean implementsSingleEntityIdFactory

	private val String className

	private val SimpleCodeSnippetContext ctx

	/**
	 * Constructor with all mandatory data.
	 * 
	 * @param refReg Registry to use for resolving references to other generated artifacts.
	 * @param copyrightHeader Header with copyright for the source.
	 * @param pkg Package where the converter is located.
	 * @param vo Value object to generate the converter for.
	 * @param targetType External base type.
	 * @param implementsSingleEntityIdFactory TRUE if this is a converter for an entity ID.
	 */
	new(CodeReferenceRegistry refReg, String copyrightHeader, String pkg, AbstractVO vo, ExternalType targetType,
		boolean implementsSingleEntityIdFactory) {

		this.refReg = refReg
		this.copyrightHeader = copyrightHeader
		this.pkg = pkg
		this.voTypeName = vo.name
		this.targetTypeName = targetTypeName
		this.implementsSingleEntityIdFactory = implementsSingleEntityIdFactory
		this.className = voTypeName + "Converter"

		ctx = new SimpleCodeSnippetContext()
		ctx.requiresImport("javax.enterprise.context.ApplicationScoped")
		ctx.requiresImport("javax.persistence.AttributeConverter")
		ctx.requiresImport("javax.persistence.Converter")
		ctx.requiresImport("org.fuin.objects4j.common.ThreadSafe")
		ctx.requiresImport("org.fuin.objects4j.vo.AbstractValueObjectConverter")
		if (implementsSingleEntityIdFactory) {
			ctx.requiresImport("org.fuin.ddd4j.ddd.EntityId")
		}
		ctx.requiresReference(vo.uniqueName)
		ctx.requiresReference(targetType.uniqueName)
		ctx.resolve(refReg)

	}

	override toString() {
		'''	
			«copyrightHeader»
			package «pkg»;
			
			«new SrcImports(ctx.imports).toString»
			
			/**
			 * Converts «voTypeName» from/to «targetTypeName».
			 */
			@ThreadSafe
			@ApplicationScoped
			@Converter(autoApply = true)
			public final class «className» extends
					AbstractValueObjectConverter<«targetTypeName», «voTypeName»> implements
					AttributeConverter<«voTypeName», «targetTypeName»>«IF implementsSingleEntityIdFactory», SingleEntityIdFactory«ENDIF» {
			
				@Override
				public Class<«targetTypeName»> getBaseTypeClass() {
					return «targetTypeName».class;
				}
			
				@Override
				public final Class<«voTypeName»> getValueObjectClass() {
					return «voTypeName».class;
				}
			
				@Override
				public final boolean isValid(final «targetTypeName» value) {
					return «voTypeName».isValid(value);
				}
			
				@Override
				public final «voTypeName» toVO(final «targetTypeName» value) {
					return «voTypeName».valueOf(value);
				}
			
				@Override
				public final «targetTypeName» fromVO(final «voTypeName» value) {
					if (value == null) {
						return null;
					}
					return value.asBaseType();
				}
			
				«IF implementsSingleEntityIdFactory»
					@Override
					public final EntityId createEntityId(final String id) {
						return «voTypeName».valueOf(id);
					}
				«ENDIF»
			
			}
		'''
	}

}
