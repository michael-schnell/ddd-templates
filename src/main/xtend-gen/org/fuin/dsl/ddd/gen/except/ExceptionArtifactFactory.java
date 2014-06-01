package org.fuin.dsl.ddd.gen.except;

import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Variable;
import org.fuin.dsl.ddd.gen.base.AbstractSource;
import org.fuin.srcgen4j.commons.GenerateException;
import org.fuin.srcgen4j.commons.GeneratedArtifact;

@SuppressWarnings("all")
public class ExceptionArtifactFactory extends AbstractSource<org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception> {
  public Class<? extends org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception> getModelType() {
    return org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception.class;
  }
  
  public GeneratedArtifact create(final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex) throws GenerateException {
    try {
      EObject _eContainer = ex.eContainer();
      final Namespace ns = ((Namespace) _eContainer);
      String _asPackage = this.asPackage(ns);
      String _plus = (_asPackage + ".");
      String _name = ex.getName();
      String _plus_1 = (_plus + _name);
      String _replace = _plus_1.replace(".", "/");
      final String filename = (_replace + ".java");
      String _artifactName = this.getArtifactName();
      CharSequence _create = this.create(ex, ns);
      String _string = _create.toString();
      byte[] _bytes = _string.getBytes("UTF-8");
      return new GeneratedArtifact(_artifactName, filename, _bytes);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence create(final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex, final Namespace ns) {
    StringConcatenation _builder = new StringConcatenation();
    String _copyrightHeader = this.getCopyrightHeader();
    _builder.append(_copyrightHeader, "");
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    String _asPackage = this.asPackage(ns);
    _builder.append(_asPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.fuin.objects4j.vo.KeyValue;");
    _builder.newLine();
    CharSequence __imports = this._imports(ex);
    _builder.append(__imports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    String _doc = ex.getDoc();
    String _text = this.text(_doc);
    _builder.append(_text, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public final class ");
    String _name = ex.getName();
    _builder.append(_name, "");
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
    CharSequence __varsDecl = this._varsDecl(ex);
    _builder.append(__varsDecl, "\t");
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
        String _name_1 = v.getName();
        _builder.append(_name_1, "\t");
        _builder.append(" ");
        String _superDoc = this.superDoc(v);
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
    String _name_2 = ex.getName();
    _builder.append(_name_2, "\t");
    _builder.append("(");
    EList<Variable> _variables_1 = ex.getVariables();
    CharSequence __paramsDecl = this._paramsDecl(_variables_1);
    _builder.append(__paramsDecl, "\t");
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
          _builder.appendImmediate(",", "\t\t\t");
        }
        _builder.append("\t\t\t");
        _builder.append("new KeyValue(\"");
        String _name_3 = v_1.getName();
        _builder.append(_name_3, "\t\t\t");
        _builder.append("\", ");
        String _name_4 = v_1.getName();
        _builder.append(_name_4, "\t\t\t");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("));");
    _builder.newLine();
    _builder.append("\t\t");
    EList<Variable> _variables_3 = ex.getVariables();
    CharSequence __paramsAssignment = this._paramsAssignment(_variables_3);
    _builder.append(__paramsAssignment, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    EList<Variable> _variables_4 = ex.getVariables();
    CharSequence __getters = this._getters("public final", _variables_4);
    _builder.append(__getters, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence _varsDecl(final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Variable> _variables = ex.getVariables();
      List<Variable> _nullSafe = this.<Variable>nullSafe(_variables);
      for(final Variable variable : _nullSafe) {
        CharSequence __varDecl = this._varDecl(variable);
        _builder.append(__varDecl, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder;
  }
}