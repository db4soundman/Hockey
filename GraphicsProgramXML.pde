/*HOCKEY GRAPHICS PROGRAM FOR MIAMI ALL-ACCESS
 CREATED BY DOUG BLASE, ALMOST 2 WEEKS EXACTLY,
 DETERMINED FINISHED SAT 6/29/2013, 8:10PM.
 
 */


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
ArrayList<String> penalties;
ArrayList<Clock> homePPClock, awayPPClock;
PFont myFont;
PFont font24;
PFont font18;
PFont font16;
PFont font14;
PFont font12;
PFont font10;
PImage sb;
PImage sg;
PImage maa;
PImage ppBar;
PImage statBar;
PImage standingsGraphic;
boolean showCommercial = false;
boolean showScoreboard = false;
boolean showPP = false;
boolean intermission = false;
boolean showFinal = false;
boolean showSO = true;
boolean showLowerThird = false;
boolean showStatsBar = false;
boolean showSponsor = false;
boolean previousSponsor = false;
boolean penaltyFlag = false;
boolean showStandings = false;
Color background;
color bg;
HockeyGame game;
JRadioButton homeTeam, awayTeam, sogButton, ppButton, gameStats, season, 
summary, goal, penalty;
JLabel clockCommands, awayName, homeName, awayScore, homeScore, clock, 
awayShotsOnGoal, homeShotsOnGoal;
JFrame controlPanel, standingsUI;
JPanel clockControl, displayControls, clockLabel, status1, status2, status3, scoreControl;
JButton runClock, resetClock, setClock, hideGraphics, homeUp1, awayUp1, homeDown1, awayDown1, nchc, 
clockSpeedUp, clockSpeedDn, commercial, showBoard, nextPeriod, prevPeriod, finalButton, intButton, clockButton, 
showLT, hideLT, showSB, hideSB, submit, ap2, ap4, ap5, hp2, hp5, hp4, deleteA, deleteH, showPPClock, penaltyFlagButton, 
save, showTeamStat, hideTeamStat, sponsorButton, hSog1, aSog1, setHsog, setAsog, hPPg, aPPg, hPPf, aPPf;

Team homeTEAM, awayTEAM;

JTextField textField;
JComboBox playerSelectH, playerSelectA, assist1H, assist1A, 
assist2H, assist2A, penaltyNames, hGoalie, aGoalie;

String timeThisHappened;
String statsBarText, name1, name2, name3, ltTeam, ltType, sponsorText;
Player playerForGraphic;
int homePlayersOnIce, awayPlayersOnIce;
String[] teams = {
  "MIAMI", "DENVER", "COLORADO COLLEGE", "MINNESOTA-DULUTH", "NEBRASKA-OMAHA", "NORTH DAKOTA", "ST. CLOUD STATE", "W. MICHIGAN"
};
JComboBox[] teamNames = new JComboBox[teams.length];
JTextField[][] recordNums = new JTextField[teams.length][4];
JWindow graphicsFrame;

int homePreSOscore = 0;
int awayPreSOscore = 0;

void setup() {
  //-----------FONTS AND IMAGES--------------//
  myFont = loadFont("ArialMT-20.vlw");
  font24 = loadFont("ArialMT-24.vlw");
  font18 = loadFont("ArialMT-18.vlw");
  font16 = loadFont("ArialMT-16.vlw");
  font14 = loadFont("ArialMT-14.vlw");
  font12 = loadFont("ArialMT-12.vlw");
  font10 = loadFont("ArialMT-10.vlw");
  sb = loadImage("Scoreboard.png");
  sg = loadImage("StatsGraphic.png");
  maa = loadImage("M.png");
  ppBar = loadImage("PPBar.png");
  statBar = loadImage("StatBar.png");
  standingsGraphic = loadImage("standings.png");

  size(1920, 1080);
  //size(1280, 1024);
  smooth();

  game = new HockeyGame();
  background = JColorChooser.showDialog(null, "Background", new Color(0,102,0));
  bg = color(background.getRed(), background.getGreen(), background.getBlue());
  try {
    JAXBContext context = JAXBContext
      .newInstance(Team.class);

    File playerData;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.showOpenDialog(null);
    playerData = fileChooser.getSelectedFile();
    Unmarshaller um = context.createUnmarshaller();

    awayTEAM = (Team) um.unmarshal(new FileReader(
    playerData));


    JFileChooser fileChooser2 = new JFileChooser();
    fileChooser2.showOpenDialog(null);
    playerData = fileChooser2.getSelectedFile();
    Unmarshaller um2 = context.createUnmarshaller();

    um2.unmarshal(new FileReader(playerData));

    homeTEAM = (Team) um2.unmarshal(new FileReader(
    playerData));
  }

  catch(Exception e) {
    e.printStackTrace();
    //println("oops");
  }


  //  awayTEAM = new Team();
  //  homeTEAM = new Team();
  controlPanel = new JFrame("Scoreboard Control");
  controlPanel.setBounds(100, 50, 800, 700);
  controlPanel.setResizable(false);
  controlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  controlPanel.setLayout(null);

  penalties = new ArrayList<String>();
  try {

    Scanner penaltyScanner;
    File penaltyFile;
    JFileChooser fileChooserP = new JFileChooser();
    fileChooserP.showOpenDialog(null);
    penaltyFile = fileChooserP.getSelectedFile();
    penaltyScanner = new Scanner(penaltyFile);
    while (penaltyScanner.hasNext ()) {
      penalties.add(penaltyScanner.nextLine());
    }

    penaltyScanner.close();
  }
  catch(Exception e) {
  }
  awayTEAM.setPositions();
  homeTEAM.setPositions();
  homePPClock = new ArrayList<Clock>();
  awayPPClock = new ArrayList<Clock>();

  makeClockControl();
  displayControls();
  makeStatusPanel();
  makeScoreControl();
  makeGraphicsControl();
  makePenaltyControl();
  makeStandings();



  homeTeam.setText(game.getHomeTeamName());
  homeTeam.setSelected(true);
  season.setSelected(true);
  summary.setSelected(true);
  playerSelectH.setEnabled(true);
  awayTeam.setText(game.getAwayTeamName());
  sogButton.setSelected(true);

  controlPanel.setVisible(true);
  repaint();

  graphicsFrame = new JWindow();
  graphicsFrame.setLayout(null);
  graphicsFrame.setVisible(true);
  graphicsFrame.setBounds(1280, 0, 1920, 1080);
  //graphicsFrame.setBounds(1280, 0, 1280, 1024);
  graphicsFrame.add(this);
  this.frame.setExtendedState(JFrame.ICONIFIED);
  this.frame.setVisible(false);
  JOptionPane.showMessageDialog(null, "Remember to set the Goalies.", 
  "REMINDER", 
  JOptionPane.INFORMATION_MESSAGE);
  standingsUI.setVisible(true);
}


void draw() {
  background(bg);
  if (showStandings)
    game.getScoreboard().showStandingsGraphic();

  if (showLowerThird)
    game.getScoreboard().showLowerThird(playerForGraphic, ltTeam, ltType);

  if (game.isClockRunning()) {
    game.getClock().runClock();

    for (Clock c:awayPPClock) {
      c.runClock();
    }

    for (Clock c:homePPClock) {
      c.runClock();
    }
  }
  if (awayPPClock.size()>0) {
    if (awayPPClock.get(0).getMinutes() == 0 && awayPPClock.get(0).getSeconds() < 0
      && awayPPClock.get(0).getTenths() == 0) 
      awayPPClock.remove(0);
  }
  if (homePPClock.size()>0) {
    if (homePPClock.get(0).getMinutes() == 0 && homePPClock.get(0).getSeconds() < 0
      && homePPClock.get(0).getTenths() == 0) 
      homePPClock.remove(0);
  }
  if (showScoreboard) {
    if (penaltyFlag)
      game.getScoreboard().showPenalty();

    if (intermission)
      game.displayScoreboard("INT");
    if (showFinal)
      game.displayScoreboard("FINAL");
    if (!intermission&&!showFinal)
      game.displayScoreboard("CLOCK");

    if (showSponsor)
      game.getScoreboard().showSponsorInfo(sponsorText);

    if (showStatsBar)
      game.getScoreboard().showStatsBar(statsBarText);

    if (showPP) {
      awayPlayersOnIce = 5-awayPPClock.size();
      homePlayersOnIce = 5-homePPClock.size();

      if (homePPClock.size()>0 ||awayPPClock.size()>0) {
        if (homePPClock.size()>0 && awayPPClock.size()>0)

          if (homePlayersOnIce==awayPlayersOnIce) {
            if (homePPClock.get(0).getTimeLeft()<=awayPPClock.get(0).getTimeLeft())
              game.getScoreboard().showPPBarNeutral( homePlayersOnIce+"-ON-"+awayPlayersOnIce, homePPClock.get(0));
            else if (homePPClock.get(0).getTimeLeft()>awayPPClock.get(0).getTimeLeft())
              game.getScoreboard().showPPBarNeutral( homePlayersOnIce+"-ON-"+awayPlayersOnIce, awayPPClock.get(0));
          }


        if (homePlayersOnIce>awayPlayersOnIce) {
          if (awayPlayersOnIce==4)
            game.getScoreboard().showPPBarHome("POWER PLAY", awayPPClock.get(0));
          else {
            if (homePlayersOnIce<5) {
              Clock cForPP;
              if (homePPClock.get(0).getTimeLeft()<=awayPPClock.get(0).getTimeLeft())
                cForPP = homePPClock.get(0);
              else cForPP = awayPPClock.get(0);
              game.getScoreboard().showPPBarHome(homePlayersOnIce+"-ON-" +awayPlayersOnIce, cForPP);
            }
            else game.getScoreboard().showPPBarHome(homePlayersOnIce+"-ON-" +awayPlayersOnIce, awayPPClock.get(0));
          }
        }

        else if (awayPlayersOnIce>homePlayersOnIce) {
          if (homePlayersOnIce==4)
            game.getScoreboard().showPPBarAway("POWER PLAY", homePPClock.get(0)); 
          else {
            if (awayPlayersOnIce<5) {
              Clock cForPP;
              if (homePPClock.get(0).getTimeLeft()<=awayPPClock.get(0).getTimeLeft())
                cForPP = homePPClock.get(0);
              else cForPP = awayPPClock.get(0);
              game.getScoreboard().showPPBarAway(awayPlayersOnIce+"-ON-" +homePlayersOnIce, cForPP);
            }
            else game.getScoreboard().showPPBarAway(awayPlayersOnIce+"-ON-" +homePlayersOnIce, homePPClock.get(0));
          }
        }
      }
    }
  }

  if (showCommercial) {
    if (intermission)
      game.commercial("INT");
    if (showFinal)
      game.commercial("FINAL");
    if (!intermission&&!showFinal)
      game.commercial("CLOCK");
  }

  awayShotsOnGoal.setText("A. SOG: " +awayTEAM.getSogGame());
  homeShotsOnGoal.setText("H. SOG: " +homeTEAM.getSogGame());
  awayScore.setText(""+game.getAwayTeamScore());
  homeScore.setText(""+game.getHomeTeamScore());
  clock.setText(game.getPeriod() + "      " + game.getClock().toStringForControl());

  //repaint();
  if (game.isClockRunning())
    runClock.setText("Stop Clock");
  else runClock.setText("Run Clock");
  if (showPP)
    showPPClock.setText("Hide");
  else showPPClock.setText("Show");
}

void statsBarSetup() {
  if (goal.isSelected()) {
    if (homeTeam.isSelected()) {
      name1=(String)playerSelectH.getSelectedItem();
      name2=(String)assist1H.getSelectedItem();
      name3=(String)assist2H.getSelectedItem();
      statsBarText = game.getHomeTeamName()+ " GOAL: " +name1;
      if (assist1H.getSelectedItem().equals("UNASSISTED"))
        statsBarText += " (Unassisted) "+timeThisHappened;
      else if (assist2H.getSelectedItem().equals("UNASSISTED"))
        statsBarText += " ("+name2.substring(name2.indexOf(" ")+1) +") " + timeThisHappened;
      else statsBarText += " (" +name2.substring(name2.indexOf(" ")+1) + ", "+ name3.substring(name3.indexOf(" ")+1) + ") " + timeThisHappened;
    }

    else {
      name1=(String)playerSelectA.getSelectedItem();
      name2=(String)assist1A.getSelectedItem();
      name3=(String)assist2A.getSelectedItem();
      statsBarText = game.getAwayTeamName()+" GOAL: "+name1;
      if (assist1A.getSelectedItem().equals("UNASSISTED"))
        statsBarText += " (Unassisted) "+timeThisHappened;
      else if (assist2A.getSelectedItem().equals("UNASSISTED"))
        statsBarText += " ("+name2.substring(name2.indexOf(" ")+1) +") " + timeThisHappened;
      else statsBarText += " ("+name2.substring(name2.indexOf(" ")+1)+", "+ (name3.substring(name3.indexOf(" ")+1)) +") " + timeThisHappened;
    }
  }

  if (summary.isSelected()) {

    if (homeTeam.isSelected()) {
      name1=(String)playerSelectH.getSelectedItem();
      int index = playerSelectH.getSelectedIndex();
      Player player = homeTEAM.getPlayers().get(playerSelectH.getSelectedIndex());
      if (!player.getPosition().equals("G")) {
        if (season.isSelected())
          statsBarText = name1 + " ("+game.getHomeTeamName()+"):  "+player.getGamesPlayed()
            + " GP, " + player.getShots().getGoals() + " G, " + player.getShots().getAssists() + " A, "
              + player.getShots().getPts() + " PTS";

        else {
          statsBarText = name1 + " ("+game.getHomeTeamName()+"):  "+ player.getGoalsGame() + " G, " 
            + player.getAssistsGame() + " A, " + player.getPtsGame()+ " PTS, "+ player.getPimGame() + " PIM";
        }
      }
      else {
        if (season.isSelected())
          statsBarText = name1 + " ("+game.getHomeTeamName()+"):  "+player.getGamesPlayed()
            + " GP, " + player.getGoalie().getWins() + "-" +player.getGoalie().getLosses() +", " + player.getGoalie().getGaa() +" GAA, "
              +player.getSavePct() + " SV PCT";

        else {
          statsBarText = name1 + " ("+game.getHomeTeamName()+"):  "+ player.getGa() + " GA, " 
            + player.getSaves() + " SAVES";
        }
      }
    }

    else {
      name1=(String)playerSelectA.getSelectedItem();
      int index = playerSelectA.getSelectedIndex();
      Player player = awayTEAM.getPlayers().get(playerSelectA.getSelectedIndex());
      if (!player.getPosition().equals("G")) {
        if (season.isSelected())
          statsBarText = name1 + " ("+game.getAwayTeamName()+"):  "+player.getGamesPlayed()
            + " GP, " + player.getShots().getGoals() + " G, " + player.getShots().getAssists() + " A, "
              + player.getShots().getPts() + " PTS";

        else {
          statsBarText = name1 + " ("+game.getAwayTeamName()+"):  "+ player.getGoalsGame() + " G, " 
            + player.getAssistsGame() + " A, " + player.getPtsGame()+ " PTS, "+ player.getPimGame() + " PIM";
        }
      }
      else {
        if (season.isSelected())
          statsBarText = name1 + " ("+game.getAwayTeamName()+"):  "+player.getGamesPlayed()
            + " GP, " + player.getGoalie().getWins() + "-" +player.getGoalie().getLosses() +", " + player.getGoalie().getGaa() +" GAA, "
              +player.getSavePct() + " SV PCT";

        else {
          statsBarText = name1 + " ("+game.getAwayTeamName()+"):  "+ player.getGa() + " GA, " 
            + player.getSaves() + " SAVES";
        }
      }
    }
  }

  if (penalty.isSelected()) {
    name2 = (String)penaltyNames.getSelectedItem();
    if (homeTeam.isSelected()) {
      name1=(String)playerSelectH.getSelectedItem();

      statsBarText = game.getHomeTeamName() +" PENALTY: " + name1 + " ("+name2 + ") " + timeThisHappened;
    }

    else {
      name1=(String)playerSelectA.getSelectedItem();
      statsBarText = game.getAwayTeamName() +" PENALTY: " + name1 + " ("+name2 + ") " + timeThisHappened;
    }
  }
}

void lowerThirdSetup() {
  if (homeTeam.isSelected()) {
    ltTeam = "HOME";
    playerForGraphic = homeTEAM.getPlayers().get(playerSelectH.getSelectedIndex());
  }
  else {
    ltTeam = "AWAY";
    playerForGraphic = awayTEAM.getPlayers().get(playerSelectA.getSelectedIndex());
  }
  if (season.isSelected())
    ltType = "SEASON SUMMARY";

  if (gameStats.isSelected()) {
    ltType = "GAME SUMMARY";
  }
}

void teamStatsSetup() {
  if (sogButton.isSelected()) {
    statsBarText = "SHOTS ON GOAL: " + game.getAwayTeamName() +": " + awayTEAM.getSogGame() + "   |   " + game.getHomeTeamName()+": " + homeTEAM.getSogGame();
  } 

  if (ppButton.isSelected()) {
    if (homeTeam.isSelected())
      statsBarText = game.getHomeTeamName() + " PP: " +homeTEAM.getTotals().getPpStats().getPpg() +"-"+homeTEAM.getTotals().getPpStats().getPpc()
        +", " + homeTEAM.getPpPercent() + "  |  " + game.getAwayTeamName() + " PK: " + 
          awayTEAM.getTotals().getPpStats().getPks()+"-"+awayTEAM.getTotals().getPpStats().getPkc()+", " + awayTEAM.getPkPercent();
    else statsBarText = game.getAwayTeamName() + " PP: " +awayTEAM.getTotals().getPpStats().getPpg() +"-"+awayTEAM.getTotals().getPpStats().getPpc()
      +", " + awayTEAM.getPpPercent() + "  |  " + game.getHomeTeamName() + " PK: " + 
        homeTEAM.getTotals().getPpStats().getPks()+"-"+homeTEAM.getTotals().getPpStats().getPkc()+", " + homeTEAM.getPkPercent();
  }
}

void deleteAwayClock(Clock c) {
  awayPPClock.remove(c);
}

void deleteHomeClock(Clock c) {
  homePPClock.remove(c);
}

void checkSponsor() {
  if (showSponsor)
    previousSponsor = true;
  else previousSponsor = false;
}

void syncClocks() {
  for (Clock c:awayPPClock) {
    c.setSecondDefinition(game.getClock().getSecondDefinition());
  }

  for (Clock c:homePPClock) {
    c.setSecondDefinition(game.getClock().getSecondDefinition());
  }
}

