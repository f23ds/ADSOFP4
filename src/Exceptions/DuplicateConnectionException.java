package Exceptions;

import Components.*;

/**
 * Excepción que surge al tratar de añadir un nodo que ya está en otra red o subred
 *
 * @author Fabio Desio Alba López
 */
public class DuplicateConnectionException extends ConnectionException {

  /**
   * Constructor de la excepción
   * @param node nodo ya conectado
   */
  public DuplicateConnectionException(Node node) {
    super(node);
  }

  @Override
  public String toString() {
    return ANSI_RED + String.format(
      "Connection exception: Node %03d is connected to a different network",
      this.getNode().getId()
    ) + ANSI_RESET;
  }
}
