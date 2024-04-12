package Components;

import java.util.*;

import Interfaces.IConnectable;
import Interfaces.IMessage;

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
    Collections.addAll(nodes, nodeList);
    this.parent = null;
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

  @Override
  public void broadcast(IMessage msg) {
    for (Node node : nodes) node.broadcast(msg);
  }

  @Override
  public IConnectable getParent() {
    return parent;
  }
}
