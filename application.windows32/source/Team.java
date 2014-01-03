import java.io.*;
//import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Scanner;
import javax.swing.JFileChooser;

//package de.vogella.xml.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import de.vogella.xml.jaxb.model.Bookstore;

@XmlRootElement(name = "hkseas")

public class Team {	

    private Totals totals;

    private ArrayList<Player> players;
  private String ppPercent;
  private String pkPercent;
  private int sogGame;



  /**
   	 * @return the totals
   	 */
  @XmlElement(name = "totals", required = true)
  public Totals getTotals() {
    return totals;
  }

  /**
   	 * @param totals the totals to set
   	 */
  public void setTotals(Totals totals) {
    this.totals = totals;
  }

  /**
   	 * @return the players
   	 */
     @XmlElement(name = "player", required = true)
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   	 * @param players
   	 *            the players to set
   	 */
  public void setPlayers(ArrayList<Player> players) {
    this.players = players;
  }
  
  /**
   * @return the ppPercent
   */
  public String getPpPercent() {
    return ppPercent;
  }

  /**
   * @param ppPercent the ppPercent to set
   */
  public void setPpPercent(String ppPercent) {
    this.ppPercent = ppPercent;
  }

  /**
   * @return the pkPercent
   */
  public String getPkPercent() {
    return pkPercent;
  }

  /**
   * @param pkPercent the pkPercent to set
   */
  public void setPkPercent(String pkPercent) {
    this.pkPercent = pkPercent;
  }

  /**
   * @return the sogGame
   */
  public int getSogGame() {
    return sogGame;
  }

  /**
   * @param sogGame the sogGame to set
   */
  public void setSogGame(int sogGame) {
    this.sogGame = sogGame;
  }

  public void save() {
    File playerData;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.showSaveDialog(null);
    playerData = fileChooser.getSelectedFile();
    PrintWriter output;
    try {
      output = new PrintWriter(playerData);
      output.println(getTotals().getPpStats().getPpg() + " " + getTotals().getPpStats().getPpc() + " " + getTotals().getPpStats().getPks() + " " + getTotals().getPpStats().getPkc());
      for (Player p : players) {
        output.println(p.getName());
        output.println(p.toString());
      }
      output.close();
    }
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void recalculatePowerPlayStats() {
    this.ppPercent = ""
      + ((((double) (getTotals().getPpStats().getPpg()) / (double) (getTotals().getPpStats().getPpc()) * 100)));
    if (getTotals().getPpStats().getPpg() == 0)
      this.ppPercent = "0%";
    else {
      if (ppPercent.length() < 4)
        this.ppPercent = ppPercent.substring(0, 3) + "%";
      else this.ppPercent = ppPercent.substring(0, 4) + "%";
    }
    this.pkPercent = ""
      + ((((double) (getTotals().getPpStats().getPks()) / (double) (getTotals().getPpStats().getPkc()) * 100)));
    if (getTotals().getPpStats().getPks() == 0)
      this.pkPercent = "0%";
    else {
      if (pkPercent.length() < 4)
        this.pkPercent = pkPercent.substring(0, 3);
      else this.pkPercent = pkPercent.substring(0, 4) + "%";
    }
  }
  
  public void setPositions(){
   for(Player p: players){
    p.createPositions();
    p.initializeGameVars();
   }
  this.recalculatePowerPlayStats(); 
  }
}

