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
  private final int version;
  private final int nonce;
  private final int timestamp;
  private final int difficulty;
  private final Transaction transaction;
  private boolean isValidated;
  private final String hash;
  private final Block prevBlock;

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
  }

  public int getVersion() {
    return version;
  }

  public int getNonce() {
    return nonce;
  }

  public int getTimestamp() {
    return timestamp;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public String getHash() {
    return hash;
  }

  public Block getPrevBlock() {
    return prevBlock;
  }

  /**
   * @return boolean return the isValidated
   */
  public boolean isIsValidated() {
    return isValidated;
  }

  /**
   * @param isValidated the isValidated to set
   */
  public void setIsValidated(boolean isValidated) {
    this.isValidated = isValidated;
  }
}
