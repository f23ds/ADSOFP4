package Notifications;

import Components.*;
import Interfaces.IMessage;

public class ValidateBlockRes implements IMessage {

  Block block;
  boolean isValidated;

  public ValidateBlockRes(Block block, boolean isValidated) {
    this.block = block;
    this.isValidated = isValidated;
  }

  @Override
  public String getMessage() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getMessage'"
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
