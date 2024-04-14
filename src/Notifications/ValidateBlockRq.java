package Notifications;

import Components.*;
import Components.MiningNode;
import Interfaces.IMessage;

public class ValidateBlockRq implements IMessage {

  private Block block;
  private MiningNode miningNode;

  public ValidateBlockRq(Block block, MiningNode miningNode) {
    this.block = block;
    this.miningNode = miningNode;
  }

  @Override
  public String getMessage() {
    return String.format(
      "<b:" + block.getId() + ", src:" + miningNode.getId() + ">"
    );
  }

  @Override
  public boolean isTransactionNotification() {
    return false;
  }

  @Override
  public TransactionNotification getTransactionNotification() {
    return null;
  }

  @Override
  public boolean isValidateBlockRq() {
    return true;
  }

  @Override
  public ValidateBlockRq getValidateBlockRq() {
    return this;
  }

  public Block getBlock() {
    return block;
  }

  public MiningNode getMiningNode() {
    return miningNode;
  }
}
