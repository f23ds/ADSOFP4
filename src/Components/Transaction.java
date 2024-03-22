package Components;

/**
 * Clase para dar soporte a una Transaccion de Blockchain
 *
 * @author Fabio Desio y Alba Lopez
 */
public class Transaction {

  private final int id = ++idCounter;
  private static int idCounter = -1;
  private String pswEmisor;
  private String pswReceiver;
  private double value;

  /**
   * Constructor para la clase Transaction
   * @param id
   * @param pswEmisor
   * @param pswReceiver
   * @param value
   */
  public Transaction(Wallet wallet1, Wallet wallet2, double value) {
    this.pswEmisor = wallet1.getPassword();
    this.pswReceiver = wallet2.getPassword();
    this.value = value;
  }

  /* GETTERS AND SETTERS */

  public int getId() {
    return id;
  }

  public String getPswEmisor() {
    return pswEmisor;
  }

  public void setPswEmisor(String pswEmisor) {
    this.pswEmisor = pswEmisor;
  }

  public String getPswReceiver() {
    return pswReceiver;
  }

  public void setPswReceiver(String pswReceiver) {
    this.pswReceiver = pswReceiver;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
