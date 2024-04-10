package Components;

/**
 * Clase para dar soporte a una cartera de blockchain
 * 
 * @author Fabio Desio y Alba Lopez
 */
public class Wallet {

  private String username;
  private String publicKey;
  private double balance;

  /**
   * Constructor para la clase wallet
   * @param username
   * @param publicKey
   * @param balance
   */
  public Wallet(String username, String publicKey, double balance) {
    this.username = username;
    this.publicKey = publicKey;
    this.balance = balance;
  }

  /* GETTERS AND SETTERS */
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
