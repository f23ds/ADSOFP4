package Network;

import Interfaces.IConnectable;

/**
 * Clase abstracta para dar soporte a los elementos que forman parte de la red.
 * Pueden ser:
 * A) Nodos y nodos mineros
 * B) Subredes
 *
 * @author Fabio Desio Alba López
 */
public abstract class NetworkElement implements IConnectable {

  /** Identificador único del elemento de red */
  protected final int id = ++idCounter;

  /** Contador de identificadores para los elementos de red */
  protected static int idCounter = -1;

  /** El elemento padre en la jerarquía de conexión */
  protected IConnectable parent;

  /**
   * Constructor de la clase NetworkElement
   * Inicializa el elemento padre como nulo.
   */
  public NetworkElement() {
    this.parent = null;
  }

  /**
   * Obtiene el elemento padre en la jerarquía de conexión.
   * @return el elemento padre
   */
  @Override
  public IConnectable getParent() {
    return this.parent;
  }

  /**
   * Establece el elemento padre en la jerarquía de conexión.
   * @param parent el nuevo elemento padre
   */
  public void setParent(IConnectable parent) {
    this.parent = parent;
  }

  /**
   * Obtiene el nombre completo del elemento de red.
   * @return el nombre completo del elemento de red
   */
  public abstract String fullName();

  /**
   * Verifica si el elemento de red es una subred.
   * @return true si el elemento es una subred, false de lo contrario
   */
  public boolean isSubnet() {
    return false;
  }

  /**
   * Verifica si el elemento de red es un nodo.
   * @return true si el elemento es un nodo, false de lo contrario
   */
  public boolean isNode() {
    return false;
  }

  /**
   * Verifica si el elemento de red es un nodo minero.
   * @return true si el elemento es un nodo minero, false de lo contrario
   */
  public boolean isMiningNode() {
    return false;
  }

  /**
   * Obtiene la subred asociada al elemento de red.
   * @return la subred asociada al elemento de red
   */
  public Subnet getSubnet() {
    return null;
  }

  /**
   * Obtiene el nodo asociado al elemento de red.
   * @return el nodo asociado al elemento de red
   */
  public Node getNode() {
    return null;
  }

  /**
   * Obtiene el nodo minero asociado al elemento de red.
   * @return el nodo minero asociado al elemento de red
   */
  public MiningNode getMiningNode() {
    return null;
  }
}
