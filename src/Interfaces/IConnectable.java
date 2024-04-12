package Interfaces;

/**
 * Interfaz para enviar y recibir disintos tipos de mensajes
 * 
 * @author Fabio Desio Alba López
 */
public interface IConnectable {
  /**
   * Procesamiento del mensaje al:
   * a) Ejecutarlo sobre un nodo
   * b) Redistribuirlo sobre una red o subred
   * @param msg mensaje a procesar
   */
  public void broadcast(IMessage msg);

  /**
   * Función para gettear el padre del objeto IConnectable
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
