package Components;

import java.util.*;

/**
 * Clase para dar soporte a subredes
 *
 * @author Fabio Desio Alba López
 */
public class Subnet {

  private final int id = ++idCounter;
  private static int idCounter = -1;
  private ArrayList<Node> nodes;

  /**
   * Constructor para la subred
   * @param nodes
   */
  public Subnet(Node... nodeList) {
    this.nodes = new ArrayList<Node>();
    Collections.addAll(nodes, nodeList);
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
