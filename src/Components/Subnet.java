package Components;

import Interfaces.*;
import java.util.*;

/**
 * Clase para dar soporte a subredes
 *
 * @author Fabio Desio Alba López
 */
public class Subnet implements IConnectable {

  private final int id = ++idCounter;
  private static int idCounter = -1;
  private ArrayList<Node> nodes;
  private IConnectable parent;

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

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  @Override
  public void broadcast(IMessage msg) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'broadcast'");
  }

  @Override
  public IConnectable getParent() {
    return this.parent;
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

  public void setParent(IConnectable parent) {
    this.parent = parent;
  }
}
