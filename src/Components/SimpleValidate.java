package Components;

import Interfaces.IMiningMethod;
import Interfaces.IValidateMethod;

/**
 * Implementación simple de un método de validación para bloques.
 * Este método verifica si el hash calculado por el método de minería coincide con el hash del bloque.
 * @author Fabio Desio Alba Lopez
 */
public class SimpleValidate implements IValidateMethod {

  /**
   * Valida un bloque utilizando el método de minería proporcionado.
   * @param miningMethod el método de minería utilizado para crear el bloque
   * @param block el bloque a validar
   * @return true si el bloque es válido, false de lo contrario
   */
  @Override
  public boolean validate(IMiningMethod miningMethod, Block block) {
    return miningMethod.createHash(block).equals(block.getHash());
  }
}
