package Notifications;

import Components.Transaction;
import Interfaces.IMessage;

/**
 * Clase para dar soporte a las notificaciones de las transacciones.
 * Se encarga de notificar a la red la creación de una nueva transacción.
 * Deben ser recibidos y gestionados por los nodos
 * 
 * @author Fabio Desio Alba López
 */
public class TransactionNotification implements IMessage {

    private Transaction transaction;

    

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }
}
