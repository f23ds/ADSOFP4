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
  private double value;

  /**
   * Constructor para la clase Transaction
   * @param walletE cartera emisora
   * @param walletR cartera receptora
   * @param value valor de la transacción
   */
  public Transaction(Wallet walletE, Wallet walletR, double value) {
    this.pkEmisor = walletE.getPublicKey();
    this.pkReceiver = walletR.getPublicKey();
    this.value = value;
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

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
