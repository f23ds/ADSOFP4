package Interfaces;

import Block.Block;
import Network.*;

/**
 * Interfaz para definir un método de validación para bloques.
 * Un método de validación verifica la validez de un bloque utilizando un método de minería.
 * @author Fabio Desio Alba Lopez
 */
public interface IValidateMethod {
    /**
     * Valida un bloque utilizando el método de minería proporcionado.
     * @param miningMethod el método de minería utilizado para crear el bloque
     * @param block el bloque a validar
     * @return true si el bloque es válido, false de lo contrario
     */
    boolean validate(IMiningMethod miningMethod, Block block);
}
