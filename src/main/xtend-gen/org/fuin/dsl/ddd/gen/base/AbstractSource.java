package org.fuin.dsl.ddd.gen.base;

import com.google.common.base.Objects;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractEntity;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractEntityId;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractMethod;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AbstractVO;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Constraint;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Constraints;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Constructor;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Context;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Entity;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Event;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ExternalType;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.InternalType;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Method;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Type;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Variable;
import org.fuin.dsl.ddd.gen.base.SrcMethod;
import org.fuin.dsl.ddd.gen.base.SrcMethodJavaDoc;
import org.fuin.dsl.ddd.gen.base.SrcParamsAssignment;
import org.fuin.dsl.ddd.gen.base.SrcParamsDecl;
import org.fuin.dsl.ddd.gen.base.SrcThrowsExceptions;
import org.fuin.dsl.ddd.gen.extensions.AbstractEntityExtensions;
import org.fuin.dsl.ddd.gen.extensions.CollectionExtensions;
import org.fuin.dsl.ddd.gen.extensions.ConstraintsExtensions;
import org.fuin.dsl.ddd.gen.extensions.EObjectExtensions;
import org.fuin.srcgen4j.commons.ArtifactFactory;
import org.fuin.srcgen4j.commons.ArtifactFactoryConfig;
import org.fuin.srcgen4j.core.emf.CodeSnippetContext;

@SuppressWarnings("all")
public abstract class AbstractSource<T extends Object> implements ArtifactFactory<T> {
  private String artifactName;
  
  private Map<String,String> varMap;
  
  public void init(final ArtifactFactoryConfig config) {
    String _artifact = config.getArtifact();
    this.artifactName = _artifact;
    Map<String,String> _varMap = config.getVarMap();
    this.varMap = _varMap;
  }
  
  public boolean isIncremental() {
    return true;
  }
  
  public String getArtifactName() {
    return this.artifactName;
  }
  
  public String getBasePkg() {
    Map<String,String> _nullSafe = CollectionExtensions.<String, String>nullSafe(this.varMap);
    return _nullSafe.get("basepkg");
  }
  
  public String getPkg() {
    Map<String,String> _nullSafe = CollectionExtensions.<String, String>nullSafe(this.varMap);
    return _nullSafe.get("pkg");
  }
  
  public String getCopyrightHeader() {
    Map<String,String> _nullSafe = CollectionExtensions.<String, String>nullSafe(this.varMap);
    final String header = _nullSafe.get("copyrightHeader");
    boolean _equals = Objects.equal(header, null);
    if (_equals) {
      return "";
    }
    return header;
  }
  
  public String asPackage(final Namespace ns) {
    String _pkg = this.getPkg();
    boolean _equals = Objects.equal(_pkg, null);
    if (_equals) {
      String _basePkg = this.getBasePkg();
      String _plus = (_basePkg + ".");
      Context _context = EObjectExtensions.getContext(ns);
      String _name = _context.getName();
      String _plus_1 = (_plus + _name);
      String _plus_2 = (_plus_1 + ".");
      String _name_1 = ns.getName();
      return (_plus_2 + _name_1);
    }
    String _basePkg_1 = this.getBasePkg();
    String _plus_3 = (_basePkg_1 + ".");
    Context _context_1 = EObjectExtensions.getContext(ns);
    String _name_2 = _context_1.getName();
    String _plus_4 = (_plus_3 + _name_2);
    String _plus_5 = (_plus_4 + ".");
    String _pkg_1 = this.getPkg();
    String _plus_6 = (_plus_5 + _pkg_1);
    String _plus_7 = (_plus_6 + ".");
    String _name_3 = ns.getName();
    return (_plus_7 + _name_3);
  }
  
  public String asJavaType(final Variable variable) {
    Type _type = variable.getType();
    String name = _type.getName();
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(name,"Date")) {
        _matched=true;
        name = "LocalDate";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Time")) {
        _matched=true;
        name = "LocalTime";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Timestamp")) {
        _matched=true;
        name = "LocalDateTime";
      }
    }
    String _multiplicity = variable.getMultiplicity();
    boolean _equals = Objects.equal(_multiplicity, null);
    if (_equals) {
      return name;
    }
    return (("List<" + name) + ">");
  }
  
  public String asJavaPrimitive(final Variable variable) {
    Type _type = variable.getType();
    String name = _type.getName();
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(name,"Byte")) {
        _matched=true;
        name = "byte";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Short")) {
        _matched=true;
        name = "short";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Integer")) {
        _matched=true;
        name = "int";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Long")) {
        _matched=true;
        name = "long";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Float")) {
        _matched=true;
        name = "float";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Double")) {
        _matched=true;
        name = "double";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Boolean")) {
        _matched=true;
        name = "boolean";
      }
    }
    if (!_matched) {
      if (Objects.equal(name,"Character")) {
        _matched=true;
        name = "char";
      }
    }
    String _multiplicity = variable.getMultiplicity();
    boolean _equals = Objects.equal(_multiplicity, null);
    if (_equals) {
      return name;
    }
    return (name + "[]");
  }
  
  public CharSequence _constructorsDecl(final CodeSnippetContext ctx, final InternalType internalType) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Constructor> _constructors = internalType.getConstructors();
      List<Constructor> _nullSafe = CollectionExtensions.<Constructor>nullSafe(_constructors);
      for(final Constructor constructor : _nullSafe) {
        String _name = internalType.getName();
        EList<Variable> _variables = constructor.getVariables();
        Constraints _constraints = constructor.getConstraints();
        CharSequence __constructorDecl = this._constructorDecl(ctx, _name, _variables, _constraints);
        _builder.append(__constructorDecl, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence _constructorsDecl(final CodeSnippetContext ctx, final AbstractVO vo) {
    CharSequence _xifexpression = null;
    EList<Constructor> _constructors = vo.getConstructors();
    List<Constructor> _nullSafe = CollectionExtensions.<Constructor>nullSafe(_constructors);
    int _size = _nullSafe.size();
    boolean _equals = (_size == 0);
    if (_equals) {
      String _name = vo.getName();
      EList<Variable> _variables = vo.getVariables();
      _xifexpression = this._constructorDecl(ctx, _name, _variables, null);
    } else {
      _xifexpression = this._constructorsDecl(ctx, ((InternalType) vo));
    }
    return _xifexpression;
  }
  
  public CharSequence _constructorDecl(final CodeSnippetContext ctx, final String internalTypeName, final List<Variable> variables, final Constraints constraints) {
    StringConcatenation _builder = new StringConcatenation();
    List<Constraint> _list = ConstraintsExtensions.list(constraints);
    SrcMethodJavaDoc _srcMethodJavaDoc = new SrcMethodJavaDoc(ctx, "Constructor with all data.", variables, _list);
    _builder.append(_srcMethodJavaDoc, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public ");
    _builder.append(internalTypeName, "");
    _builder.append("(");
    List<Variable> _nullSafe = CollectionExtensions.<Variable>nullSafe(variables);
    SrcParamsDecl _srcParamsDecl = new SrcParamsDecl(ctx, _nullSafe);
    _builder.append(_srcParamsDecl, "");
    _builder.append(") ");
    List<org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception> _exceptionList = ConstraintsExtensions.exceptionList(constraints);
    SrcThrowsExceptions _srcThrowsExceptions = new SrcThrowsExceptions(ctx, _exceptionList);
    _builder.append(_srcThrowsExceptions, "");
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super();");
    _builder.newLine();
    _builder.append("\t");
    List<Variable> _nullSafe_1 = CollectionExtensions.<Variable>nullSafe(variables);
    SrcParamsAssignment _srcParamsAssignment = new SrcParamsAssignment(ctx, _nullSafe_1);
    _builder.append(_srcParamsAssignment, "\t");
    _builder.append("\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence _methodsDecl(final CodeSnippetContext ctx, final InternalType internalType) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Method> _methods = internalType.getMethods();
      List<Method> _nullSafe = CollectionExtensions.<Method>nullSafe(_methods);
      for(final Method method : _nullSafe) {
        SrcMethod _srcMethod = new SrcMethod(ctx, "public final", false, method);
        _builder.append(_srcMethod, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence _eventAbstractMethodsDecl(final AbstractEntity entity) {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<AbstractMethod> _constructorsAndMethods = AbstractEntityExtensions.constructorsAndMethods(entity);
      for(final AbstractMethod method : _constructorsAndMethods) {
        EList<Event> _events = method.getEvents();
        CharSequence __eventAbstractMethods = this._eventAbstractMethods(_events);
        _builder.append(__eventAbstractMethods, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence _eventAbstractMethods(final List<Event> events) {
    CharSequence _xblockexpression = null;
    {
      boolean _equals = Objects.equal(events, null);
      if (_equals) {
        return "";
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        for(final Event event : events) {
          CharSequence __eventAbstractMethod = this._eventAbstractMethod(event);
          _builder.append(__eventAbstractMethod, "");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence _eventAbstractMethod(final Event event) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Handles: ");
    String _name = event.getName();
    _builder.append(_name, " ");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param event Event to handle.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected abstract void handle(final ");
    String _name_1 = event.getName();
    _builder.append(_name_1, "");
    _builder.append(" event);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence _eventMethodsDecl(final AbstractEntity entity) {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<AbstractMethod> _constructorsAndMethods = AbstractEntityExtensions.constructorsAndMethods(entity);
      for(final AbstractMethod method : _constructorsAndMethods) {
        EList<Event> _events = method.getEvents();
        CharSequence __eventMethods = this._eventMethods(_events);
        _builder.append(__eventMethods, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence _eventMethods(final List<Event> events) {
    CharSequence _xblockexpression = null;
    {
      boolean _equals = Objects.equal(events, null);
      if (_equals) {
        return "";
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        for(final Event event : events) {
          CharSequence __eventMethod = this._eventMethod(event);
          _builder.append(__eventMethod, "");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence _eventMethod(final Event event) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("@EventHandler");
    _builder.newLine();
    _builder.append("protected final void handle(final ");
    String _name = event.getName();
    _builder.append(_name, "");
    _builder.append(" event) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// TODO Handle event!");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String optionalExtendsForBase(final String typeName, final ExternalType base) {
    boolean _equals = Objects.equal(base, null);
    if (_equals) {
      return "";
    }
    String _name = base.getName();
    boolean _equals_1 = _name.equals("String");
    if (_equals_1) {
      return "extends AbstractStringValueObject ";
    }
    String _name_1 = base.getName();
    boolean _equals_2 = _name_1.equals("UUID");
    if (_equals_2) {
      return "extends AbstractUUIDVO ";
    }
    String _name_2 = base.getName();
    boolean _equals_3 = _name_2.equals("Integer");
    if (_equals_3) {
      return "extends AbstractIntegerValueObject ";
    }
    String _name_3 = base.getName();
    boolean _equals_4 = _name_3.equals("Long");
    if (_equals_4) {
      return "extends AbstractLongValueObject ";
    }
    return "";
  }
  
  public CharSequence _optionalBaseMethods(final String typeName, final ExternalType base) {
    boolean _equals = Objects.equal(base, null);
    if (_equals) {
      return "";
    }
    String _name = base.getName();
    boolean _equals_1 = _name.equals("String");
    if (_equals_1) {
      return this._optionalBaseMethodsString(typeName);
    }
    String _name_1 = base.getName();
    boolean _equals_2 = _name_1.equals("UUID");
    if (_equals_2) {
      return this._optionalBaseMethodsUUID(typeName);
    }
    boolean _or = false;
    String _name_2 = base.getName();
    boolean _equals_3 = _name_2.equals("Integer");
    if (_equals_3) {
      _or = true;
    } else {
      String _name_3 = base.getName();
      boolean _equals_4 = _name_3.equals("Long");
      _or = _equals_4;
    }
    if (_or) {
      String _name_4 = base.getName();
      return this._optionalBaseMethodsNumber(typeName, _name_4);
    }
    return "";
  }
  
  public CharSequence _optionalBaseMethodsNumber(final String typeName, final String baseName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Returns the information if a given ");
    _builder.append(baseName, " ");
    _builder.append(" can be converted into");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* an instance of this class. A <code>null</code> value returns <code>true</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to check.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return TRUE if it\'s a valid ");
    _builder.append(baseName, " ");
    _builder.append(", else FALSE.");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static boolean isValid(final ");
    _builder.append(baseName, "");
    _builder.append(" value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (value == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(baseName, "\t\t");
    _builder.append(".valueOf(value);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("} catch (final NumberFormatException ex) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Parses a given ");
    _builder.append(baseName, " ");
    _builder.append(" and returns a new instance of this class.");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to convert. A <code>null</code> value returns");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            <code>null</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return Converted value.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static ");
    _builder.append(typeName, "");
    _builder.append(" valueOf(final ");
    _builder.append(baseName, "");
    _builder.append(" value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (value == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return new ");
    _builder.append(typeName, "\t");
    _builder.append("(value);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Integer asBaseType() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return val;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public String asString() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"\" + val;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Parses a given String and returns a new instance of this class.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to convert. A <code>null</code> value returns");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            <code>null</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return Converted value.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static ");
    _builder.append(typeName, "");
    _builder.append(" valueOf(final String value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (value == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return new ");
    _builder.append(typeName, "\t");
    _builder.append("(");
    _builder.append(baseName, "\t");
    _builder.append(".valueOf(value));");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence _optionalBaseMethodsString(final String typeName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Returns the information if a given string can be converted into");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* an instance of this class. A <code>null</code> value returns <code>true</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to check.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return TRUE if it\'s a valid string, else FALSE.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static boolean isValid(final String value) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (value == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Verify the value is valid!");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Parses a given string and returns a new instance of this class.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to convert. A <code>null</code> value returns");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            <code>null</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return Converted value.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static ");
    _builder.append(typeName, "");
    _builder.append(" valueOf(final String value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (value == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Parse string value and return new instance! ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// return new ");
    _builder.append(typeName, "\t");
    _builder.append("(value);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence _optionalBaseMethodsUUID(final String typeName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Returns the information if a given string can be converted into");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* an instance of this class. A <code>null</code> value returns <code>true</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to check.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return TRUE if it\'s a valid string, else FALSE.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static boolean isValid(final String value) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return UUIDStrValidator.isValid(value);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Parses a given string and returns a new instance of this class.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param value");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            Value to convert. A <code>null</code> value returns");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            <code>null</code>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return Converted value.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static ");
    _builder.append(typeName, "");
    _builder.append(" valueOf(final String value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (value == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return new ");
    _builder.append(typeName, "\t");
    _builder.append("(UUID.fromString(value));");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates source for a protected default constructor
   * if no other constructor with no arguments exists.
   * 
   * @param internalType Type to optionally generate the default constructor for.
   */
  public String _optionalDeserializationConstructor(final InternalType internalType) {
    String _xblockexpression = null;
    {
      EList<Constructor> _constructors = internalType.getConstructors();
      List<Constructor> _nullSafe = CollectionExtensions.<Constructor>nullSafe(_constructors);
      for (final Constructor constructor : _nullSafe) {
        boolean _or = false;
        boolean _equals = Objects.equal(constructor, null);
        if (_equals) {
          _or = true;
        } else {
          EList<Variable> _variables = constructor.getVariables();
          List<Variable> _nullSafe_1 = CollectionExtensions.<Variable>nullSafe(_variables);
          int _size = _nullSafe_1.size();
          boolean _equals_1 = (_size == 0);
          _or = _equals_1;
        }
        if (_or) {
          return "";
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* Default constructor only for de-serialization.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("protected ");
      String _name = internalType.getName();
      _builder.append(_name, "");
      _builder.append("() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("super();");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public CharSequence _abstractChildEntityLocatorMethods(final AbstractEntity parent) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Set<Entity> _childEntities = AbstractEntityExtensions.childEntities(parent);
      for(final Entity child : _childEntities) {
        CharSequence __abstractChildEntityLocatorMethod = this._abstractChildEntityLocatorMethod(child);
        _builder.append(__abstractChildEntityLocatorMethod, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence _abstractChildEntityLocatorMethod(final AbstractEntity entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Locates the child entity of type ");
    String _name = entity.getName();
    _builder.append(_name, " ");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param ");
    AbstractEntityId _idType = entity.getIdType();
    String _name_1 = _idType.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_1);
    _builder.append(_firstLower, " ");
    _builder.append(" Unique identifier of the child entity to find.");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return Child entity or NULL if no entity with the given identifier was found.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected abstract ");
    String _name_2 = entity.getName();
    _builder.append(_name_2, "");
    _builder.append(" find");
    String _name_3 = entity.getName();
    _builder.append(_name_3, "");
    _builder.append("(final ");
    AbstractEntityId _idType_1 = entity.getIdType();
    String _name_4 = _idType_1.getName();
    _builder.append(_name_4, "");
    _builder.append(" ");
    AbstractEntityId _idType_2 = entity.getIdType();
    String _name_5 = _idType_2.getName();
    String _firstLower_1 = StringExtensions.toFirstLower(_name_5);
    _builder.append(_firstLower_1, "");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence _childEntityLocatorMethods(final AbstractEntity parent) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Set<Entity> _childEntities = AbstractEntityExtensions.childEntities(parent);
      for(final Entity child : _childEntities) {
        CharSequence __childEntityLocatorMethod = this._childEntityLocatorMethod(child);
        _builder.append(__childEntityLocatorMethod, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence _childEntityLocatorMethod(final AbstractEntity entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("@ChildEntityLocator");
    _builder.newLine();
    _builder.append("protected final ");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append(" find");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "");
    _builder.append("(final ");
    AbstractEntityId _idType = entity.getIdType();
    String _name_2 = _idType.getName();
    _builder.append(_name_2, "");
    _builder.append(" ");
    AbstractEntityId _idType_1 = entity.getIdType();
    String _name_3 = _idType_1.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_3);
    _builder.append(_firstLower, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// TODO Implement!");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence _uniquelyNumberedException(final org.fuin.dsl.ddd.domainDrivenDesignDsl.Exception ex) {
    CharSequence _xifexpression = null;
    int _cid = ex.getCid();
    boolean _greaterThan = (_cid > 0);
    if (_greaterThan) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("UniquelyNumberedException");
      _xifexpression = _builder;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Exception");
      _xifexpression = _builder_1;
    }
    return _xifexpression;
  }
}
