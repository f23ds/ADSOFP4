package Components;

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

  private String name;
  private ArrayList<NetworkElement> elements;

  /**
   * Constructor para la clase BlockchainNetwork
   * @param name
   */
  public BlockchainNetwork(String name) {
    this.name = name;
    this.elements = new ArrayList<NetworkElement>();
  }

  /**
   * Método para añadir nodos, nodos mineros o subredes a la red de blockhain
   * @param element elemento genérico a añadir
   * @return devuleve la red construida
   * @throws ConnectionException para nodos ya conectados
   * @throws DuplicateConnectionException para nodos conectados a otras redes o subredes
   */
  public BlockchainNetwork connect(NetworkElement element)
    throws ConnectionException, DuplicateConnectionException {
    String connectedStr = String.format("%s - new peer connected: ", this.name);
    Node node;
    Subnet subnet;

    if (element.isNode()) {
      node = element.getNode();
      /* Consideramos la excepción en la que ya se encuentra el nodo conectado */
      if (elements.contains(element)) throw new ConnectionException(node);

      /* Consideramos la excepción en la que el nodo está conectado a otra red */
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
      /* Añadimos el nodo al array de nodos */
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Node> getNodes() {
    return elements
      .stream()
      .filter(NetworkElement::isNode)
      .map(e -> e.getNode())
      .collect(Collectors.toList());
  }

  public List<Subnet> getSubnets() {
    return elements
      .stream()
      .filter(NetworkElement::isSubnet)
      .map(e -> e.getSubnet())
      .collect(Collectors.toList());
  }
}
