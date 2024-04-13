package Components;

import Interfaces.IConnectable;

/**
 * Clase abstracta para dar soporte a los elementos que forman parte de la red
 * A) Nodos y nodos mineros
 * B) Subredes
 *
 * @author Fabio Desio Alba López
 */
public abstract class NetworkElement implements IConnectable {

  protected final int id = ++idCounter;
  protected static int idCounter = -1;
  protected IConnectable parent;

  /**
   * Constructor de la clase NetworkElement
   */
  public NetworkElement() {
    this.parent = null;
  }

  @Override
  public IConnectable getParent() {
    return this.parent;
  }

  /**
   * Setter del atributo parent
   * @param parent padre a settear
   */
  public void setParent(IConnectable parent) {
    this.parent = parent;
  }

  public abstract boolean isSubnet();
  public abstract boolean isNode();

  public abstract Node getNode();
  public abstract Subnet getSubnet();

  public abstract String fullName();
}
