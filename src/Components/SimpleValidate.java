package Components;

import Interfaces.IMiningMethod;
import Interfaces.IValidateMethod;

public class SimpleValidate implements IValidateMethod {

  @Override
  public boolean validate(IMiningMethod miningMethod, Block block) {
    
    return miningMethod.createHash(block) == block.getHash();
  }
}
