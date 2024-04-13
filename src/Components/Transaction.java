package Components;

/**
 * Clase para dar soporte a una Transaccion de Blockchain
 *
 * @author Fabio Desio y Alba Lopez
 */
public class Transaction {

  private final int id = ++idCounter;
  private static int idCounter = -1;
  private String pkEmisor;
  private String pkReceiver;
  private int value;
  private boolean isConfirmed;

  /**
   * Constructor para la clase Transaction
   * @param walletE cartera emisora
   * @param walletR cartera receptora
   * @param value valor de la transacción
   */
  public Transaction(Wallet walletE, Wallet walletR, int value) {
    this.pkEmisor = walletE.getPublicKey();
    this.pkReceiver = walletR.getPublicKey();
    this.value = value;
    this.isConfirmed = false;
  }

  @Override
  public String toString() {
    return (
      "Transaction " +
      id +
      "| from: " +
      pkEmisor +
      ", to: " +
      pkReceiver +
      ", quantity: " +
      value
    );
  }

  /* GETTERS AND SETTERS */

  public int getId() {
    return id;
  }

  public String getpkEmisor() {
    return pkEmisor;
  }

  public void setpkEmisor(String pkEmisor) {
    this.pkEmisor = pkEmisor;
  }

  public String getpkReceiver() {
    return pkReceiver;
  }

  public void setpkReceiver(String pkReceiver) {
    this.pkReceiver = pkReceiver;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public boolean isConfirmed() {
    return isConfirmed;
  }

  public void setConfirmed(boolean isConfirmed) {
    this.isConfirmed = isConfirmed;
  }
}
