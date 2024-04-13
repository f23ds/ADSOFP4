package Components;

import java.util.*;

/**
 * Clase para dar soporte a nodos mineros
 *
 * @author Fabio Desio Alba López
 */
public class MiningNode extends Node {

  private double capacity; // En MIPS
  private List<Block> blocks;

  public MiningNode(Wallet wallet, double capacity) {
    super(wallet);
    this.capacity = capacity;
    this.blocks = new ArrayList<Block>();
  }

  @Override
  public String fullName() {
    return String.format("MiningNode#%03d", this.id);
  }

  /* GETTERS AND SETTERS */
  public double getCapacity() {
    return capacity;
  }

  public void setCapacity(double capacity) {
    this.capacity = capacity;
  }

  public List<Block> getBlocks() {
    return blocks;
  }
}
