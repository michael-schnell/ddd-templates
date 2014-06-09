package org.fuin.dsl.ddd.gen.aggregate;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Aggregate;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.AggregateId;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.gen.base.AbstractSource;
import org.fuin.srcgen4j.commons.ArtifactFactory;
import org.fuin.srcgen4j.commons.GenerateException;
import org.fuin.srcgen4j.commons.GeneratedArtifact;

@SuppressWarnings("all")
public class ESRepositoryArtifactFactory extends AbstractSource<Aggregate> implements ArtifactFactory<Aggregate> {
  public Class<? extends Aggregate> getModelType() {
    return Aggregate.class;
  }
  
  public GeneratedArtifact create(final Aggregate aggregate, final Map<String,Object> context, final boolean preparationRun) throws GenerateException {
    try {
      EObject _eContainer = aggregate.eContainer();
      final Namespace ns = ((Namespace) _eContainer);
      String _asPackage = this.asPackage(ns);
      String _plus = (_asPackage + ".");
      String _name = aggregate.getName();
      String _plus_1 = (_plus + _name);
      String _replace = _plus_1.replace(".", "/");
      final String filename = (_replace + "Repository.java");
      String _artifactName = this.getArtifactName();
      CharSequence _create = this.create(aggregate, ns);
      String _string = _create.toString();
      byte[] _bytes = _string.getBytes("UTF-8");
      return new GeneratedArtifact(_artifactName, filename, _bytes);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence create(final Aggregate aggregate, final Namespace ns) {
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
    _builder.append("import org.fuin.ddd4j.ddd.*;");
    _builder.newLine();
    _builder.append("import org.fuin.ddd4j.esrepo.*;");
    _builder.newLine();
    _builder.append("import org.fuin.ddd4j.eventstore.jpa.*;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Repository that is capable of storing a {@link ");
    String _name = aggregate.getName();
    _builder.append(_name, " ");
    _builder.append("}.");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public final class ");
    String _name_1 = aggregate.getName();
    _builder.append(_name_1, "");
    _builder.append("Repository extends EventStoreRepository<");
    AggregateId _idType = aggregate.getIdType();
    String _name_2 = _idType.getName();
    _builder.append(_name_2, "");
    _builder.append(", ");
    String _name_3 = aggregate.getName();
    _builder.append(_name_3, "");
    _builder.append("> {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Constructor with event store to use as storage.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param eventStore Event store.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param serRegistry Registry used to locate serializers.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param desRegistry Registry used to locate deserializers.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_4 = aggregate.getName();
    _builder.append(_name_4, "\t");
    _builder.append("Repository(final EventStore eventStore,");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("final SerializerRegistry serRegistry, final DeserializerRegistry desRegistry) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super(eventStore, serRegistry, desRegistry);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Class<");
    String _name_5 = aggregate.getName();
    _builder.append(_name_5, "\t");
    _builder.append("> getAggregateClass() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    String _name_6 = aggregate.getName();
    _builder.append(_name_6, "\t\t");
    _builder.append(".class;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public final EntityType getAggregateType() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    AggregateId _idType_1 = aggregate.getIdType();
    String _name_7 = _idType_1.getName();
    _builder.append(_name_7, "\t\t");
    _builder.append(".TYPE;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public final ");
    String _name_8 = aggregate.getName();
    _builder.append(_name_8, "\t");
    _builder.append(" create() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return new ");
    String _name_9 = aggregate.getName();
    _builder.append(_name_9, "\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected final String getIdParamName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"");
    AggregateId _idType_2 = aggregate.getIdType();
    String _name_10 = _idType_2.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_10);
    _builder.append(_firstLower, "\t\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
