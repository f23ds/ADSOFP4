package Notifications;

import Components.*;
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

  @Override
  public boolean isTransactionNotification() {
    return true;
  }

  @Override
  public TransactionNotification getTransactionNotification() {
    return this;
  }

  public void process(Node n) {
    System.out.println(
      "[" +
      n.fullName() +
      "]" +
      " - Received notification - Nex Tx: " +
      this.getMessage()
    );
  }

  public void process(Subnet s) {
    System.out.println(
      "[" +
      s.fullName() +
      "] " +
      this.getMessage() +
      "\nBroadcasting to " +
      s.getNodes().size() +
      " nodes:"
    );
  }

  public Transaction getTransaction() {
    return transaction;
  }
}
