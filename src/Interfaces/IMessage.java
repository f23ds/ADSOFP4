package Interfaces;

import Components.*;
import Notifications.*;

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
  public default void process(NetworkElement element) {
    boolean isNode = element.isNode() ? true : false;
    String msg = this.getMessage();

    System.out.println(
      "[" +
      element.fullName() +
      "] " +
      (
        isNode
          ? " - Received notification - Nex Tx: " + msg
          : msg +
          "\nBroadcasting to " +
          element.getSubnet().getNodes().size() +
          " nodes:"
      )
    );
  }

  public boolean isTransactionNotification();
  public TransactionNotification getTransactionNotification();
  public boolean isValidateBlockRq();
  public ValidateBlockRq getValidateBlockRq();
}
