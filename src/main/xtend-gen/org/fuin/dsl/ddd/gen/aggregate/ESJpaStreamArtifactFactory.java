package org.fuin.dsl.ddd.gen.aggregate;

import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AggregateId;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.gen.base.AbstractSource;
import org.fuin.dsl.ddd.gen.base.SrcAll;
import org.fuin.dsl.ddd.gen.base.Utils;
import org.fuin.dsl.ddd.gen.extensions.AbstractElementExtensions;
import org.fuin.dsl.ddd.gen.extensions.StringExtensions;
import org.fuin.srcgen4j.commons.ArtifactFactory;
import org.fuin.srcgen4j.commons.GenerateException;
import org.fuin.srcgen4j.commons.GeneratedArtifact;
import org.fuin.srcgen4j.core.emf.CodeReferenceRegistry;
import org.fuin.srcgen4j.core.emf.CodeSnippetContext;
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext;

@SuppressWarnings("all")
public class ESJpaStreamArtifactFactory extends AbstractSource<Aggregate> implements ArtifactFactory<Aggregate> {
  public Class<? extends Aggregate> getModelType() {
    return Aggregate.class;
  }
  
  public GeneratedArtifact create(final Aggregate aggregate, final Map<String,Object> context, final boolean preparationRun) throws GenerateException {
    try {
      String _name = aggregate.getName();
      final String className = (_name + "Stream");
      EObject _eContainer = aggregate.eContainer();
      final Namespace ns = ((Namespace) _eContainer);
      final String pkg = this.asPackage(ns);
      String _name_1 = aggregate.getName();
      final String fqn = ((pkg + ".") + _name_1);
      String _replace = fqn.replace(".", "/");
      final String filename = (_replace + ".java");
      final CodeReferenceRegistry refReg = Utils.getCodeReferenceRegistry(context);
      String _uniqueName = AbstractElementExtensions.uniqueName(aggregate);
      String _plus = (_uniqueName + "Stream");
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
    ctx.requiresImport("javax.persistence.Entity");
    ctx.requiresImport("javax.persistence.Id");
    ctx.requiresImport("javax.persistence.Table");
  }
  
  public void addReferences(final CodeSnippetContext ctx, final Aggregate aggregate) {
    AggregateId _idType = aggregate.getIdType();
    String _uniqueName = AbstractElementExtensions.uniqueName(_idType);
    ctx.requiresReference(_uniqueName);
  }
  
  public String create(final SimpleCodeSnippetContext ctx, final Aggregate aggregate, final String pkg, final String className) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* ");
      String _name = aggregate.getName();
      _builder.append(_name, " ");
      _builder.append(" stream.");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("@Table(name = \"");
      String _name_1 = aggregate.getName();
      String _sqlUpper = StringExtensions.toSqlUpper(_name_1);
      _builder.append(_sqlUpper, "");
      _builder.append("_STREAMS\")");
      _builder.newLineIfNotEmpty();
      _builder.append("@Entity");
      _builder.newLine();
      _builder.append("public class ");
      _builder.append(className, "");
      _builder.append(" extends Stream {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Id");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@NotNull");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Column(name = \"");
      String _name_2 = aggregate.getName();
      String _sqlUpper_1 = StringExtensions.toSqlUpper(_name_2);
      _builder.append(_sqlUpper_1, "    ");
      _builder.append("_ID\")");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("private String ");
      String _name_3 = aggregate.getName();
      String _firstLower = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_3);
      _builder.append(_firstLower, "    ");
      _builder.append("Id;");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("private transient ");
      String _name_4 = aggregate.getName();
      _builder.append(_name_4, "    ");
      _builder.append("Id id;");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Protected default constructor for JPA.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("protected ");
      _builder.append(className, "    ");
      _builder.append("() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("super();");
      _builder.newLine();
      _builder.append("  \t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Constructor with mandatory data.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @param ");
      String _name_5 = aggregate.getName();
      String _firstLower_1 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_5);
      _builder.append(_firstLower_1, "     ");
      _builder.append("Id");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("*            Unique ");
      String _name_6 = aggregate.getName();
      String _firstLower_2 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_6);
      _builder.append(_firstLower_2, "     ");
      _builder.append(" identifier.");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public ");
      _builder.append(className, "    ");
      _builder.append("(@NotNull final ");
      String _name_7 = aggregate.getName();
      _builder.append(_name_7, "    ");
      _builder.append("Id ");
      String _name_8 = aggregate.getName();
      String _firstLower_3 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_8);
      _builder.append(_firstLower_3, "    ");
      _builder.append("Id) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("super();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Contract.requireArgNotNull(\"");
      String _name_9 = aggregate.getName();
      String _firstLower_4 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_9);
      _builder.append(_firstLower_4, "\t\t");
      _builder.append("Id\", ");
      String _name_10 = aggregate.getName();
      String _firstLower_5 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_10);
      _builder.append(_firstLower_5, "\t\t");
      _builder.append("Id);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("this.");
      String _name_11 = aggregate.getName();
      String _firstLower_6 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_11);
      _builder.append(_firstLower_6, "\t\t");
      _builder.append("Id = ");
      String _name_12 = aggregate.getName();
      String _firstLower_7 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_12);
      _builder.append(_firstLower_7, "\t\t");
      _builder.append("Id.asString();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("this.id = ");
      String _name_13 = aggregate.getName();
      String _firstLower_8 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_13);
      _builder.append(_firstLower_8, "\t\t");
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
      _builder.append("* Returns the unique ");
      String _name_14 = aggregate.getName();
      String _firstLower_9 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_14);
      _builder.append(_firstLower_9, "     ");
      _builder.append(" identifier as string.");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @return ");
      String _name_15 = aggregate.getName();
      _builder.append(_name_15, "     ");
      _builder.append(" identifier.");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public final String get");
      String _name_16 = aggregate.getName();
      _builder.append(_name_16, "    ");
      _builder.append("Id() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("return ");
      String _name_17 = aggregate.getName();
      String _firstLower_10 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_17);
      _builder.append(_firstLower_10, "\t\t");
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
      _builder.append("* Returns the ");
      String _name_18 = aggregate.getName();
      String _firstLower_11 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_18);
      _builder.append(_firstLower_11, "     ");
      _builder.append(" identifier.");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @return Name converted into a ");
      String _name_19 = aggregate.getName();
      String _firstLower_12 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_19);
      _builder.append(_firstLower_12, "     ");
      _builder.append(" ID.");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public final ");
      String _name_20 = aggregate.getName();
      _builder.append(_name_20, "    ");
      _builder.append("Id getId() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("if (id == null) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("id = ");
      String _name_21 = aggregate.getName();
      _builder.append(_name_21, "    ");
      _builder.append("Id.valueOf(");
      String _name_22 = aggregate.getName();
      String _firstLower_13 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_22);
      _builder.append(_firstLower_13, "    ");
      _builder.append("Id);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return id;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* Creates a container that stores the given event entry.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @param eventEntry");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*            Event entry to convert into a JPA variant.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* @return JPA entity.");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public final StreamEvent createEvent(@NotNull final EventEntry eventEntry) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("incVersion();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return new ");
      String _name_23 = aggregate.getName();
      _builder.append(_name_23, "\t\t");
      _builder.append("Event(getId(), getVersion(), eventEntry);");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public final String toString() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return ");
      String _name_24 = aggregate.getName();
      String _firstLower_14 = org.eclipse.xtext.xbase.lib.StringExtensions.toFirstLower(_name_24);
      _builder.append(_firstLower_14, "\t\t");
      _builder.append("Id;");
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
