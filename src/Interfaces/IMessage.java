package Interfaces;

import Components.*;
/**
 * Interfaz para mensajes
 *
 * @author Fabio Desio Alba López
 */
public interface IMessage {
  /**
   * Obtención del texto del mensaje
   * @return string con el texto del mensaje
   */
  public String getMessage();

  /**
   * Procesamiento por un nodo
   * @param n nodo
   */
  public default void process(Node n) {
    System.out.println(
      "[" +
      n.fullName() +
      "]" +
      " - Received notification - Nex Tx: " +
      this.getMessage()
    );
  }
}
