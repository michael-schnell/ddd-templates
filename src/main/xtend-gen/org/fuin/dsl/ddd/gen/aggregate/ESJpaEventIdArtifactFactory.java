package org.fuin.dsl.ddd.gen.aggregate;

import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AggregateId;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.gen.base.AbstractSource;
import org.fuin.dsl.ddd.gen.base.SrcAll;
import org.fuin.dsl.ddd.gen.base.Utils;
import org.fuin.dsl.ddd.gen.extensions.AbstractElementExtensions;
import org.fuin.srcgen4j.commons.ArtifactFactory;
import org.fuin.srcgen4j.commons.GenerateException;
import org.fuin.srcgen4j.commons.GeneratedArtifact;
import org.fuin.srcgen4j.core.emf.CodeReferenceRegistry;
import org.fuin.srcgen4j.core.emf.CodeSnippetContext;
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext;

@SuppressWarnings("all")
public class ESJpaEventIdArtifactFactory extends AbstractSource<Aggregate> implements ArtifactFactory<Aggregate> {
  public Class<? extends Aggregate> getModelType() {
    return Aggregate.class;
  }
  
  public GeneratedArtifact create(final Aggregate aggregate, final Map<String,Object> context, final boolean preparationRun) throws GenerateException {
    try {
      String _name = aggregate.getName();
      final String className = (_name + "EventId");
      EObject _eContainer = aggregate.eContainer();
      final Namespace ns = ((Namespace) _eContainer);
      final String pkg = this.asPackage(ns);
      String _name_1 = aggregate.getName();
      final String fqn = ((pkg + ".") + _name_1);
      String _replace = fqn.replace(".", "/");
      final String filename = (_replace + ".java");
      final CodeReferenceRegistry refReg = Utils.getCodeReferenceRegistry(context);
      String _uniqueName = AbstractElementExtensions.uniqueName(aggregate);
      String _plus = (_uniqueName + "EventId");
      refReg.putReference(_plus, fqn);
      if (preparationRun) {
        return null;
      }
      final SimpleCodeSnippetContext ctx = new SimpleCodeSnippetContext(refReg);
      this.addImports(ctx);
      this.addReferences(ctx, aggregate);
      String _artifactName = this.getArtifactName();
      String _create = this.create(ctx, aggregate, pkg, className);
      String _string = _create.toString();
      byte[] _bytes = _string.getBytes("UTF-8");
      return new GeneratedArtifact(_artifactName, filename, _bytes);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void addImports(final CodeSnippetContext ctx) {
    ctx.requiresImport("java.io.Serializable");
  }
  
  public void addReferences(final CodeSnippetContext ctx, final Aggregate aggregate) {
    AggregateId _idType = aggregate.getIdType();
    String _uniqueAbstractName = AbstractElementExtensions.uniqueAbstractName(_idType);
    ctx.requiresReference(_uniqueAbstractName);
  }
  
  public String create(final SimpleCodeSnippetContext ctx, final Aggregate aggregate, final String pkg, final String className) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* Identifies a stream event based on a string representation of the aggregate identifier and a version number.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("public class ");
      _builder.append(className, "");
      _builder.append(" implements Serializable {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("private static final long serialVersionUID = 0L;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("private String ");
      String _name = aggregate.getName();
      String _firstLower = StringExtensions.toFirstLower(_name);
      _builder.append(_firstLower, "    ");
      _builder.append("Id;");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("private Integer eventNumber;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Default constructor for JPA. <b><i>CAUTION:</i> DO NOT USE IN APPLICATION CODE.</b>");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public ");
      String _name_1 = aggregate.getName();
      _builder.append(_name_1, "    ");
      _builder.append("EventId() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("super();");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Constructor with all required data.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @param ");
      String _name_2 = aggregate.getName();
      String _firstLower_1 = StringExtensions.toFirstLower(_name_2);
      _builder.append(_firstLower_1, "     ");
      _builder.append("Id");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("*            Unique name.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @param eventNumber");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*            Number of the event within the stream.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public ");
      String _name_3 = aggregate.getName();
      _builder.append(_name_3, "    ");
      _builder.append("EventId(@NotNull final ");
      String _name_4 = aggregate.getName();
      _builder.append(_name_4, "    ");
      _builder.append("Id ");
      String _name_5 = aggregate.getName();
      String _firstLower_2 = StringExtensions.toFirstLower(_name_5);
      _builder.append(_firstLower_2, "    ");
      _builder.append("Id, @NotNull final Integer eventNumber) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("super();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Contract.requireArgNotNull(\"");
      String _name_6 = aggregate.getName();
      String _firstLower_3 = StringExtensions.toFirstLower(_name_6);
      _builder.append(_firstLower_3, "\t\t");
      _builder.append("Id\", ");
      String _name_7 = aggregate.getName();
      String _firstLower_4 = StringExtensions.toFirstLower(_name_7);
      _builder.append(_firstLower_4, "\t\t");
      _builder.append("Id);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("Contract.requireArgNotNull(\"nueventNumbermber\", eventNumber);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.");
      String _name_8 = aggregate.getName();
      String _firstLower_5 = StringExtensions.toFirstLower(_name_8);
      _builder.append(_firstLower_5, "\t\t");
      _builder.append("Id = ");
      String _name_9 = aggregate.getName();
      String _firstLower_6 = StringExtensions.toFirstLower(_name_9);
      _builder.append(_firstLower_6, "\t\t");
      _builder.append("Id.asString();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("this.eventNumber = eventNumber;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Returns the aggregate ID.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @return Unique aggregate identifier.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@NeverNull");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public String get");
      String _name_10 = aggregate.getName();
      _builder.append(_name_10, "    ");
      _builder.append("Id() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("return ");
      String _name_11 = aggregate.getName();
      String _firstLower_7 = StringExtensions.toFirstLower(_name_11);
      _builder.append(_firstLower_7, "\t\t");
      _builder.append("Id;");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Returns the number of the event within the stream.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @return Order of the event in the stream.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@NeverNull");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public Integer getEventNumber() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return eventNumber;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("// CHECKSTYLE:OFF Generated code");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public int hashCode() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("final int prime = 31;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("int result = 1;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("result = prime * result\t+ ((");
      String _name_12 = aggregate.getName();
      String _firstLower_8 = StringExtensions.toFirstLower(_name_12);
      _builder.append(_firstLower_8, "\t\t");
      _builder.append("Id == null) ? 0 : ");
      String _name_13 = aggregate.getName();
      String _firstLower_9 = StringExtensions.toFirstLower(_name_13);
      _builder.append(_firstLower_9, "\t\t");
      _builder.append("Id.hashCode());");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("result = prime * result\t+ ((eventNumber == null) ? 0 : eventNumber.hashCode());");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return result;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public boolean equals(Object obj) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (this == obj)");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return true;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (obj == null)");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (getClass() != obj.getClass())");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t\t");
      String _name_14 = aggregate.getName();
      _builder.append(_name_14, "\t\t");
      _builder.append("EventId other = (");
      String _name_15 = aggregate.getName();
      _builder.append(_name_15, "\t\t");
      _builder.append("EventId) obj;");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("if (");
      String _name_16 = aggregate.getName();
      String _firstLower_10 = StringExtensions.toFirstLower(_name_16);
      _builder.append(_firstLower_10, "\t\t");
      _builder.append("Id == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("if (other.");
      String _name_17 = aggregate.getName();
      String _firstLower_11 = StringExtensions.toFirstLower(_name_17);
      _builder.append(_firstLower_11, "    ");
      _builder.append("Id != null)");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("} else if (!");
      String _name_18 = aggregate.getName();
      String _firstLower_12 = StringExtensions.toFirstLower(_name_18);
      _builder.append(_firstLower_12, "\t\t");
      _builder.append("Id.equals(other.");
      String _name_19 = aggregate.getName();
      String _firstLower_13 = StringExtensions.toFirstLower(_name_19);
      _builder.append(_firstLower_13, "\t\t");
      _builder.append("Id))");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (eventNumber == null) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("if (other.eventNumber != null)");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("} else if (!eventNumber.equals(other.eventNumber))");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return true;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("// CHECKSTYLE:ON");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public String toString() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return ");
      String _name_20 = aggregate.getName();
      String _firstLower_14 = StringExtensions.toFirstLower(_name_20);
      _builder.append(_firstLower_14, "\t\t");
      _builder.append("Id + \"-\" + eventNumber;");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String src = _builder.toString();
      String _copyrightHeader = this.getCopyrightHeader();
      Set<String> _imports = ctx.getImports();
      SrcAll _srcAll = new SrcAll(_copyrightHeader, pkg, _imports, src);
      _xblockexpression = _srcAll.toString();
    }
    return _xblockexpression;
  }
}
