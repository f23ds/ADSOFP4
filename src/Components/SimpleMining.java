package Components;

import Interfaces.IMiningMethod;
import blockchain.utils.*;

public class SimpleMining implements IMiningMethod {

  @Override
  public String createHash(Block block) {
    Block prevBlock = block.getPrevBlock();
    String hash = CommonUtils.sha256(
      String.format(
        block.getVersion() +
        (prevBlock == null ? BlockConfig.GENESIS_BLOCK : prevBlock.getHash()) +
        block.getTimestamp() +
        block.getDifficulty() +
        block.getNonce()
      )
    );

    block.setHash(hash);

    return hash;
  }

  @Override
  public Block mineBlock(
    Transaction transaction,
    Block previousConfirmedBlock,
    String minerKey
  ) {
    Block block = new Block(transaction, previousConfirmedBlock, minerKey);
    createHash(block);
    return block;
  }
}
