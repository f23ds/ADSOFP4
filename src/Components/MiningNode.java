package Components;

import Interfaces.*;
import blockchain.utils.*;
import java.util.*;

/**
 * Clase para dar soporte a nodos mineros
 *
 * @author Fabio Desio Alba López
 */
public class MiningNode extends Node implements IMiningMethod {

  private double capacity; // En MIPS
  private List<Block> blocks;

  public MiningNode(Wallet wallet, double capacity) {
    super(wallet);
    this.capacity = capacity;
    this.blocks = new ArrayList<Block>();
  }

  @Override
  public String fullName() {
    return String.format("MiningNode#%03d", this.id);
  }

  /* GETTERS AND SETTERS */
  public double getCapacity() {
    return capacity;
  }

  public void setCapacity(double capacity) {
    this.capacity = capacity;
  }

  public List<Block> getBlocks() {
    return blocks;
  }

  @Override
  public String createHash(Block block) {
    Block prevBlock = block.getPrevBlock();
    return CommonUtils.sha256(
      String.format(
        block.getVersion() +
        (prevBlock == null ? BlockConfig.GENESIS_BLOCK : prevBlock.getHash()) +
        block.getTimestamp() +
        block.getDifficulty() +
        block.getNonce()
      )
    );
  }

  @Override
  public Block mineBlock(
    Transaction transaction,
    Block previousConfirmedBlock,
    String minerKey
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mineBlock'");
  }
}
