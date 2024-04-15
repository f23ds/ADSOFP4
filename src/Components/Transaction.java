package Components;

/**
 * Clase para dar soporte a una Transaccion de Blockchain
 * Esta clase representa una transacción en la red blockchain, que involucra la transferencia de valor entre carteras.
 * La transacción puede estar en diferentes estados de confirmación.
 * 
 * @author Fabio Desio y Alba Lopez
 */
public class Transaction {

  /** Identificador único de la transacción */
  private final int id = ++idCounter;

  /** Contador de identificadores para las transacciones */
  private static int idCounter = -1;

  /** Clave pública de la cartera emisora */
  private String pkEmisor;

  /** Clave pública de la cartera receptora */
  private String pkReceiver;

  /** Valor transferido en la transacción */
  private int value;

  /** Estado de la transacción */

  /**
   * Constructor para la clase Transaction.
   * @param walletE la cartera emisora
   * @param walletR la cartera receptora
   * @param value el valor de la transacción
   */
  public Transaction(Wallet walletE, Wallet walletR, int value) {
    this.pkEmisor = walletE.getPublicKey();
    this.pkReceiver = walletR.getPublicKey();
    this.value = value;
  }

  /**
   * Constructor para la clase Transaction.
   * @param publicKeyE la clave pública de la cartera emisora
   * @param publicKeyR la clave pública de la cartera receptora
   * @param value el valor de la transacción
   */
  public Transaction(String publicKeyE, String publicKeyR, int value) {
    this.pkEmisor = publicKeyE;
    this.pkReceiver = publicKeyR;
    this.value = value;
  }

  /**
   * Obtiene una representación en cadena de la transacción.
   * @return una representación en cadena de la transacción
   */
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

  /**
   * Obtiene el identificador único de la transacción.
   * @return el identificador único de la transacción
   */
  public int getId() {
    return id;
  }

  /**
   * Obtiene el valor transferido en la transacción.
   * @return el valor de la transacción
   */
  public int getValue() {
    return value;
  }

  /**
   * Establece el valor transferido en la transacción.
   * @param value el nuevo valor de la transacción
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Obtiene la clave pública de la cartera emisora.
   * @return la clave pública de la cartera emisora
   */
  public String getPkEmisor() {
    return pkEmisor;
  }

  /**
   * Establece la clave pública de la cartera emisora.
   * @param pkEmisor la nueva clave pública de la cartera emisora
   */
  public void setPkEmisor(String pkEmisor) {
    this.pkEmisor = pkEmisor;
  }

  /**
   * Obtiene la clave pública de la cartera receptora.
   * @return la clave pública de la cartera receptora
   */
  public String getPkReceiver() {
    return pkReceiver;
  }

  /**
   * Establece la clave pública de la cartera receptora.
   * @param pkReceiver la nueva clave pública de la cartera receptora
   */
  public void setPkReceiver(String pkReceiver) {
    this.pkReceiver = pkReceiver;
  }

}
