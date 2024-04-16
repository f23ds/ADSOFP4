package Notifications;

import Interfaces.IMessage;
import Network.*;

/**
 * Clase para representar las notificaciones de transacciones.
 * Estas notificaciones informan a la red sobre la creación de una nueva transacción.
 * Deben ser recibidas y gestionadas por los nodos.
 * 
 * @author Fabio Desio y Alba López
 */
public class TransactionNotification implements IMessage {

  /** La transacción asociada a la notificación */
  private Transaction transaction;

  /**
   * Constructor de la clase TransactionNotification.
   * @param transaction la transacción asociada a la notificación
   */
  public TransactionNotification(Transaction transaction) {
    this.transaction = transaction;
  }

  /**
   * Procesa la notificación en un nodo.
   * @param n el nodo que procesa la notificación
   */
  @Override
  public void process(Node n) {
    System.out.println(
      "[" +
      n.fullName() +
      "]" +
      " - Received notification - Nex Tx: " +
      this.getMessage()
    );

    n.handleTransactionNotification(this);
  }

  /**
   * Obtiene el mensaje de la notificación.
   * @return el mensaje de la notificación
   */
  @Override
  public String getMessage() {
    return transaction.toString();
  }

  /**
   * Obtiene la transacción asociada a la notificación.
   * @return la transacción asociada a la notificación
   */
  public Transaction getTransaction() {
    return transaction;
  }
}
