package Components;

import blockchain.utils.BlockConfig;
import java.util.*;

/**
 * Clase para dar soporte a los bloques de la red
 *
 * @author Fabio Desio Alba López
 */
public class Block {

  private final int id;
  private static int idCounter = -1;
  private int version;
  private final int nonce;
  private final int timestamp;
  private int difficulty;
  private final Transaction transaction;
  private boolean isValidated;
  private String hash;
  private Block prevBlock;
  private String minerKey;

  /**
   * Constructor de la clase Block
   * @param transaction transacción del bloque
   */
  public Block(Transaction transaction) {
    this.id = ++idCounter;
    this.version = BlockConfig.VERSION;
    this.nonce = (int) (new Random().nextInt(1000) + 1);
    this.timestamp = (int) (new Date().getTime() / 1000);
    this.difficulty = BlockConfig.DIFFICULTY;
    this.transaction = transaction;
    this.isValidated = false;
    this.hash = null;
    this.prevBlock = null;
    this.minerKey = null;
  }

  /**
   * Constructor de la clase Block
   * @param transaction transacción del bloque
   * @param prevBlock bloque anterior
   * @param minerKey clave pública de la cartera del minero
   */
  public Block(Transaction transaction, Block prevBlock, String minerKey) {
    this.id = ++idCounter;
    this.version = BlockConfig.VERSION;
    this.nonce = (int) (new Random().nextInt(1000) + 1);
    this.timestamp = (int) (new Date().getTime() / 1000);
    this.difficulty = BlockConfig.DIFFICULTY;
    this.transaction = transaction;
    this.isValidated = false;
    this.hash = null;
    this.prevBlock = prevBlock;
    this.minerKey = minerKey;
  }

  /**
   * Devuelve una representación en cadena del objeto Block.
   * @return una cadena que representa el objeto Block
   */
  @Override
  public String toString() {
    return String.format(
      "id:" +
      this.id +
      ", v:" +
      this.version +
      ", nonce:" +
      this.nonce +
      ", ts:" +
      this.timestamp +
      ", diff:" +
      this.difficulty +
      " , hash:" +
      this.hash +
      ", minerKey:" +
      this.minerKey
    );
  }

  // Métodos de acceso

  /**
   * Obtiene la versión del bloque.
   * @return la versión del bloque
   */
  public int getVersion() {
    return version;
  }

  /**
   * Obtiene el valor del nonce.
   * @return el valor del nonce
   */
  public int getNonce() {
    return nonce;
  }

  /**
   * Obtiene la marca de tiempo del bloque.
   * @return la marca de tiempo del bloque
   */
  public int getTimestamp() {
    return timestamp;
  }

  /**
   * Obtiene la dificultad del bloque.
   * @return la dificultad del bloque
   */
  public int getDifficulty() {
    return difficulty;
  }

  /**
   * Obtiene la transacción asociada al bloque.
   * @return la transacción asociada al bloque
   */
  public Transaction getTransaction() {
    return transaction;
  }

  /**
   * Obtiene el hash del bloque.
   * @return el hash del bloque
   */
  public String getHash() {
    return hash;
  }

  /**
   * Obtiene el bloque previo en la cadena.
   * @return el bloque previo en la cadena
   */
  public Block getPrevBlock() {
    return prevBlock;
  }

  /**
   * Determina si el bloque ha sido validado.
   * @return true si el bloque ha sido validado, false de lo contrario
   */
  public boolean isIsValidated() {
    return isValidated;
  }

  /**
   * Establece el estado de validación del bloque.
   * @param isValidated el nuevo estado de validación
   */
  public void setIsValidated(boolean isValidated) {
    this.isValidated = isValidated;
  }

  /**
   * Establece el hash del bloque.
   * @param hash el hash del bloque
   */
  public void setHash(String hash) {
    this.hash = hash;
  }

  /**
   * Establece la versión del bloque.
   * @param version la nueva versión del bloque
   */
  public void setVersion(int version) {
    this.version = version;
  }

  /**
   * Obtiene la clave pública del minero.
   * @return la clave pública del minero
   */
  public String getMinerKey() {
    return minerKey;
  }

  /**
   * Establece la clave pública del minero.
   * @param minerKey la nueva clave pública del minero
   */
  public void setMinerKey(String minerKey) {
    this.minerKey = minerKey;
  }

  /**
   * Obtiene el identificador único del bloque.
   * @return el identificador único del bloque
   */
  public int getId() {
    return id;
  }

  public void setPrevBlock(Block prevBlock) {
    this.prevBlock = prevBlock;
  }
}
