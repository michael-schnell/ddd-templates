package org.fuin.dsl.ddd.gen.extensions;

import com.google.common.base.Objects;
import org.eclipse.emf.ecore.EObject;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Context;
import org.fuin.dsl.ddd.domainDrivenDesignDsl.Namespace;

/**
 * Provides extension methods for EObject.
 */
@SuppressWarnings("all")
public class EObjectExtensions {
  /**
   * Returns the namespace for an object.
   * 
   * @param obj Object to return the namespace for.
   * 
   * @return Namespace or null if the object is not inside one.
   */
  public static Namespace getNamespace(final EObject obj) {
    boolean _equals = Objects.equal(obj, null);
    if (_equals) {
      return null;
    }
    if ((obj instanceof Namespace)) {
      return ((Namespace)obj);
    }
    EObject _eContainer = obj.eContainer();
    return EObjectExtensions.getNamespace(_eContainer);
  }
  
  /**
   * Returns the context for an object.
   * 
   * @param obj Object to return the context for.
   * 
   * @return Context or null if the object is not inside one.
   */
  public static Context getContext(final EObject obj) {
    boolean _equals = Objects.equal(obj, null);
    if (_equals) {
      return null;
    }
    if ((obj instanceof Context)) {
      return ((Context)obj);
    }
    EObject _eContainer = obj.eContainer();
    return EObjectExtensions.getContext(_eContainer);
  }
}
