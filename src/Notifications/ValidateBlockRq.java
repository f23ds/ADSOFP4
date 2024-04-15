package Notifications;

import Components.*;
import Interfaces.IMessage;

/**
 * Clase de notificación para solicitar la validación de un bloque.
 * Esta notificación contiene el bloque que se debe validar y el nodo minero que solicita la validación.
 * @author Fabio Desio Alba Lopez
 */
public class ValidateBlockRq implements IMessage {

  /** El bloque que se debe validar */
  private Block block;

  /** El nodo minero que solicita la validación */
  private MiningNode miningNode;

  /**
   * Constructor para la clase ValidateBlockRq.
   * @param block el bloque que se debe validar
   * @param miningNode el nodo minero que solicita la validación
   */
  public ValidateBlockRq(Block block, MiningNode miningNode) {
    this.block = block;
    this.miningNode = miningNode;
  }

  /**
   * Obtiene el mensaje de la notificación.
   * @return el mensaje de la notificación
   */
  @Override
  public String getMessage() {
    return "ValidateBlockRq";
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
        "Received Task: ValidateBlockRq: <b:" +
        block.getId() +
        ", src:%03d" +
        ">",
        miningNode.getId()
      )
    );

    n.handleValidateBlockRq(this);
  }

  /**
   * Obtiene el bloque que se debe validar.
   * @return el bloque que se debe validar
   */
  public Block getBlock() {
    return block;
  }

  /**
   * Obtiene el nodo minero que solicita la validación.
   * @return el nodo minero que solicita la validación
   */
  public MiningNode getMiningNode() {
    return miningNode;
  }
}
