package Components;

import Exceptions.TransactionException;
import Interfaces.*;
import java.util.*;

public class Node extends NetworkElement {

  private Wallet wallet;
  private ArrayList<Transaction> transactions;

  /**
   * Constructor del nodo
   * @param wallet
   */
  public Node(Wallet wallet) {
    this.wallet = wallet;
    this.parent = null;
    this.transactions = new ArrayList<Transaction>();
  }

  /**
   * Función para devolver el nombre completo formateado del nodo
   * @return string con el nombre completo formateado
   */
  public String fullName() {
    return String.format("Node#%03d", this.id);
  }

  /**
   * Función para crear una transacción desde la cartera del nodo a otra cartera
   * @param walletR del destinatario
   * @param value valor de la transacción
   * @return transacción creada
   * @throws TransactionException para errores al crear la transacción
   */
  public Transaction createTransaction(Wallet walletR, int value)
    throws TransactionException {
    if (value < 0) throw new TransactionException(
      this.getWallet().getPublicKey(),
      walletR.getPublicKey(),
      value
    );
    /* Creamos la transacción */
    Transaction transaction = new Transaction(this.getWallet(), walletR, value);

    /* La devolvemos */
    return transaction;
  }

  /**
   * Función para crear una transacción desde la cartera del nodo a otra cartera
   * @param String con la public key de la cartera del destinatario
   * @param value valor de la transacción
   * @return transacción creada
   * @throws TransactionException para errores al crear la transacción
   */
  public Transaction createTransaction(String publicKey, int value)
    throws TransactionException {
    if (value < 0) throw new TransactionException(
      this.getWallet().getPublicKey(),
      publicKey,
      value
    );
    /* Creamos la transacción */
    Transaction transaction = new Transaction(this.getWallet(), wallet, value);

    /* La devolvemos */
    return transaction;
  }

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  @Override
  public void broadcast(IMessage msg) {
    msg.process(this);
    /* Si el mensjae es una TransactionNotification */
    if (msg.isTransactionNotification()) {
      Transaction tx = msg.getTransactionNotification().getTransaction();
      /* Si está confirmada, la añadimos */
      if (tx.isConfirmed()) {
        this.transactions.add(
            msg.getTransactionNotification().getTransaction()
          );
      }
    }
  }

  public boolean isSubnet() {
    return false;
  }

  public boolean isNode() {
    return true;
  }

  public Node getNode() {
    return this;
  }

  public Subnet getSubnet() {
    return null;
  }

  @Override
  public String toString() {
    return (
      String.format(
        "u: %s, PK:%s, balance: %.0f | @",
        wallet.getUsername(),
        wallet.getPublicKey(),
        wallet.getBalance()
      ) +
      this.fullName()
    );
  }

  /* GETTERS AND SETTERS */
  public int getId() {
    return id;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public void setWallet(Wallet wallet) {
    this.wallet = wallet;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(ArrayList<Transaction> transactions) {
    this.transactions = transactions;
  }
}
