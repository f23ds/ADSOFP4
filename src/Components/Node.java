package Components;

import Interfaces.*;
import java.util.*;

public class Node implements IConnectable {

  private final int id = ++idCounter;
  private static int idCounter = -1;
  // Ulid id = UlidCreator.getUlid();
  private Wallet wallet;
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

  /**
   * Constructor del nodo
   * @param wallet
   * @param transactions
   */
  public Node(Wallet wallet) {
    this.wallet = wallet;
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
   * @param wallet del destinatario
   * @param value valor de la transacción
   * @return transacción creada
   */
  public Transaction createTransaction(Wallet wallet, double value) {
    /* Creamos la transacción */
    Transaction transaction = new Transaction(this.getWallet(), wallet, value);

    /* La añadimos */
    this.transactions.add(transaction);

    /* La devolvemos */
    return transaction;
  }

  @Override
  public void broadcast(IMessage msg) {
    // TODO Auto-generated method stub

  }

  @Override
  public IConnectable getParent() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return (
      String.format(
        "u: %s, PK:%s, balance: %.0f | @",
        wallet.getUsername(),
        wallet.getPassword(),
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
