package Components;

import java.util.*;

/**
 * Clase para dar soporte a una BlockchainNetwork
 *
 * @author Fabio Desio y Alba Lopez
 */
public class BlockchainNetwork {

  private String name;
  private ArrayList<Node> nodes = new ArrayList<Node>();
  private ArrayList<Subnet> subnets = new ArrayList<Subnet>();

  /**
   * Constructor para la clase BlockchainNetwork
   * @param name
   */
  public BlockchainNetwork(String name) {
    this.name = name;
  }

  public BlockchainNetwork connect(Object object) {
    /* TODO: POSIBLE EXCEPCIÓN */
    System.out.print(String.format("%s - new peer connected: ", this.name));

    if (object instanceof MiningNode) {
      MiningNode miningNode = (MiningNode) object;
      this.nodes.add(miningNode);

      System.out.println(miningNode.toString());
    }

    if (object instanceof Node) {
      Node node = (Node) object;
      this.nodes.add(node);
      System.out.println(node.toString());
    }

    if (object instanceof Subnet) {
      Subnet subnet = (Subnet) object;
      this.subnets.add(subnet);
      System.out.println(subnet.toString());
    }

    return this;
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
}
