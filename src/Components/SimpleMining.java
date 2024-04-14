package Components;

import Interfaces.IMiningMethod;
import blockchain.utils.*;

public class SimpleMining implements IMiningMethod {

  private Block previousConfirmedBlock;

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
