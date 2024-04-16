package Notifications;

import Block.Block;
import Interfaces.IMessage;
import Network.*;

/**
 * Clase de notificación para representar la respuesta de validación de un bloque.
 * Esta notificación contiene el bloque validado, un indicador booleano que indica si el bloque es válido y la fuente de la validación.
 * @author Fabio Desio Alba Lopez
 */
public class ValidateBlockRes implements IMessage {

  Block block;
  boolean isValidated;
  int src;

  /**
   * Constructor para la clase ValidateBlockRes.
   * @param block el bloque validado
   * @param isValidated indica si el bloque es válido
   * @param src la fuente de la validación
   */
  public ValidateBlockRes(Block block, boolean isValidated, int src) {
    this.block = block;
    this.isValidated = isValidated;
    this.src = src;
  }

  /**
   * Obtiene el mensaje de la notificación.
   * @return el mensaje de la notificación
   */
  @Override
  public String getMessage() {
    return "ValidateBlockRes";
  }

  /**
   * Procesa la notificación en un nodo.
   * @param n el nodo que procesa la notificación
   */
  @Override
  public void process(Node n) {
    System.out.println(
      String.format(
        "[" +
        n.fullName() +
        "] " +
        "Received Task: ValidateBlockRes: <b:" +
        block.getId() +
        ", res:" +
        isValidated +
        ", src:%03d" +
        ">",
        src
      )
    );

    n.handleValidateBlockRes(this);
  }

  /**
   * Obtiene el bloque validado.
   * @return el bloque validado
   */
  public Block getBlock() {
    return block;
  }

  /**
   * Indica si el bloque es válido.
   * @return true si el bloque es válido, false de lo contrario
   */
  public boolean isValidated() {
    return isValidated;
  }

  /**
   * Obtiene la fuente de la validación.
   * @return la fuente de la validación
   */
  public int getSrc() {
    return src;
  }
}
