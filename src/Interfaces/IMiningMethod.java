package Interfaces;

import Components.*;

public interface IMiningMethod {
    String createHash(Block block);
    Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey);
}
