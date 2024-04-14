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
    boolean isMiningNode = false;

    if (isNode) if (element.isMiningNode()) isMiningNode = true;

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
      if (isMiningNode) out = "Emitted Task: ValidateBlockRes: " + msg;
      if (!isNode) out =
        "ValidateBlockRes\nBroadcasting to " +
        element.getSubnet().getNodes().size() +
        " nodes:";
    }

    System.out.println("[" + element.fullName() + "] " + out);
  }

  public boolean isTransactionNotification();

  public TransactionNotification getTransactionNotification();

  public boolean isValidateBlockRq();

  public ValidateBlockRq getValidateBlockRq();

  public boolean isValidateBlockRes();

  public ValidateBlockRes getValidateBlockRes();
}
