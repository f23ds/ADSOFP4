package Notifications;

import Components.*;
import Interfaces.IMessage;

public class ValidateBlockRes implements IMessage {

  Block block;
  boolean isValidated;
  int src;

  public ValidateBlockRes(Block block, boolean isValidated, int src) {
    this.block = block;
    this.isValidated = isValidated;
    this.src = src;
  }

  @Override
  public String getMessage() {
    return String.format(
      "<b:" + block.getId() + ", res:" + isValidated + ", src:%03d" + ">",
      src
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
    return false;
  }

  @Override
  public ValidateBlockRq getValidateBlockRq() {
    return null;
  }

  @Override
  public boolean isValidateBlockRes() {
    return true;
  }

  @Override
  public ValidateBlockRes getValidateBlockRes() {
    return this;
  }
}
