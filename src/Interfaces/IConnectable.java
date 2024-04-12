package Interfaces;

/**
 * Interfaz para enviar y recibir disintos tipos de mensajes
 * 
 * @author Fabio Desio Alba López
 */
public interface IConnectable {
  /**
   * Método abstracto para el procesar el mensaje por un nodo o redistribuirlo por la red o subred
   * @param msg mensaje a procesar
   */
  public void broadcast(IMessage msg);

  /**
   * Para soportar subredes dentro de redes
   * @return objeto IConnectable padre si lo hay, null en caso contrario
   */
  public IConnectable getParent();

  /**
   * getParent de nivel superior
   * @return objeto IConnectable de nivel superior, null en caso contrario
   */
  public default IConnectable getTopParent() {
    IConnectable parent = getParent();
    while (parent != null) {
      if (parent.getParent() == null) return parent;
      parent = parent.getParent();
    }
    return parent;
  }
}
