package Network;

import Interfaces.*;
import java.util.*;

/**
 * Clase para dar soporte a subredes
 *
 * @author Fabio Desio Alba López
 */
public class Subnet extends NetworkElement {

  private ArrayList<Node> nodes;

  /**
   * Constructor para la subred
   * @param nodes
   */
  public Subnet(Node... nodeList) {
    this.nodes = new ArrayList<Node>();
    for (Node node : nodeList) {
      node.setParent(this);
      this.nodes.add(node);
    }
    this.parent = null;
  }

  @Override
  public String fullName() {
    return String.format("Subnet#%03d", this.id);
  }

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  @Override
  public void broadcast(IMessage msg) {
    System.out.println(
      "[" +
      this.fullName() +
      "] " +
      msg.getMessage() +
      "\nBroadcasting to " +
      nodes.size() +
      " nodes:"
    );
    nodes.stream().forEach(n -> n.broadcast(msg));
  }

  @Override
  public boolean isSubnet() {
    return true;
  }

  @Override
  public Subnet getSubnet() {
    return this;
  }

  @Override
  public String toString() {
    String subNetStr = String.format(
      "Node network of %d nodes: [",
      nodes.size()
    );

    for (Node node : nodes) {
      subNetStr += node.toString();
    }

    subNetStr = subNetStr.substring(0, subNetStr.length()) + "]";

    return subNetStr;
  }

  /* GETTERS AND SETTERS */
  public int getId() {
    return id;
  }

  public ArrayList<Node> getNodes() {
    return nodes;
  }

  public void setNodes(ArrayList<Node> nodes) {
    this.nodes = nodes;
  }
}
