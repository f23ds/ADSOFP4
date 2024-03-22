package Components;

/**
 * Clase para dar soporte a nodos mineros
 * 
 * @author Fabio Desio Alba López
 */
public class MiningNode extends Node {

  private double capacity; // En MIPS

  public MiningNode(
    Wallet wallet,
    double capacity
  ) {
    super(wallet);
    this.capacity = capacity;
  }


  @Override
  public String toString() {
    String miningStr = super.toString();

    miningStr = miningStr.substring(0, miningStr.length() - 8) + String.format("MiningNode#%03d", this.getId());
    return miningStr;
  }


  /* GETTERS AND SETTERS */
  public double getCapacity() {
    return capacity;
  }

  public void setCapacity(double capacity) {
    this.capacity = capacity;
  }
}
