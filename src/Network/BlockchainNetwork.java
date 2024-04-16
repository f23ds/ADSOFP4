package Network;

import Exceptions.*;
import Interfaces.IConnectable;
import Interfaces.IMessage;
// import Notifications.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase para dar soporte a una BlockchainNetwork
 *
 * @author Fabio Desio y Alba Lopez
 */
public class BlockchainNetwork implements IConnectable {

  /** Nombre de la red */
  private String name;

  /** Lista de elementos de la red */
  private ArrayList<NetworkElement> elements;

  /**
   * Constructor para la clase BlockchainNetwork
   * @param name el nombre de la red
   */
  public BlockchainNetwork(String name) {
    this.name = name;
    this.elements = new ArrayList<NetworkElement>();
  }

  /**
   * Método para añadir nodos, nodos mineros o subredes a la red de blockchain
   * @param element elemento genérico a añadir
   * @return devuelve la red construida
   * @throws ConnectionException si el elemento ya está conectado
   * @throws DuplicateConnectionException si el elemento está conectado a otras redes o subredes
   */
  public BlockchainNetwork connect(NetworkElement element)
    throws ConnectionException, DuplicateConnectionException {
    String connectedStr = String.format("%s - new peer connected: ", this.name);
    Node node;
    Subnet subnet;

    if (element.isNode()) {
      node = element.getNode();
      // Consideramos la excepción en la que ya se encuentra el nodo conectado
      if (elements.contains(element)) throw new ConnectionException(node);

      // Consideramos la excepción en la que el nodo está conectado a otra red
      Optional<Subnet> probSubnet = elements
        .stream()
        .filter(NetworkElement::isSubnet)
        .map(NetworkElement::getSubnet)
        .filter(s -> s.getNodes().contains(element))
        .findFirst();

      if (probSubnet.isPresent()) {
        throw new DuplicateConnectionException(node);
      }

      node.setParent(this);
      // Añadimos el nodo al array de nodos
      System.out.println(connectedStr + node.toString());
    }

    if (element.isSubnet()) {
      subnet = element.getSubnet();
      subnet.setParent(this);

      System.out.println(connectedStr + subnet.toString());
    }

    this.elements.add(element);

    return this;
  }

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  @Override
  public void broadcast(IMessage msg) {
    elements.stream().forEach(e -> e.broadcast(msg));
  }

  @Override
  public IConnectable getParent() {
    return null;
  }

  @Override
  public String toString() {
    String connectionsStr = "";

    for (NetworkElement element : this.elements) {
      connectionsStr += "* " + element.toString() + "\n";
    }

    return (
      String.format(
        "%s consists of %d elements:\n",
        this.name,
        this.elements.size()
      ) +
      connectionsStr
    );
  }

  /* GETTERS AND SETTERS */

  /**
   * Obtiene el nombre de la red.
   * @return el nombre de la red
   */
  public String getName() {
    return name;
  }

  /**
   * Establece el nombre de la red.
   * @param name el nuevo nombre de la red
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Obtiene la lista de nodos en la red.
   * @return la lista de nodos en la red
   */
  public List<Node> getNodes() {
    return elements
      .stream()
      .filter(NetworkElement::isNode)
      .map(e -> e.getNode())
      .collect(Collectors.toList());
  }

  /**
   * Obtiene la lista de subredes en la red.
   * @return la lista de subredes en la red
   */
  public List<Subnet> getSubnets() {
    return elements
      .stream()
      .filter(NetworkElement::isSubnet)
      .map(e -> e.getSubnet())
      .collect(Collectors.toList());
  }
}
