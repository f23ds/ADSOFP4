package Components;

import Interfaces.*;
import java.util.*;

/**
 * Clase para dar soporte a nodos mineros
 *
 * @author Fabio Desio Alba López
 */
public class MiningNode extends Node {

  private double capacity; // En MIPS
  private List<Block> blocks;
  private SimpleMining miningMethod;
  private Block previousConfirmedBlock;

  public MiningNode(Wallet wallet, double capacity) {
    super(wallet);
    this.capacity = capacity;
    this.blocks = new ArrayList<Block>();
    this.previousConfirmedBlock = null;
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
  public void broadcast(IMessage msg) {
    super.broadcast(msg);
    if (msg.isTransactionNotification()) {
      Transaction tx = msg.getTransactionNotification().getTransaction();
      if (!tx.isConfirmed()) {
        SimpleMining sm = new SimpleMining();
        String minerKey = this.getWallet().getPublicKey();
        Block block = sm.mineBlock(tx, this.previousConfirmedBlock, minerKey);
        blocks.add(block);
        previousConfirmedBlock = block;
        System.out.println(
          String.format(
            "[" + this.fullName() + "] Mined block: " + block.toString()
          )
        );
      }
    }
  }

  public SimpleMining getMiningMethod() {
    return this.miningMethod;
  }

  public void setMiningMethod(SimpleMining simpleMining) {
    this.miningMethod = simpleMining;
  }

    /**
     * @return Block return the previousConfirmedBlock
     */
    public Block getPreviousConfirmedBlock() {
        return previousConfirmedBlock;
    }

    /**
     * @param previousConfirmedBlock the previousConfirmedBlock to set
     */
    public void setPreviousConfirmedBlock(Block previousConfirmedBlock) {
        this.previousConfirmedBlock = previousConfirmedBlock;
    }

}
