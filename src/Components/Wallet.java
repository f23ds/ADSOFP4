package Components;

/**
 * Clase para dar soporte a una cartera de blockchain.
 * Esta clase representa una cartera en la red blockchain, que contiene la información de un usuario, su clave pública y su saldo.
 *
 * @author Fabio Desio y Alba Lopez
 */
public class Wallet {

  /** Nombre de usuario asociado a la cartera */
  private String username;

  /** Clave pública de la cartera */
  private String publicKey;

  /** Saldo de la cartera */
  private int balance;

  /**
   * Constructor para la clase Wallet.
   * @param username el nombre de usuario
   * @param publicKey la clave pública
   * @param balance el saldo inicial de la cartera
   */
  public Wallet(String username, String publicKey, int balance) {
    this.username = username;
    this.publicKey = publicKey;
    this.balance = balance;
  }

  /* GETTERS AND SETTERS */

  /**
   * Obtiene el nombre de usuario asociado a la cartera.
   * @return el nombre de usuario
   */
  public String getUsername() {
    return username;
  }

  /**
   * Establece el nombre de usuario asociado a la cartera.
   * @param username el nuevo nombre de usuario
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Obtiene la clave pública de la cartera.
   * @return la clave pública
   */
  public String getPublicKey() {
    return publicKey;
  }

  /**
   * Establece la clave pública de la cartera.
   * @param publicKey la nueva clave pública
   */
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  /**
   * Obtiene el saldo de la cartera.
   * @return el saldo de la cartera
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Establece el saldo de la cartera.
   * @param balance el nuevo saldo de la cartera
   */
  public void setBalance(int balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "u: " + username + ", PK:" + publicKey + ", balance: " + balance;
  }
}
