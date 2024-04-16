package Network;

import Interfaces.*;
import Notifications.*;
import java.util.*;

import Block.Block;
import Block.SimpleMining;
import Block.SimpleValidate;

/**
 * Clase para dar soporte a nodos mineros
 *
 * @author Fabio Desio Alba López
 */
public class MiningNode extends Node {

  private double capacity; // En MIPS
  private static List<Block> blocks = new ArrayList<Block>();
  private SimpleMining miningMethod;
  private SimpleValidate validateMethod;

  public MiningNode(Wallet wallet, double capacity) {
    super(wallet);
    this.capacity = capacity;
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

  public Block getPreviousConfirmedBlock() {
    return blocks.isEmpty() ? null : blocks.get(blocks.size() - 1);
  }

  @Override
  public void handleTransactionNotification(TransactionNotification txNoti) {
    Transaction tx = txNoti.getTransaction();
    IConnectable network = this.getTopParent();

    if (this.getTransactions().contains(tx)) {
      System.out.println(
        "[" +
        this.fullName() +
        "] Transaction already confirmed: Tx-" +
        tx.getId()
      );
    }

    if (!this.getTransactions().contains(tx) && miningMethod != null) {
      String minkerKey = this.getWallet().getPublicKey();
      Block block = miningMethod.mineBlock(
        tx,
        this.getPreviousConfirmedBlock(),
        minkerKey
      );

      blocks.add(block);
      block.setPrevBlock(block);

      System.out.println(
        String.format(
          "[" + this.fullName() + "] " + "Mined block: " + block.toString()
        )
      );
      network.broadcast(new ValidateBlockRq(block, this));
    }
  }

  @Override
  public void handleValidateBlockRq(ValidateBlockRq blockRqNoti) {
    IConnectable network = this.getTopParent();

    if (validateMethod != null) {
      if (blockRqNoti.getMiningNode() == this) {
        System.out.println(
          "[" + this.fullName() + "] " + "You cannot validate your own block"
        );
        return;
      }
    }
    Block block = blockRqNoti.getBlock();
    boolean isValidated = validateMethod.validate(miningMethod, block);
    block.setIsValidated(isValidated);

    System.out.println(
      String.format(
        "[" +
        this.fullName() +
        "] " +
        "Emitted Task: ValidateBlockRes <b:" +
        block.getId() +
        ", res:" +
        block.isIsValidated() +
        ", src:%03d" +
        ">",
        this.getId()
      )
    );

    network.broadcast(new ValidateBlockRes(block, isValidated, this.getId()));
  }

  @Override
  public boolean isMiningNode() {
    return true;
  }

  @Override
  public MiningNode getMiningNode() {
    return this;
  }

  @Override
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
