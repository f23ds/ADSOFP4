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
  private TransactionStatus txStatus;

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
    this.txStatus = TransactionStatus.NOT_CONFIRMED;
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

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public TransactionStatus getTxStatus() {
    return txStatus;
  }

  public void setTxStatus(TransactionStatus txStatus) {
    this.txStatus = txStatus;
  }

  public String getPkEmisor() {
    return pkEmisor;
  }

  public void setPkEmisor(String pkEmisor) {
    this.pkEmisor = pkEmisor;
  }

  public String getPkReceiver() {
    return pkReceiver;
  }

  public void setPkReceiver(String pkReceiver) {
    this.pkReceiver = pkReceiver;
  }

  
}
