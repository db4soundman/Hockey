import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Player {

  private String name;
  private String position;
  private String number;
  private int goalsGame;
  private int assistsGame;
  private int gamesPlayed;
  private ShotStats shots;
  private PenaltyStats penalty;
  private GoalieStats goalie;
  private int penaltiesGame;
  private int ptsGame;
  private int pimGame;
  private int ga;
  private int saves;
  private int savesSeason;
  private int totalShots;
  private String savePct;
  private int wins;
  private int losses;
  private boolean inGoal;
  private boolean hasBeenInGoal;

  @XmlElement(name = "player")
    private String playerElement;

  /**
   	 * @return the name
   	 */
  @XmlAttribute(name = "name")
    public String getName() {
      return name;
    }

  public void setName(String name) {
    this.name = name;
  }

  /**
   	 * @return the number
   	 */
  @XmlAttribute(name = "uni")
    public String getNumber() {
      return number;
    }

  public void setNumber(String n) {
    this.number = n;
  }

  /**
   	 * @return the gamesPlayed
   	 */
  @XmlAttribute(name = "gp")
    public int getGamesPlayed() {
      return gamesPlayed;
    }

  /**
   	 * @param gamesPlayed
   	 *            the gamesPlayed to set
   	 */
  public void setGamesPlayed(int gamesPlayed) {
    this.gamesPlayed = gamesPlayed;
  }

  /**
   	 * @return the shots
   	 */
  @XmlElement(name = "shots", required = true)
    public ShotStats getShots() {
      return shots;
    }

  /**
   	 * @param shots
   	 *            the shots to set
   	 */
  public void setShots(ShotStats shots) {
    this.shots = shots;
  }

  /**
   	 * @return the penalty
   	 */
  @XmlElement(name = "penalty")
    public PenaltyStats getPenalty() {
      return penalty;
    }

  /**
   	 * @param penalty
   	 *            the penalty to set
   	 */
  public void setPenalty(PenaltyStats penalty) {
    this.penalty = penalty;
  }

  /**
   	 * @return the goalie
   	 */
  @XmlElement(name = "goalie", required = false)
    public GoalieStats getGoalie() {
      return goalie;
    }

  /**
   	 * @param goalie
   	 *            the goalie to set
   	 */
  public void setGoalie(GoalieStats goalie) {
    this.goalie = goalie;
  }

  /**
   	 * @return the penaltiesGame
   	 */
  public int getPenaltiesGame() {
    return penaltiesGame;
  }

  /**
   	 * @param penaltiesGame
   	 *            the penaltiesGame to set
   	 */
  public void setPenaltiesGame(int penaltiesGame) {
    this.penaltiesGame = penaltiesGame;
  }

  /**
   	 * @return the pimGame
   	 */
  public int getPimGame() {
    return pimGame;
  }

  /**
   	 * @param pimGame
   	 *            the pimGame to set
   	 */
  public void setPimGame(int pimGame) {
    this.pimGame = pimGame;
  }

  /**
   	 * @return the ga
   	 */
  public int getGa() {
    return ga;
  }

  /**
   	 * @param ga
   	 *            the ga to set
   	 */
  public void setGa(int ga) {
    this.ga = ga;
    setInGoal(true);
  }

  /**
   	 * @return the saves
   	 */

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
   	 * @return the totalShots
   	 */
  public int getTotalShots() {
    return totalShots;
  }

  /**
   	 * @param totalShots
   	 *            the totalShots to set
   	 */
  public void setTotalShots(int totalShots) {
    this.totalShots = totalShots;
  }

  /**
   	 * @return the savePct
   	 */

  public String getSavePct() {
    return savePct;
  }

  /**
   	 * @return the inGoal
   	 */
  public boolean isInGoal() {
    return inGoal;
  }

  /**
   	 * @param inGoal
   	 *            the inGoal to set
   	 */
  public void setInGoal(boolean inGoal) {
    if (position.equals("G")) {
      this.inGoal = inGoal;
      hasBeenInGoal = true;
    }
  }

  public void setPtsGame(int nP) {
    this.ptsGame = nP;
  }

  public int getPtsGame() {
    return ptsGame;
  }

  // this.goalsGame = 0;
  // this.assistsGame = 0;
  // this.penaltiesGame = 0;
  // this.pimGame = 0;
  // this.ptsGame = 0;

  // else {
  // this.ga = 0;
  // this.saves = 0;
  // this.gamesPlayed = input.nextInt();
  // this.savesSeason = input.nextInt();
  // this.totalShots = input.nextInt();
  // this.gaSeason = totalShots - savesSeason;
  // if (gaSeason == 0)
  // this.gaa = "0%";
  // else {
  // this.gaa = (((float) (gaSeason) / (float) gamesPlayed))
  // + "";
  // this.gaa = gaa.substring(0, 3);
  // }
  // if (savesSeason == 0)
  // this.savePct = "0%";
  // else {
  // this.savePct = (((float) (savesSeason) / (float)
  // totalShots) * 100)
  // + "%";
  // if (savePct.length() >= 4)
  // this.savePct = savePct.substring(0, 4) + "%";
  // else this.savePct = savePct.substring(0, 3) + "%";
  // }
  // this.wins = input.nextInt();
  // this.losses = input.nextInt();
  // this.inGoal = false;
  // this.hasBeenInGoal = false;
  // }
  // }
  /**
   	 * @return the goalsGame
   	 */
  public int getGoalsGame() {
    return goalsGame;
  }

  /**
   	 * @param goalsGame
   	 *            the goalsGame to set
   	 */
  public void setGoalsGame(int goalsGame) {
    this.goalsGame = goalsGame;
  }

  /**
   	 * @return the assistsGame
   	 */
  public int getAssistsGame() {
    return assistsGame;
  }

  /**
   	 * @param assistsGame
   	 *            the assistsGame to set
   	 */
  public void setAssistsGame(int assistsGame) {
    this.assistsGame = assistsGame;
  }

  /**
   	 * @return the position
   	 */
  public String getPosition() {
    return position;
  }

  public void setPosition(String pos) {
    this.position = pos;
  }

  public String toString() {
    if (!position.equals("G"))
      return (position + " " + number + " "
        + (getPenalty().getPenalties() + penaltiesGame)
        + " " + (getPenalty().getPim() + pimGame) + " "
        + (gamesPlayed + 1) + " "
        + (getShots().getGoals() + goalsGame) + " "
        + (getShots().getAssists() + assistsGame) + " " + (getShots()
        .getPts() + ptsGame));

    else {
      if (hasBeenInGoal)
        gamesPlayed++;
      return (position + " " + number + " " + gamesPlayed + " "
        + (savesSeason + saves) + " "
        + (totalShots + saves + ga) + " " + wins + " " + losses);
    }
  }

  public void initializeGameVars() {
    if(this.name.contains(",")){
     this.name = (name.substring(name.indexOf(" ")+1) + " "+ name.substring(0, name.indexOf(","))); 
    }
    name = name.toUpperCase();
    this.goalsGame = 0;
    this.assistsGame = 0;
    this.penaltiesGame = 0;
    this.pimGame = 0;
    this.ptsGame = 0;
    this.ga = 0;
    this.saves = 0;
    if (this.getGoalie() !=null) {
      this.totalShots = this.getGoalie().getSaves()
        + this.getGoalie().getGa();
      if (this.getGoalie().getSaves() == 0)
        this.savePct = "0%";
      else {
        this.savePct = (((float) (this.getGoalie().getSaves()) / (float) totalShots) * 100)
          + "%";
        if (savePct.length() >= 4)
          this.savePct = savePct.substring(0, 4) + "%";
        else this.savePct = savePct.substring(0, 3) + "%";
      }
    }
  }

  public void createPositions() {
    if (this.getGoalie() !=null)
      this.setPosition("G");
    else this.setPosition("N");
  }
}

