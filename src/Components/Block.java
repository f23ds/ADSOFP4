package Components;

import blockchain.utils.BlockConfig;
import java.util.*;

/**
 * Clase para dar soporte a los bloques de la red
 *
 * @author Fabio Desio Alba López
 */
public class Block {

  @SuppressWarnings("unused")
  private final int id;

  private static int idCounter = -1;
  private int version;
  private final int nonce;
  private final int timestamp;
  private final int difficulty;
  private final Transaction transaction;
  private boolean isValidated;
  private String hash;
  private final Block prevBlock;
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
   * @param minerKey public key de la cartera del minero
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

  public void setHash(String hash) {
    this.hash = hash;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getMinerKey() {
    return minerKey;
  }

  public void setMinerKey(String minerKey) {
    this.minerKey = minerKey;
  }

  public int getId() {
    return id;
  }
}
