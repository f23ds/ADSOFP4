package Exceptions;

/**
 * Excepción que surge al tratar de hacer una transacción con argumentos inválidos
 *
 * @author Fabio Desio Alba López
 */
public class TransactionException extends Exception {

  private String keySource;
  private String keyRec;
  private int value;
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  /**
   * Constructor de la excepción
   * @param keySource public key de la cartera emisora
   * @param keyRec public key de la cartera receptora
   * @param value valor de la transacción
   */
  public TransactionException(String keySource, String keyRec, int value) {
    this.keySource = keySource;
    this.keyRec = keyRec;
    this.value = value;
  }

  @Override
  public String toString() {
    return (
      ANSI_RED +
      String.format(
        "Negative transfer attempt: source: " + this.keySource + ", receiver: " + this.keyRec + ", amount: " + this.value
      ) +
      ANSI_RESET
    );
  }
}
