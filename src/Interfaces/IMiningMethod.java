package Interfaces;

import Block.Block;
import Network.*;

/**
 * Interfaz para definir un método de minería.
 * Un método de minería es responsable de crear un hash para un bloque y de minar un nuevo bloque.
 * @author Fabio Desio y Alba Lopez
 */
public interface IMiningMethod {
    /**
     * Crea el hash para un bloque dado.
     * @param block el bloque para el cual se calculará el hash
     * @return el hash creado
     */
    String createHash(Block block);

    /**
     * Mina un nuevo bloque utilizando la transacción, el bloque previamente confirmado y la clave pública del minero.
     * @param transaction la transacción asociada al bloque
     * @param previousConfirmedBlock el bloque previamente confirmado
     * @param minerKey la clave pública del minero
     * @return el nuevo bloque minado
     */
    Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey);
}
