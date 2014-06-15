package org.fuin.dsl.ddd.gen.except;

import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Variable;
import org.fuin.dsl.ddd.gen.base.AbstractSource;
import org.fuin.dsl.ddd.gen.base.SrcAll;
import org.fuin.dsl.ddd.gen.base.SrcGetters;
import org.fuin.dsl.ddd.gen.base.SrcParamsAssignment;
import org.fuin.dsl.ddd.gen.base.SrcParamsDecl;
import org.fuin.dsl.ddd.gen.base.SrcVarsDecl;
import org.fuin.dsl.ddd.gen.base.Utils;
import org.fuin.dsl.ddd.gen.extensions.AbstractElementExtensions;
import org.fuin.dsl.ddd.gen.extensions.StringExtensions;
import org.fuin.dsl.ddd.gen.extensions.VariableExtensions;
import org.fuin.srcgen4j.commons.GenerateException;
import org.fuin.srcgen4j.commons.GeneratedArtifact;
import org.fuin.srcgen4j.core.emf.CodeReferenceRegistry;
import org.fuin.srcgen4j.core.emf.CodeSnippetContext;
import org.fuin.srcgen4j.core.emf.SimpleCodeSnippetContext;

@SuppressWarnings("all")
public class ExceptionArtifactFactory extends AbstractSource<org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception> {
  public Class<? extends org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception> getModelType() {
    return org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception.class;
  }
  
  public GeneratedArtifact create(final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex, final Map<String,Object> context, final boolean preparationRun) throws GenerateException {
    try {
      final String className = ex.getName();
      EObject _eContainer = ex.eContainer();
      final Namespace ns = ((Namespace) _eContainer);
      final String pkg = this.asPackage(ns);
      String _name = ex.getName();
      final String fqn = ((pkg + ".") + _name);
      String _replace = fqn.replace(".", "/");
      final String filename = (_replace + ".java");
      final CodeReferenceRegistry refReg = Utils.getCodeReferenceRegistry(context);
      String _uniqueName = AbstractElementExtensions.uniqueName(ex);
      refReg.putReference(_uniqueName, fqn);
      if (preparationRun) {
        return null;
      }
      final SimpleCodeSnippetContext ctx = new SimpleCodeSnippetContext(refReg);
      this.addImports(ctx);
      this.addReferences(ctx, ex);
      String _artifactName = this.getArtifactName();
      String _create = this.create(ctx, ex, pkg, className);
      String _string = _create.toString();
      byte[] _bytes = _string.getBytes("UTF-8");
      return new GeneratedArtifact(_artifactName, filename, _bytes);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void addImports(final CodeSnippetContext ctx) {
    ctx.requiresImport("org.fuin.objects4j.vo.KeyValue");
  }
  
  public Object addReferences(final CodeSnippetContext ctx, final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex) {
    return null;
  }
  
  public String create(final SimpleCodeSnippetContext ctx, final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex, final String pkg, final String className) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* ");
      String _doc = ex.getDoc();
      String _text = StringExtensions.text(_doc);
      _builder.append(_text, " ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("public final class ");
      _builder.append(className, "");
      _builder.append(" extends ");
      CharSequence __uniquelyNumberedException = this._uniquelyNumberedException(ex);
      _builder.append(__uniquelyNumberedException, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private static final long serialVersionUID = 1000L;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      SrcVarsDecl _srcVarsDecl = new SrcVarsDecl(ctx, "private", false, ex);
      _builder.append(_srcVarsDecl, "\t");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("* Constructs a new instance of the exception.");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("*");
      _builder.newLine();
      {
        EList<Variable> _variables = ex.getVariables();
        for(final Variable v : _variables) {
          _builder.append("\t");
          _builder.append("* @param ");
          String _name = v.getName();
          _builder.append(_name, "\t");
          _builder.append(" ");
          String _superDoc = VariableExtensions.superDoc(v);
          _builder.append(_superDoc, "\t");
          _builder.append(" ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      String _name_1 = ex.getName();
      _builder.append(_name_1, "\t");
      _builder.append("(");
      EList<Variable> _variables_1 = ex.getVariables();
      SrcParamsDecl _srcParamsDecl = new SrcParamsDecl(ctx, _variables_1);
      _builder.append(_srcParamsDecl, "\t");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("super(");
      {
        int _cid = ex.getCid();
        boolean _greaterThan = (_cid > 0);
        if (_greaterThan) {
          int _cid_1 = ex.getCid();
          _builder.append(_cid_1, "\t\t");
          _builder.append(", ");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t    ");
      _builder.append("KeyValue.replace(\"");
      String _message = ex.getMessage();
      _builder.append(_message, "\t\t    ");
      _builder.append("\",");
      _builder.newLineIfNotEmpty();
      {
        EList<Variable> _variables_2 = ex.getVariables();
        boolean _hasElements = false;
        for(final Variable v_1 : _variables_2) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(",", "\t\t");
          }
          _builder.append("\t\t");
          _builder.append("new KeyValue(\"");
          String _name_2 = v_1.getName();
          _builder.append(_name_2, "\t\t");
          _builder.append("\", ");
          String _name_3 = v_1.getName();
          _builder.append(_name_3, "\t\t");
          _builder.append(")");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t");
      _builder.append("));");
      _builder.newLine();
      _builder.append("\t\t");
      EList<Variable> _variables_3 = ex.getVariables();
      SrcParamsAssignment _srcParamsAssignment = new SrcParamsAssignment(ctx, _variables_3);
      _builder.append(_srcParamsAssignment, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      EList<Variable> _variables_4 = ex.getVariables();
      SrcGetters _srcGetters = new SrcGetters(ctx, "public final", _variables_4);
      _builder.append(_srcGetters, "\t");
      _builder.newLineIfNotEmpty();
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
