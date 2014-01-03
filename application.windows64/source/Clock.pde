import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * Provides a clock for my sports graphics programs.
 * 
 * @author Doug Blase
 * 
 */
public class Clock implements Comparable {
  private int secondDefinition;
  private int minutes;
  private int seconds;
  private int tenths;
  private final int PERIOD_LENGTH;
  private int counter;

  public Clock() {
    this.minutes = Integer.parseInt(JOptionPane.showInputDialog(
    null, "Length of Period"));
    this.PERIOD_LENGTH = minutes;
    this.seconds = 0;
    this.tenths = 1;
    this.secondDefinition = 60;
    this.counter = 54;
  }

  public Clock(int minutes, Clock gameClock) {
    this.minutes = minutes;
    this.seconds = 0;
    this.tenths = 9;
    this.secondDefinition = 60;
    this.counter = 0;
    this.PERIOD_LENGTH = 0;
  }

  public void speedUpClock() {
    secondDefinition -= 6;
  }

  public void slowDownClock() {
    secondDefinition += 6;
  }

  public void setSecondDefinition( int definition) {
    this.secondDefinition = definition;
  }

  public void tick() {

    seconds--;

    if (seconds < 0 && minutes>0) {
      seconds = 59;
      minutes--;
    }
  }

  public void runClock() {
    counter++;
    tenths = round(10 - counter / (secondDefinition/10));
    if (counter >= secondDefinition) {
      counter = 0;
      tick();
    }
  }

  public void resetClock() {
    tenths = 1;
    seconds = 0;
    minutes = PERIOD_LENGTH;
    this.counter = 6 * (10 - tenths);
  }

  public void setSeconds(int seconds) {
    this.seconds= seconds;
  }

  /**
   * @return the secondDefinition
   */
  public int getSecondDefinition() {
    return secondDefinition;
  }

  /**
   * @return the minutes
   */
  public int getMinutes() {
    return minutes;
  }

  /**
   * @return the seconds
   */
  public int getSeconds() {
    return seconds;
  }

  /**
   * @return the tenths
   */
  public int getTenths() {
    return tenths;
  }

  /**
   * @return the PERIOD_LENGTH
   */
  public int getPERIOD_LENGTH() {
    return PERIOD_LENGTH;
  }

  /**
   * @return the counter
   */
  public int getCounter() {
    return counter;
  }

  public void setClock() {
    String newMinutes = JOptionPane.showInputDialog(null, 
    "Minutes (leave blank if no change) ");
    String newSeconds = JOptionPane.showInputDialog(null, 
    "Seconds (leave blank if no change) ");
    String newTenths = JOptionPane.showInputDialog(null, 
    "Tenths (leave blank if no change)");
    if (newMinutes != null|| !newMinutes.equals(""))
      this.minutes = Integer.parseInt(newMinutes);
    if (newSeconds != null||!newSeconds.equals(""))
      this.seconds = Integer.parseInt(newSeconds);
    if (newTenths != null||!newTenths.equals("")) {
      this.tenths = Integer.parseInt(newTenths);
      this.counter = 6 * (10 - tenths);
    }
  }

  public String toString() {
    StringBuilder clockString = new StringBuilder();
    if (minutes > 0) {
      clockString.append(minutes + ":");
      if (seconds > 9)
        clockString.append(seconds);
      else clockString.append("0" + seconds);
    }

    else {
      clockString.append(seconds + ".");
      if (tenths == 10)
        clockString.append(0);
      else clockString.append(tenths);
    }
    return clockString.toString();
  }

  public String toStringPP() {
    StringBuilder clockString = new StringBuilder();

    clockString.append(minutes + ":");
    if (seconds > 9)
      clockString.append(seconds);
    else clockString.append("0" + seconds);

    return clockString.toString();
  }

  public String toStringForControl() {
    StringBuilder clockString = new StringBuilder();
    clockString.append(minutes + ":");
    if (seconds > 9)
      clockString.append(seconds);
    else clockString.append("0" + seconds);
    clockString.append(".");
    if (tenths == 10)
      clockString.append(0);
    else clockString.append(tenths);

    return clockString.toString();
  }

  public int getTimeLeft() {
    return minutes*60 + seconds + tenths;
  }

  public int compareTo(Object o) {
    int thisClock = this.getTimeLeft();
    Clock that = (Clock)o;
    int thatClock = that.getTimeLeft();

    if (thisClock<thatClock)
      return -1;

    if (thisClock==thatClock)
      return 0;

    else return 1;
  }
}

