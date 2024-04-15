package Components;

import Exceptions.TransactionException;
import Interfaces.*;
import Notifications.*;
import java.util.*;

/**
 * Clase que representa un nodo en la red.
 * Los nodos son elementos de red capaces de realizar transacciones.
 * @author Fabio Desio Alba López
 */
public class Node extends NetworkElement {

  /** La cartera asociada al nodo */
  private Wallet wallet;

  /** Lista de transacciones realizadas por el nodo */
  private ArrayList<Transaction> transactions;

  /**
   * Constructor del nodo.
   * @param wallet la cartera asociada al nodo
   */
  public Node(Wallet wallet) {
    this.wallet = wallet;
    this.parent = null;
    this.transactions = new ArrayList<Transaction>();
  }

  /**
   * Función para devolver el nombre completo formateado del nodo.
   * @return el nombre completo formateado del nodo
   */
  @Override
  public String fullName() {
    return String.format("Node#%03d", this.id);
  }

  /**
   * Función para crear una transacción desde la cartera del nodo a otra cartera.
   * @param walletR la cartera del destinatario
   * @param value el valor de la transacción
   * @return la transacción creada
   * @throws TransactionException si ocurre un error al crear la transacción
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
   * Función para crear una transacción desde la cartera del nodo a otra cartera.
   * @param publicKeyD la clave pública de la cartera del destinatario
   * @param value el valor de la transacción
   * @return la transacción creada
   * @throws TransactionException si ocurre un error al crear la transacción
   */
  public Transaction createTransaction(String publicKeyD, int value)
    throws TransactionException {
    if (value < 0) throw new TransactionException(
      wallet.getPublicKey(),
      publicKeyD,
      value
    );
    /* Creamos la transacción */
    Transaction transaction = new Transaction(
      wallet.getPublicKey(),
      publicKeyD,
      value
    );

    /* La devolvemos */
    return transaction;
  }

  /* ----------------------------- MÉTODOS DE ICONNECTABLE ----------------------------- */

  /**
   * Método para transmitir un mensaje.
   * @param msg el mensaje a transmitir
   */
  @Override
  public void broadcast(IMessage msg) {
    msg.process(this);
  }

  /**
   * Método que indica si el elemento de red es un nodo.
   * @return true si el elemento es un nodo, false de lo contrario
   */
  @Override
  public boolean isNode() {
    return true;
  }

  /**
   * Método que devuelve el nodo asociado al elemento de red.
   * @return el nodo asociado al elemento de red
   */
  @Override
  public Node getNode() {
    return this;
  }

  /**
   * Método que devuelve una representación en cadena del nodo.
   * @return una representación en cadena del nodo
   */
  @Override
  public String toString() {
    return (
      String.format(
        "u: %s, PK:%s, balance: %d | @",
        wallet.getUsername(),
        wallet.getPublicKey(),
        wallet.getBalance()
      ) +
      this.fullName()
    );
  }

  public void handleTransactionNotification(TransactionNotification txNoti) {}

  public void handleValidateBlockRq(ValidateBlockRq blockRqNoti) {}

  public void handleValidateBlockRes(ValidateBlockRes blockResNoti) {
    Block block = blockResNoti.getBlock();
    Transaction tx = block.getTransaction();
    boolean participates = false;
    String publicKeyE = tx.getPkEmisor();
    String publicKeyR = tx.getPkReceiver();
    String walletPublicKey = wallet.getPublicKey();
    int balance = wallet.getBalance();
    int value = tx.getValue();

    System.out.println(
      "[" +
      this.fullName() +
      "] Commiting transaction : Tx-" +
      tx.getId() +
      " in " +
      this.fullName()
    );

    System.out.println(
      "[" + this.fullName() + "] -> Tx details:" + tx.toString()
    );

    /* Añadimos la transacción a la lista de transacciones confirmadas */
    this.addTransaction(tx);

    if (walletPublicKey == publicKeyE) {
      wallet.setBalance(balance - value);
      participates = true;
    } else if (walletPublicKey == publicKeyR) {
      wallet.setBalance(balance + value);
      participates = true;
    }

    if (participates) {
      System.out.println(
        "[" + this.fullName() + "] " + "Applied Transaction: " + tx.toString()
      );
      System.out.println(
        "[" + this.fullName() + "] " + "New wallet value: " + wallet.toString()
      );
    }
  }

  public SimpleMining getMiningMethod() {
    return null;
  }

  /* GETTERS AND SETTERS */

  /**
   * Obtiene el identificador del nodo.
   * @return el identificador del nodo
   */
  public int getId() {
    return id;
  }

  /**
   * Obtiene la cartera asociada al nodo.
   * @return la cartera asociada al nodo
   */
  public Wallet getWallet() {
    return wallet;
  }

  /**
   * Establece la cartera asociada al nodo.
   * @param wallet la nueva cartera asociada al nodo
   */
  public void setWallet(Wallet wallet) {
    this.wallet = wallet;
  }

  /**
   * Obtiene la lista de transacciones realizadas por el nodo.
   * @return la lista de transacciones realizadas por el nodo
   */
  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  /**
   * Establece la lista de transacciones realizadas por el nodo.
   * @param transactions la nueva lista de transacciones realizadas por el nodo
   */
  public void setTransactions(ArrayList<Transaction> transactions) {
    this.transactions = transactions;
  }

  public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
  }
}
