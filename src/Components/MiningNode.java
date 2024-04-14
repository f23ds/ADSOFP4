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

  public MiningNode(Wallet wallet, double capacity) {
    super(wallet);
    this.capacity = capacity;
    this.blocks = new ArrayList<Block>();
    this.miningMethod = null;
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

  private void mineFromTx(Transaction tx) {
    this.miningMethod = new SimpleMining();
    String minerKey = this.getWallet().getPublicKey();
    Block block = this.miningMethod.mineBlock(tx, this.miningMethod.getPreviousConfirmedBlock(), minerKey);
    blocks.add(block);
    this.miningMethod.setPreviousConfirmedBlock(block);
    System.out.println(
      String.format(
        "[" + this.fullName() + "] Mined block: " + block.toString()
      )
    );
  }

  @Override
  public void broadcast(IMessage msg) {
    super.broadcast(msg);
    if (msg.isTransactionNotification()) {
      Transaction tx = msg.getTransactionNotification().getTransaction();
      if (!tx.isConfirmed()) this.mineFromTx(tx);
    }
  }

  public SimpleMining getMiningMethod() {
    return this.miningMethod;
  }

  public void setMiningMethod(SimpleMining simpleMining) {
    this.miningMethod = simpleMining;
  }
}
