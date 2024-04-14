package Components;

import Interfaces.*;
import Notifications.ValidateBlockRq;
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
  private SimpleValidate validateMethod;

  public MiningNode(Wallet wallet, double capacity) {
    super(wallet);
    this.capacity = capacity;
    this.blocks = new ArrayList<Block>();
    this.miningMethod = null;
    this.validateMethod = null;
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

  private Block mineFromTx(Transaction tx) {
    String minerKey = this.getWallet().getPublicKey();
    Block block = miningMethod.mineBlock(
      tx,
      miningMethod.getPreviousConfirmedBlock(),
      minerKey
    );
    blocks.add(block);
    miningMethod.setPreviousConfirmedBlock(block);
    System.out.println(
      String.format(
        "[" + this.fullName() + "] Mined block: " + block.toString()
      )
    );

    return block;
  }

  @Override
  public void broadcast(IMessage msg) {
    super.broadcast(msg);
    IConnectable network = this.getTopParent();
    if (msg.isTransactionNotification()) {
      Transaction tx = msg.getTransactionNotification().getTransaction();
      if (!tx.isConfirmed() && miningMethod != null) {
        Block blockToValidate = this.mineFromTx(tx);
        network.broadcast(new ValidateBlockRq(blockToValidate, this));
      }
    }
    if (msg.isValidateBlockRq() && validateMethod != null) {
      if (msg.getValidateBlockRq().getMiningNode() == this) {
        System.out.println(
          "[" + this.fullName() + "] You cannot validate your own block"
        );
        return;
      }
      boolean isValidated = validateMethod.validate(
        miningMethod,
        msg.getValidateBlockRq().getBlock()
      );
      network.broadcast(msg);

    }
  }

  public SimpleMining getMiningMethod() {
    return this.miningMethod;
  }

  public void setMiningMethod(SimpleMining simpleMining) {
    this.miningMethod = simpleMining;
  }

  public SimpleValidate getValidationMethod() {
    return this.validateMethod;
  }

  public void setValidationMethod(SimpleValidate simpleValidate) {
    this.validateMethod = simpleValidate;
  }
}
