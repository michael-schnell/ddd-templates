package org.fuin.dsl.ddd.gen.base;

import java.net.URL;
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
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Entity;
import org.fuin.dsl.ddd.gen.base.SrcChildEntityLocatorMethod;
import org.fuin.dsl.ddd.gen.base.Utils;
import org.fuin.dsl.ddd.gen.extensions.DomainModelExtensions;
import org.fuin.srcgen4j.core.emf.SimpleCodeReferenceRegistry;
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(DomainDrivenDesignDslInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class SrcChildEntityLocatorMethodTest {
  @Inject
  private ParseHelper<DomainModel> parser;
  
  @Test
  public void testCreate() {
    final SimpleCodeReferenceRegistry refReg = new SimpleCodeReferenceRegistry();
    refReg.putReference("x.a.MyEntity", "a.b.c.MyEntity");
    refReg.putReference("x.a.MyEntityId", "a.b.c.MyEntityId");
    final SimpleCodeSnippetContext ctx = new SimpleCodeSnippetContext(refReg);
    DomainModel _createModel = this.createModel();
    final Entity entity = DomainModelExtensions.<Entity>find(_createModel, Entity.class, "MyEntity");
    final SrcChildEntityLocatorMethod testee = new SrcChildEntityLocatorMethod(ctx, entity);
    final String result = testee.toString();
    StringAssert _assertThat = Assertions.assertThat(result);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("@ChildEntityLocator");
    _builder.newLine();
    _builder.append("protected final MyEntity findMyEntity(@NotNull final MyEntityId myEntityId) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Implement!");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _assertThat.isEqualTo(_builder.toString());
    Set<String> _imports = ctx.getImports();
    CollectionAssert _assertThat_1 = Assertions.assertThat(_imports);
    _assertThat_1.containsOnly("javax.validation.constraints.NotNull", 
      "org.fuin.ddd4j.ddd.ChildEntityLocator", "a.b.c.MyEntity", "a.b.c.MyEntityId");
  }
  
  private DomainModel createModel() {
    try {
      Class<? extends SrcChildEntityLocatorMethodTest> _class = this.getClass();
      URL _resource = _class.getResource("/example1.ddd");
      String _readAsString = Utils.readAsString(_resource);
      return this.parser.parse(_readAsString);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
