package Components;

/**
 * Clase para dar soporte a una cartera de blockchain
 * 
 * @author Fabio Desio y Alba Lopez
 */
public class Wallet {

  private String username;
  private String password;
  private double balance;

  /**
   * Constructor para la clase wallet
   * @param username
   * @param password
   * @param balance
   */
  public Wallet(String username, String password, double balance) {
    this.username = username;
    this.password = password;
    this.balance = balance;
  }

  /* GETTERS AND SETTERS */
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
