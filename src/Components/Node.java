package Components;

import Exceptions.TransactionException;
import Interfaces.*;
import java.util.*;

public class Node implements IConnectable {

  private final int id = ++idCounter;
  private static int idCounter = -1;
  private Wallet wallet;
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
  private IConnectable parent;

  /**
   * Constructor del nodo
   * @param wallet
   */
  public Node(Wallet wallet) {
    this.wallet = wallet;
    this.parent = null;
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
  public Transaction createTransaction(Wallet walletR, double value)
    throws TransactionException {
    if (value < 0) throw new TransactionException(
      this.getWallet().getPublicKey(),
      walletR.getPublicKey(),
      value
    );
    /* Creamos la transacción */
    Transaction transaction = new Transaction(this.getWallet(), walletR, value);

    /* La añadimos */
    this.transactions.add(transaction);

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
  public Transaction createTransaction(String publicKey, double value)
    throws TransactionException {
    if (value < 0) throw new TransactionException(
      this.getWallet().getPublicKey(),
      publicKey,
      value
    );
    /* Creamos la transacción */
    Transaction transaction = new Transaction(this.getWallet(), wallet, value);

    /* La añadimos */
    this.transactions.add(transaction);

    /* La devolvemos */
    return transaction;
  }

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  @Override
  public void broadcast(IMessage msg) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'broadcast'");
  }

  @Override
  public IConnectable getParent() {
    return this.parent;
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

  public void setParent(IConnectable parent) {
    this.parent = parent;
  }
}
