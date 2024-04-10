package Notifications;

import Components.Transaction;
import Interfaces.IMessage;

/**
 * Clase para dar soporte a las notificaciones de las transacciones.
 * Se encarga de notificar a la red la creación de una nueva transacción.
 * Deben ser recibidos y gestionados por los nodos
 *
 * @author Fabio Desio Alba López
 */
public class TransactionNotification implements IMessage {

  private Transaction transaction;

  /**
   * Constructor de la clase TransactionNotification
   * @param transaction transacción 
   */
  public TransactionNotification(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public String getMessage() {
    return transaction.toString();
  }
}
