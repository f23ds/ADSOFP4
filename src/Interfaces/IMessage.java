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
   * @param element elemento de la red
   */
  public default void process(NetworkElement element) {
    String msg = this.getMessage();
    String out = "";
    boolean isNode = element.isNode() ? true : false;

    if (this.isTransactionNotification()) {
      out =
        String.format(
          isNode
            ? "- Received notification - Nex Tx: " + msg
            : msg +
            "\nBroadcasting to " +
            element.getSubnet().getNodes().size() +
            " nodes:"
        );
    }

    if (this.isValidateBlockRq()) {
      out =
        String.format(
          isNode
            ? "Received Task: ValidateBlockRq: " + msg
            : "ValidateBlockRq\nBroadcasting to " +
            element.getSubnet().getNodes().size() +
            " nodes:"
        );
    }

    if (this.isValidateBlockRes()) {
      out =
        String.format(
          isNode
            ? "Received Task: ValidateBlockRes: " + msg
            : "ValidateBlockRes\nBroadcasting to " +
            element.getSubnet().getNodes().size() +
            " nodes:"
        );
    }

    System.out.println("[" + element.fullName() + "] " + out);
  }

  public default boolean isTransactionNotification() {
    return false;
  }

  public default TransactionNotification getTransactionNotification() {
    return null;
  }

  public default boolean isValidateBlockRq() {
    return false;
  }

  public default ValidateBlockRq getValidateBlockRq() {
    return null;
  }

  public default boolean isValidateBlockRes() {
    return false;
  }

  public default ValidateBlockRes getValidateBlockRes() {
    return null;
  }
}
