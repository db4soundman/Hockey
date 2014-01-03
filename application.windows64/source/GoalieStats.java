import javax.xml.bind.annotation.XmlAttribute;

public class GoalieStats {
  private int ga;
  private int saves;
  private String gaa;
  private int wins;
  private int losses;

  /**
   	 * @return the ga
   	 */
  @XmlAttribute(name = "ga")
    public int getGa() {
      return ga;
    }

  /**
   	 * @param ga
   	 *            the ga to set
   	 */
  public void setGa(int ga) {
    this.ga = ga;
  }

  /**
   	 * @return the saves
   	 */
  @XmlAttribute(name = "saves")
    public int getSaves() {
      return saves;
    }

  /**
   	 * @param saves
   	 *            the saves to set
   	 */
  public void setSaves(int saves) {
    this.saves = saves;
  }

  /**
   	 * @return the gaa
   	 */
  @XmlAttribute(name = "gaavg")
    public String getGaa() {
      return gaa;
    }

  /**
   	 * @param gaa
   	 *            the gaa to set
   	 */
  public void setGaa(String gaa) {
    this.gaa = gaa;
  }

  /**
   	 * @return the wins
   	 */
  @XmlAttribute(name = "w")
    public int getWins() {
      return wins;
    }

  /**
   	 * @param wins
   	 *            the wins to set
   	 */
  public void setWins(int wins) {
    this.wins = wins;
  }

  /**
   	 * @return the losses
   	 */
  @XmlAttribute(name = "l")
    public int getLosses() {
      return losses;
    }

  /**
   	 * @param losses
   	 *            the losses to set
   	 */
  public void setLosses(int losses) {
    this.losses = losses;
  }

  public boolean goalieCheck() {
    if (this.gaa !=null)
      return true;
    else return false;
  }
}

