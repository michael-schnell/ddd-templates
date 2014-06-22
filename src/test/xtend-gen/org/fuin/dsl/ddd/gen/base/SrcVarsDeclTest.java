package org.fuin.dsl.ddd.gen.base;

import java.util.Set;
import javax.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.fest.assertions.Assertions;
import org.fest.assertions.CollectionAssert;
import org.fest.assertions.StringAssert;
import org.fuin.dsl.ddd.DomainDrivenDesignDslInjectorProvider;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.DomainModel;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ValueObject;
import org.fuin.dsl.ddd.gen.base.SrcVarsDecl;
import org.fuin.dsl.ddd.gen.extensions.DomainModelExtensions;
import org.fuin.srcgen4j.core.emf.SimpleCodeReferenceRegistry;
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(DomainDrivenDesignDslInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class SrcVarsDeclTest {
  @Inject
  private ParseHelper<DomainModel> parser;
  
  @Test
  public void testCreate() {
    final SimpleCodeReferenceRegistry refReg = new SimpleCodeReferenceRegistry();
    refReg.putReference("a.b.String", "java.lang.String");
    refReg.putReference("a.b.Integer", "java.lang.Integer");
    refReg.putReference("a.b.Boolean", "java.lang.Boolean");
    final SimpleCodeSnippetContext ctx = new SimpleCodeSnippetContext(refReg);
    DomainModel _createModel = this.createModel();
    final ValueObject valueObject = DomainModelExtensions.<ValueObject>find(_createModel, ValueObject.class, "MyValueObject");
    final SrcVarsDecl testee = new SrcVarsDecl(ctx, "private", false, valueObject);
    final String result = testee.toString();
    StringAssert _assertThat = Assertions.assertThat(result);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@NotNull");
    _builder.newLine();
    _builder.append("private String a;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@NotNull");
    _builder.newLine();
    _builder.append("private Integer b;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("private Boolean c;");
    _builder.newLine();
    _builder.newLine();
    _assertThat.isEqualTo(_builder.toString());
    Set<String> _imports = ctx.getImports();
    CollectionAssert _assertThat_1 = Assertions.assertThat(_imports);
    _assertThat_1.containsOnly("java.lang.String", "java.lang.Integer", "java.lang.Boolean", 
      "javax.validation.constraints.NotNull");
  }
  
  private DomainModel createModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("context a {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("namespace b {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("type String");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("type Integer");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("type Boolean");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("value-object MyValueObject {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("String a");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("Integer b");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("nullable Boolean c");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return this.parser.parse(_builder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}