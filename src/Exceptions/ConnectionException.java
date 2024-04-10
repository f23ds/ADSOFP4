package Exceptions;

import Components.*;

/**
 * Excepción que surge al tratar de añadir un nodo que ya está en la red
 *
 * @author Fabio Desio Alba López
 */
public class ConnectionException extends Exception {

  private Node node;
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  /**
   * Constructor de la excepción
   * @param node nodo ya conectado
   */
  public ConnectionException(Node node) {
    this.node = node;
  }

  @Override
  public String toString() {
    return (
      ANSI_RED +
      String.format(
        "Connection exception: Node %03d is already connected to the network",
        node.getId()
      ) +
      ANSI_RESET
    );
  }

  /**
   * Getter del atributo nodo de la excepción
   * @return nodo a devolver
   */
  public Node getNode() {
    return node;
  }

  /**
   * Setter del atributo nodo de la excepción
   * @param node a settear
   */
  public void setNode(Node node) {
    this.node = node;
  }
}
