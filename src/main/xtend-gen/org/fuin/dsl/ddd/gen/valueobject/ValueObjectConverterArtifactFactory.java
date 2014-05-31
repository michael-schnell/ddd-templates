package org.fuin.dsl.ddd.gen.valueobject;

import com.google.common.base.Objects;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ExternalType;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.ValueObject;
import org.fuin.dsl.ddd.gen.base.AbstractSource;
import org.fuin.srcgen4j.commons.GenerateException;
import org.fuin.srcgen4j.commons.GeneratedArtifact;

@SuppressWarnings("all")
public class ValueObjectConverterArtifactFactory extends AbstractSource<ValueObject> {
  public Class<? extends ValueObject> getModelType() {
    return ValueObject.class;
  }
  
  public GeneratedArtifact create(final ValueObject valueObject) throws GenerateException {
    try {
      ExternalType _base = valueObject.getBase();
      boolean _equals = Objects.equal(_base, null);
      if (_equals) {
        return null;
      }
      EObject _eContainer = valueObject.eContainer();
      final Namespace ns = ((Namespace) _eContainer);
      String _asPackage = this.asPackage(ns);
      String _plus = (_asPackage + ".");
      String _name = valueObject.getName();
      String _plus_1 = (_plus + _name);
      String _plus_2 = (_plus_1 + "Converter");
      String _replace = _plus_2.replace(".", "/");
      final String filename = (_replace + ".java");
      String _artifactName = this.getArtifactName();
      String _name_1 = valueObject.getName();
      ExternalType _base_1 = valueObject.getBase();
      String _name_2 = _base_1.getName();
      String __valueObjectConverterSource = this._valueObjectConverterSource(ns, _name_1, _name_2, false);
      byte[] _bytes = __valueObjectConverterSource.getBytes("UTF-8");
      return new GeneratedArtifact(_artifactName, filename, _bytes);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
