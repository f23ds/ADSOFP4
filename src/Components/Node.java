package Components;

import java.util.*;

public class Node {

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

  public String fullName() {
    return String.format("Node#%03d", this.id);
  }

  @Override
  public String toString() {
    return String.format(
      "u: %s, PK:%s, balance: %.0f | @",
      wallet.getUsername(),
      wallet.getPassword(),
      wallet.getBalance()
    ) + this.fullName();
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
