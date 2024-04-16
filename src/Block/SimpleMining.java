package Block;

import Interfaces.IMiningMethod;
import Network.Transaction;
import blockchain.utils.*;

/**
 * Implementación simple de un método de minería para la creación de bloques.
 * Este método utiliza el algoritmo SHA-256 para calcular el hash del bloque.
 * @author Fabio Desio Alba Lopez
 */
public class SimpleMining implements IMiningMethod {

  /**
   * Crea el hash para un bloque dado utilizando el algoritmo SHA-256.
   * @param block el bloque para el cual se calculará el hash
   * @return el hash calculado
   */
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

  /**
   * Mina un nuevo bloque utilizando el método de minería simple.
   * @param transaction la transacción asociada al bloque
   * @param previousConfirmedBlock el bloque previamente confirmado
   * @param minerKey la clave pública del minero
   * @return el nuevo bloque minado
   */
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
