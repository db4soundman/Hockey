import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class HockeyGame {

  private String awayTeamName;
  private String homeTeamName;
  private int awayTeamScore;
  private int homeTeamScore;
  private int period;
  private Clock clock;
  private boolean clockIsRunning;

  private final int MAX_PERIODS;
  private Scoreboard scoreboard;

  /**
   * 
   */
  public HockeyGame() {
    this.awayTeamName = JOptionPane.showInputDialog(null, 
    "Visitors: ");
    this.awayTeamName=awayTeamName.toUpperCase();
    this.homeTeamName = JOptionPane.showInputDialog(null, 
    "Home: ");
    this.homeTeamName=homeTeamName.toUpperCase();
    this.awayTeamScore = 0;
    this.homeTeamScore = 0;
    this.period = 1;
    this.clock = new Clock();
    this.clockIsRunning = false;
    this.MAX_PERIODS = 3;
    this.scoreboard = new Scoreboard(awayTeamName, homeTeamName);
  }

  /**
   * @return the clock
   */
  public Clock getClock() {
    return clock;
  }

  /**
   * @return the homeTeamScore
   */
  public int getHomeTeamScore() {
    return homeTeamScore;
  }

  /**
   * @param homeTeamScore
   *            the homeTeamScore to set
   */
  public void setHomeTeamScore(int delta) {
    this.homeTeamScore = delta;
  }

  /**
   * @return the clockIsRunning
   */
  public boolean isClockRunning() {
    return clockIsRunning;
  }

  /**
   * @param clockIsRunning
   *            the clockIsRunning to set
   */
  public void runClock() {

    this.clockIsRunning = !clockIsRunning;
  }

  /**
   * @return the awayTeamName
   */
  public String getAwayTeamName() {
    return awayTeamName;
  }

  /**
   * @return the homeTeamName
   */
  public String getHomeTeamName() {
    return homeTeamName;
  }

  /**
   * @return the awayTeamScore
   */
  public int getAwayTeamScore() {
    return awayTeamScore;
  }
  public void setAwayTeamScore(int delta) {
    this.awayTeamScore =delta;
  }

  /**
   * @return the period
   */
  public int getPeriod() {
    return period;
  }

  /**
   * @param period
   *            the period to set
   */
  public void setPeriod(int period) {
    this.period = period;
  }


  /**
   * @return the MAX_PERIODS
   */
  public int getMAX_PERIODS() {
    return MAX_PERIODS;
  }

  public void displayScoreboard(String special) {
    if (clock.getMinutes() == 0 && clock.getSeconds() < 0
      && clock.getTenths() == 0) { 
      clock.setSeconds(0);
      clockIsRunning = false;
    }
    if (special.equals("INT"))
      this.scoreboard.displayScoreboard(awayTeamName, 
      awayTeamScore, homeTeamName, homeTeamScore, period, 
      "INT");
    if (special.equals("FINAL"))
      this.scoreboard.displayScoreboard(awayTeamName, 
      awayTeamScore, homeTeamName, homeTeamScore, period, 
      "FINAL");

    if (special.equals("CLOCK"))
      this.scoreboard.displayScoreboard(awayTeamName, 
      awayTeamScore, homeTeamName, homeTeamScore, period, 
      clock.toString());
  }

  public void commercial(String special) {
    if (special.equals("INT"))
      this.scoreboard.displayCommercial(awayTeamName, 
      awayTeamScore, homeTeamName, homeTeamScore, period, 
      "INT");
    if (special.equals("FINAL"))
      this.scoreboard.displayCommercial(awayTeamName, 
      awayTeamScore, homeTeamName, homeTeamScore, period, 
      "FINAL");

    if (special.equals("CLOCK"))
      this.scoreboard.displayCommercial(awayTeamName, 
      awayTeamScore, homeTeamName, homeTeamScore, period, 
      clock.toString());
  }

  public Scoreboard getScoreboard() {
    return this.scoreboard;
  }
}

