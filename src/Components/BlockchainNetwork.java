package Components;

import Exceptions.*;
import Interfaces.IConnectable;
import Interfaces.IMessage;
// import Notifications.*;
import java.util.*;

/**
 * Clase para dar soporte a una BlockchainNetwork
 *
 * @author Fabio Desio y Alba Lopez
 */
public class BlockchainNetwork implements IConnectable {

  private String name;
  private ArrayList<Node> nodes;
  private ArrayList<Subnet> subnets;
  private ArrayList<Object> connections;

  /**
   * Constructor para la clase BlockchainNetwork
   * @param name
   */
  public BlockchainNetwork(String name) {
    this.name = name;
    this.nodes = new ArrayList<Node>();
    this.subnets = new ArrayList<Subnet>();
    this.connections = new ArrayList<Object>();
  }

  /**
   * Método para añadir nodos, nodos mineros o subredes a la red de blockhain
   * @param object objeto genérico a añadir
   * @return devuleve la red construida
   * @throws ConnectionException para nodos ya conectados
   * @throws DuplicateConnectionException para nodos conectados a otras redes o subredes
   */
  public BlockchainNetwork connect(Object object)
    throws ConnectionException, DuplicateConnectionException {
    String connectedStr = String.format("%s - new peer connected: ", this.name);

    if (object instanceof Node) {
      Node node = (Node) object;

      /* Consideramos la excepción en la que ya se encuentra el nodo conectado */
      if (nodes.contains(object)) throw new ConnectionException(node);

      /* Consideramos la excepción en la que el nodo está conectado a otra red */
      for (Subnet subnet : subnets) {
        if (
          subnet.getNodes().contains(object)
        ) throw new DuplicateConnectionException(node);
      }

      /* Añadimos el nodo al array de nodos */
      this.nodes.add(node);
      node.setParent(this);
      System.out.println(connectedStr + node.toString());
    }

    if (object instanceof Subnet) {
      Subnet subnet = (Subnet) object;

      /* Añadimos la subred al array de subredes */
      this.subnets.add(subnet);
      System.out.println(connectedStr + subnet.toString());
    }

    this.connections.add(object);

    return this;
  }

  @Override
  public String toString() {
    String connectionsStr = "";

    for (Object object : this.connections) {
      connectionsStr += "* " + object.toString() + "\n";
    }

    return (
      String.format(
        "%s consists of %d elements:\n",
        this.name,
        this.connections.size()
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
    return nodes;
  }

  public void setNodes(ArrayList<Node> nodes) {
    this.nodes = nodes;
  }

  public List<Subnet> getSubnets() {
    return subnets;
  }

  public void setSubnets(ArrayList<Subnet> subnets) {
    this.subnets = subnets;
  }

  /**
   * Método de la interfaz IConnectable para mandar la transacción a todos los nodos y subredes de la red
   * @param msg
   */
  @Override
  public void broadcast(IMessage msg) {
    for (Node node : nodes) node.broadcast(msg);
    for (Subnet subnet : subnets) subnet.broadcast(msg);
  }

  /**
   * Método de la interfaz IConnectable para gettear el padre
   * @return null pues la red no tiene padre
   */
  @Override
  public IConnectable getParent() {
    return null;
  }
}
