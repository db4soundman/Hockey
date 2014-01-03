import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

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
import javax.swing.JOptionPane; 
import javax.swing.JOptionPane; 
import java.awt.Color; 
import javax.swing.JColorChooser; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class GraphicsProgramXML extends PApplet {

/*HOCKEY GRAPHICS PROGRAM FOR MIAMI ALL-ACCESS
 CREATED BY DOUG BLASE, ALMOST 2 WEEKS EXACTLY,
 DETERMINED FINISHED SAT 6/29/2013, 8:10PM.
 
 */

















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
int bg;
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

public void setup() {
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


public void draw() {
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

public void statsBarSetup() {
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

public void lowerThirdSetup() {
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

public void teamStatsSetup() {
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

public void deleteAwayClock(Clock c) {
  awayPPClock.remove(c);
}

public void deleteHomeClock(Clock c) {
  homePPClock.remove(c);
}

public void checkSponsor() {
  if (showSponsor)
    previousSponsor = true;
  else previousSponsor = false;
}

public void syncClocks() {
  for (Clock c:awayPPClock) {
    c.setSecondDefinition(game.getClock().getSecondDefinition());
  }

  for (Clock c:homePPClock) {
    c.setSecondDefinition(game.getClock().getSecondDefinition());
  }
}

private class ButtonResponder implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==nchc) {
      hideGraphics.doClick();
      showStandings = true;
    }

    if (e.getSource()==runClock)
      game.runClock();

    if (e.getSource()==resetClock)
      game.getClock().resetClock();

    if (e.getSource()==setClock)
      game.getClock().setClock();

    if (e.getSource()==clockSpeedUp) {
      game.getClock().speedUpClock();
      for (Clock c:awayPPClock) {
        c.setSecondDefinition(game.getClock().getSecondDefinition());
      }

      for (Clock c:homePPClock) {
        c.setSecondDefinition(game.getClock().getSecondDefinition());
      }
    }
    if (e.getSource()==clockSpeedDn) {
      game.getClock().slowDownClock();
      for (Clock c:awayPPClock) {
        c.setSecondDefinition(game.getClock().getSecondDefinition());
      }

      for (Clock c:homePPClock) {
        c.setSecondDefinition(game.getClock().getSecondDefinition());
      }
    }
    if (e.getSource()==nextPeriod) {
      game.setPeriod(game.getPeriod()+1);
      if (game.getPeriod()==5) {
        homePreSOscore = game.getHomeTeamScore();
        awayPreSOscore = game.getAwayTeamScore();
        game.setHomeTeamScore(0);
        game.setAwayTeamScore(0);
      }
    }

    if (e.getSource()==prevPeriod)
      game.setPeriod(game.getPeriod()-1);

    if (e.getSource()==clockButton) {
      intermission = false;
      showFinal = false;
    }

    if (e.getSource()==intButton) {
      intermission = true;
      showFinal = false;
    }

    if (e.getSource()==finalButton) {
      showFinal = true;
      intermission = false;
      if (game.getPeriod()==5) {
        if (game.getHomeTeamScore()>game.getAwayTeamScore()) {
          game.setHomeTeamScore(homePreSOscore+1);
          game.setAwayTeamScore(awayPreSOscore);
        }
        else {
          game.setHomeTeamScore(homePreSOscore);
          game.setAwayTeamScore(awayPreSOscore+1);
        }
        nextPeriod.doClick();
      }
    }

    if (e.getSource()==penaltyFlagButton) {
      penaltyFlag = !penaltyFlag;
    }

    if (e.getSource()==commercial) {
      showLowerThird = false;
      hideLT.setEnabled(false);
      showLT.setEnabled(true);
      showScoreboard = false;
      showCommercial= true;
      showStandings = false;
    }

    if (e.getSource()==showBoard) {
      showScoreboard = true;
      showCommercial = false;
    }

    if (e.getSource()==hideGraphics) {
      showScoreboard = false;
      showCommercial = false;
      showLowerThird = false;
      showStandings = false;
    }

    if (e.getSource()==sponsorButton) {
      showSponsor = !showSponsor;
      if (showSponsor) {
        sponsorButton.setText("Hide Sponsor");
        sponsorText = textField.getText();
      }
      else sponsorButton.setText("Show Sponsor");
    }

    if (e.getSource()==awayUp1) {
      game.setAwayTeamScore(game.getAwayTeamScore()+1);
      awayTEAM.setSogGame(awayTEAM.getSogGame()+1);
      if (!hGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = homeTEAM.getPlayers().get(hGoalie.getSelectedIndex());
        goalie.setGa(goalie.getGa()+1);
      }
      awayTeam.doClick();
      goal.doClick();
      if (game.getPeriod()<4) {
        int minutes = 19-game.getClock().getMinutes(); 
        int seconds = 60-game.getClock().getSeconds();
        if (game.getClock().getMinutes()==0 && seconds==60)
          minutes=20;
        if (seconds==60) {
          minutes++;
          seconds=0;
        }
        if (seconds>=10)
          timeThisHappened = minutes + ":" + seconds;
        else timeThisHappened = minutes + ":" +"0"+ seconds;
      }
      else {
        int minutes = 4-game.getClock().getMinutes(); 
        int seconds = 60-game.getClock().getSeconds();
        if (game.getClock().getMinutes()==0 && seconds==60)
          minutes=5;
        if (seconds==60) {
          minutes++;
          seconds=0;
        }
        if (seconds>=10)
          timeThisHappened = minutes + ":" + seconds;
        else timeThisHappened = minutes + ":" +"0"+ seconds;
      }
    }

    if (e.getSource()==awayDown1) {
      game.setAwayTeamScore(game.getAwayTeamScore()-1);
      awayTEAM.setSogGame(awayTEAM.getSogGame()-1);
      if (!hGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = homeTEAM.getPlayers().get(hGoalie.getSelectedIndex());
        goalie.setGa(goalie.getGa()-1);
      }
    }

    if (e.getSource()==homeUp1) {
      game.setHomeTeamScore(game.getHomeTeamScore()+1);
      homeTEAM.setSogGame(homeTEAM.getSogGame()+1);
      if (!aGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = awayTEAM.getPlayers().get(aGoalie.getSelectedIndex());
        goalie.setGa(goalie.getGa()+1);
      }
      homeTeam.doClick();
      goal.doClick();
      if (game.getPeriod()<4) {
        int minutes = 19-game.getClock().getMinutes(); 
        int seconds = 60-game.getClock().getSeconds();
        if (game.getClock().getMinutes()==0 && seconds==60)
          minutes=20;
        if (seconds==60) {
          minutes++;
          seconds=0;
        }
        if (seconds>=10)
          timeThisHappened = minutes + ":" + seconds;
        else timeThisHappened = minutes + ":" +"0"+ seconds;
      }
      else {
        int minutes = 4-game.getClock().getMinutes(); 
        int seconds = 60-game.getClock().getSeconds();
        if (game.getClock().getMinutes()==0 && seconds==60)
          minutes=5;
        if (seconds==60) {
          minutes++;
          seconds=0;
        }
        if (seconds>=10)
          timeThisHappened = minutes + ":" + seconds;
        else timeThisHappened = minutes + ":" +"0"+ seconds;
      }
    }

    if (e.getSource()==homeDown1) {
      game.setHomeTeamScore(game.getHomeTeamScore()-1);
      homeTEAM.setSogGame(homeTEAM.getSogGame()-1);
      if (!aGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = awayTEAM.getPlayers().get(aGoalie.getSelectedIndex());
        goalie.setGa(goalie.getGa()-1);
      }
    }

    if (e.getSource()==homeTeam) {
      playerSelectH.setEnabled(true);
      playerSelectA.setEnabled(false);
      if (goal.isSelected()) {
        assist1H.setEnabled(true);
        assist1A.setEnabled(false);
        assist2H.setEnabled(true);
        assist2A.setEnabled(false);
      }
      else {
        assist1H.setEnabled(false);
        assist1A.setEnabled(false);
        assist2H.setEnabled(false);
        assist2A.setEnabled(false);
      }
    }

    if (e.getSource()==awayTeam) {
      gameStats.doClick();
      playerSelectH.setEnabled(false);
      playerSelectA.setEnabled(true);
      if (goal.isSelected()) {
        assist1H.setEnabled(false);
        assist1A.setEnabled(true);
        assist2H.setEnabled(false);
        assist2A.setEnabled(true);
      }

      else {
        assist1H.setEnabled(false);
        assist1A.setEnabled(false);
        assist2H.setEnabled(false);
        assist2A.setEnabled(false);
      }
    }

    if (e.getSource()==penalty) {
      assist1H.setEnabled(false);
      assist1A.setEnabled(false);
      assist2H.setEnabled(false);
      assist2A.setEnabled(false);
      penaltyNames.setEnabled(true);
      if (game.getPeriod()<4) {
        int minutes = 19-game.getClock().getMinutes(); 
        int seconds = 60-game.getClock().getSeconds();
        if (game.getClock().getMinutes()==0 && seconds==60)
          minutes=20;
        if (seconds==60) {
          minutes++;
          seconds=0;
        }
        if (seconds>=10)
          timeThisHappened = minutes + ":" + seconds;
        else timeThisHappened = minutes + ":" +"0"+ seconds;
      }
      else {
        int minutes = 4-game.getClock().getMinutes(); 
        int seconds = 60-game.getClock().getSeconds();
        if (game.getClock().getMinutes()==0 && seconds==60)
          minutes=5;
        if (seconds==60) {
          minutes++;
          seconds=0;
        }
        if (seconds>=10)
          timeThisHappened = minutes + ":" + seconds;
        else timeThisHappened = minutes + ":" +"0"+ seconds;
      }
    }

    if (e.getSource()==goal) {
      if (homeTeam.isSelected()) {
        assist1H.setEnabled(true);
        assist2H.setEnabled(true);
      }
      else {
        assist1A.setEnabled(true);
        assist2A.setEnabled(true);
      }
      penaltyNames.setEnabled(false);
    }

    if (e.getSource()==summary) {
      assist1H.setEnabled(false);
      assist1A.setEnabled(false);
      assist2H.setEnabled(false);
      assist2A.setEnabled(false);
      penaltyNames.setEnabled(false);
    }

    if (e.getSource()==showTeamStat) {
      checkSponsor();
      showSponsor = false;
      teamStatsSetup();
      showStatsBar = true;
      hideTeamStat.setEnabled(true);
      showTeamStat.setEnabled(false);
      showSB.setEnabled(false);
    }

    if (e.getSource()==hideTeamStat) {
      showSponsor = previousSponsor;
      showStatsBar = false;
      hideTeamStat.setEnabled(false);
      showTeamStat.setEnabled(true);
      showSB.setEnabled(true);
    }

    if (e.getSource()==showSB) {
      checkSponsor();
      showSponsor = false;
      statsBarSetup();
      showStatsBar=true;
      showSB.setEnabled(false);
      hideSB.setEnabled(true);
      showTeamStat.setEnabled(false);
    }

    if (e.getSource()==hideSB) {
      showSponsor = previousSponsor;
      showStatsBar = false;
      hideSB.setEnabled(false);
      showSB.setEnabled(true);
      showTeamStat.setEnabled(true);
    }

    if (e.getSource()==showLT) {
      showCommercial = false;
      lowerThirdSetup();
      showLowerThird = true;

      showLT.setEnabled(false); 
      hideLT.setEnabled(true);
    }

    if (e.getSource()==hideLT) {
      showLowerThird = false;
      showLT.setEnabled(true);
      hideLT.setEnabled(false);
    }


    if (e.getSource()==ap2) {
      awayPPClock.add(new Clock(2, game.getClock()));
      Collections.sort(awayPPClock);
      syncClocks();
      penalty.doClick();
      awayTeam.doClick();
    }

    if (e.getSource()==ap4) {
      awayPPClock.add(new Clock(4, game.getClock()));
      Collections.sort(awayPPClock);
      syncClocks();
      penalty.doClick();
      awayTeam.doClick();
    }
    if (e.getSource()==ap5) {
      awayPPClock.add(new Clock(5, game.getClock()));
      Collections.sort(awayPPClock);
      syncClocks();
      penalty.doClick();
      awayTeam.doClick();
    }
    if (e.getSource()==hp2) {
      homePPClock.add(new Clock(2, game.getClock()));
      Collections.sort(homePPClock);
      syncClocks();
      penalty.doClick();
      homeTeam.doClick();
    }
    if (e.getSource()==hp4) {
      homePPClock.add(new Clock(4, game.getClock()));
      Collections.sort(homePPClock);
      syncClocks();
      penalty.doClick();
      homeTeam.doClick();
    }
    if (e.getSource()==hp5) {
      homePPClock.add(new Clock(5, game.getClock()));
      Collections.sort(homePPClock);
      syncClocks();
      penalty.doClick();
      homeTeam.doClick();
    }

    if (e.getSource()==hSog1) {
      homeTEAM.setSogGame(homeTEAM.getSogGame()+1);
      if (!aGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = awayTEAM.getPlayers().get(aGoalie.getSelectedIndex());
        goalie.setSaves(goalie.getSaves()+1);
      }
    }

    if (e.getSource()==aSog1) {
      awayTEAM.setSogGame(awayTEAM.getSogGame()+1);
      if (!hGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = homeTEAM.getPlayers().get(hGoalie.getSelectedIndex());
        goalie.setSaves(goalie.getSaves()+1);
      }
    }

    if (e.getSource()==setHsog) {
      homeTEAM.setSogGame(homeTEAM.getSogGame()-1);
      if (!aGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = awayTEAM.getPlayers().get(aGoalie.getSelectedIndex());
        goalie.setSaves(goalie.getSaves()-1);
      }
    }

    if (e.getSource()==setAsog) {
      awayTEAM.setSogGame(awayTEAM.getSogGame()-1);
      if (!hGoalie.getSelectedItem().equals("EMPTY")) {
        Player goalie = homeTEAM.getPlayers().get(hGoalie.getSelectedIndex());
        goalie.setSaves(goalie.getSaves()-1);
      }
    }

    if (e.getSource()==hPPg) {
      homeTEAM.getTotals().getPpStats().setPpg(homeTEAM.getTotals().getPpStats().getPpg()+1);
      homeTEAM.getTotals().getPpStats().setPpc(homeTEAM.getTotals().getPpStats().getPpc()+1);
      awayTEAM.getTotals().getPpStats().setPkc(awayTEAM.getTotals().getPpStats().getPkc()+1);
      homeTEAM.recalculatePowerPlayStats();
      awayTEAM.recalculatePowerPlayStats();
    }

    if (e.getSource()==aPPg) {
      awayTEAM.getTotals().getPpStats().setPpg(awayTEAM.getTotals().getPpStats().getPpg()+1);
      awayTEAM.getTotals().getPpStats().setPpc(awayTEAM.getTotals().getPpStats().getPpc()+1);
      homeTEAM.getTotals().getPpStats().setPkc(homeTEAM.getTotals().getPpStats().getPkc()+1);
      homeTEAM.recalculatePowerPlayStats();
      awayTEAM.recalculatePowerPlayStats();
    }

    if (e.getSource()==hPPf) {
      homeTEAM.getTotals().getPpStats().setPpc(homeTEAM.getTotals().getPpStats().getPpc()+1);
      awayTEAM.getTotals().getPpStats().setPks(awayTEAM.getTotals().getPpStats().getPks()+1);
      awayTEAM.getTotals().getPpStats().setPkc(awayTEAM.getTotals().getPpStats().getPkc()+1);
      homeTEAM.recalculatePowerPlayStats();
      awayTEAM.recalculatePowerPlayStats();
    }

    if (e.getSource()==aPPf) {
      awayTEAM.getTotals().getPpStats().setPpc(awayTEAM.getTotals().getPpStats().getPpc()+1);
      homeTEAM.getTotals().getPpStats().setPks(homeTEAM.getTotals().getPpStats().getPks()+1);
      homeTEAM.getTotals().getPpStats().setPkc(homeTEAM.getTotals().getPpStats().getPkc()+1);
      homeTEAM.recalculatePowerPlayStats();
      awayTEAM.recalculatePowerPlayStats();
    }

    if (e.getSource()==save) {
      if (homeTeam.isSelected())
        homeTEAM.save();

      else awayTEAM.save();
    }

    if (e.getSource()==submit) {
      if (homeTeam.isSelected()) {
        Player player = homeTEAM.getPlayers().get(playerSelectH.getSelectedIndex());
        if (goal.isSelected()) {
          if (assist1H.getSelectedItem().equals("UNASSISTED")) {
            player.setGoalsGame(player.getGoalsGame() +1);
            player.setPtsGame(player.getPtsGame() +1);
          }
          else if (!assist2H.getSelectedItem().equals("UNASSISTED")) {
            Player player2 = homeTEAM.getPlayers().get(assist1H.getSelectedIndex());
            Player player3 = homeTEAM.getPlayers().get(assist2H.getSelectedIndex());
            player.setGoalsGame(player.getGoalsGame() +1);
            player.setPtsGame(player.getPtsGame() +1);
            player2.setAssistsGame(player2.getAssistsGame() +1);
            player2.setPtsGame(player2.getPtsGame() + 1);
            player3.setAssistsGame(player3.getAssistsGame()+1);
            player3.setPtsGame(player3.getPtsGame()+1);
          }
          else {
            Player player2 = homeTEAM.getPlayers().get(assist1H.getSelectedIndex());
            player.setGoalsGame(player.getGoalsGame() +1);
            player.setPtsGame(player.getPtsGame() +1);
            player2.setAssistsGame(player2.getAssistsGame() +1);
            player2.setPtsGame(player2.getPtsGame() + 1);
          }
        }
        else if (penalty.isSelected()) {
          player.setPenaltiesGame(player.getPenaltiesGame() +1);
          String pim = JOptionPane.showInputDialog(null, "Length of Penalty:");
          int lengthOfPenalty = Integer.parseInt(pim);
          player.setPimGame(player.getPimGame() + lengthOfPenalty);
        }
      }

      if (awayTeam.isSelected()) {
        Player player = awayTEAM.getPlayers().get(playerSelectA.getSelectedIndex());
        if (goal.isSelected()) {
          if (assist1A.getSelectedItem().equals("UNASSISTED")) {
            player.setGoalsGame(player.getGoalsGame() +1);
            player.setPtsGame(player.getPtsGame() +1);
          }
          else if (!assist2A.getSelectedItem().equals("UNASSISTED")) {
            Player player2 = awayTEAM.getPlayers().get(assist1A.getSelectedIndex());
            Player player3 = awayTEAM.getPlayers().get(assist2A.getSelectedIndex());
            player.setGoalsGame(player.getGoalsGame() +1);
            player.setPtsGame(player.getPtsGame() +1);
            player2.setAssistsGame(player2.getAssistsGame() +1);
            player2.setPtsGame(player2.getPtsGame() + 1);
            player3.setAssistsGame(player3.getAssistsGame()+1);
            player3.setPtsGame(player3.getPtsGame()+1);
          }
          else {
            Player player2 = awayTEAM.getPlayers().get(assist1A.getSelectedIndex());
            player.setGoalsGame(player.getGoalsGame() +1);
            player.setPtsGame(player.getPtsGame() +1);
            player2.setAssistsGame(player2.getAssistsGame() +1);
            player2.setPtsGame(player2.getPtsGame() + 1);
          }
        }
        else if (penalty.isSelected()) {
          player.setPenaltiesGame(player.getPenaltiesGame() +1);
          String pim = JOptionPane.showInputDialog(null, "Length of Penalty:");
          int lengthOfPenalty = Integer.parseInt(pim);
          player.setPimGame(player.getPimGame() + lengthOfPenalty);
        }
      }
    }

    if (e.getSource()==showPPClock)
      showPP = !showPP;

    if (e.getSource()==deleteA) {
      int choice = -1;
      String userChoice = JOptionPane.showInputDialog(null, 
      "Choose visiting team penalty to delete (0 to cancel)");
      try {
        choice = Integer.parseInt(userChoice) - 1;
      }
      catch(Exception f) {
        userChoice = JOptionPane.showInputDialog(null, 
        "Choose visiting team penalty to delete");
      }

      if (choice<0)
        return;

      else {
        if (choice>=awayPPClock.size())
          JOptionPane.showMessageDialog(null, "Invalid penalty.", 
          "Message", 
          JOptionPane.INFORMATION_MESSAGE);

        else {
          awayPPClock.remove(choice);
          if (awayPPClock.size()>0)
            Collections.sort(awayPPClock);
        }
      }
    }

    if (e.getSource()==deleteH) {
      int choice=-1;
      String userChoice = JOptionPane.showInputDialog(null, 
      "Choose home team penalty to delete (0 to cancel)");
      try {
        choice = Integer.parseInt(userChoice) - 1;
      }
      catch(Exception f) {
        userChoice = JOptionPane.showInputDialog(null, 
        "Choose home team penalty to delete");
      }

      if (choice<0)
        return;

      else {
        if (choice>=homePPClock.size())
          JOptionPane.showMessageDialog(null, "Invalid penalty.", 
          "Message", 
          JOptionPane.INFORMATION_MESSAGE);

        else {
          homePPClock.remove(choice);
          if (homePPClock.size()>0)
            Collections.sort(awayPPClock);
        }
      }
    }
  }
}



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

public void makeClockControl() {
  clockControl = new JPanel();
  clockControl.setLayout(new BoxLayout(clockControl, 1));
  clockControl.setBounds(700, 200, 100, 320);
  clockControl.setAlignmentX(Component.RIGHT_ALIGNMENT);
  runClock=new JButton("Run Clock");
  runClock.setEnabled(true);
  runClock.addActionListener(new ButtonResponder());
  clockControl.add(runClock, 0, 0);
  resetClock = new JButton("Reset Clock");
  resetClock.addActionListener(new ButtonResponder());
  resetClock.setEnabled(true); 
  clockControl.add(resetClock);
  setClock = new JButton("Set Clock");
  setClock.setEnabled(true);
  setClock.addActionListener(new ButtonResponder());
  clockControl.add(setClock); 
  clockSpeedUp = new JButton("Spd Clock");
  clockSpeedUp.setEnabled(true); 
  clockSpeedUp.addActionListener(new ButtonResponder());
  clockControl.add(clockSpeedUp);
  clockSpeedDn = new JButton("SL Clock");
  clockSpeedDn.setEnabled(true); 
  clockSpeedDn.addActionListener(new ButtonResponder());
  clockControl.add(clockSpeedDn);
  nextPeriod = new JButton("Next period");
  nextPeriod.setEnabled(true); 
  nextPeriod.addActionListener(new ButtonResponder());
  prevPeriod = new JButton("Prev period");
  prevPeriod.setEnabled(true);
  prevPeriod.addActionListener(new ButtonResponder());

  clockControl.add(nextPeriod);
  clockControl.add(prevPeriod);
  clockControl.setVisible(true);

  clockButton = new JButton("Clock");
  clockButton.setEnabled(true); 
  clockButton.addActionListener(new ButtonResponder());
  clockControl.add(clockButton);

  intButton = new JButton("INT");
  intButton.setEnabled(true); 
  intButton.addActionListener(new ButtonResponder());
  clockControl.add(intButton);

  finalButton = new JButton("FINAL");
  finalButton.setEnabled(true); 
  finalButton.addActionListener(new ButtonResponder());
  clockControl.add(finalButton);

  penaltyFlagButton = new JButton("Penalty");
  penaltyFlagButton.addActionListener(new ButtonResponder());
  clockControl.add(penaltyFlagButton);

  controlPanel.add(clockControl);
  clockLabel = new JPanel();
  clockCommands = new JLabel("Clock Commands");
  clockLabel.setBounds(675, 180, 125, 20);
  clockLabel.add(clockCommands);
  clockLabel.setVisible(true);
  controlPanel.add(clockLabel);
}

public void displayControls() {
  displayControls = new JPanel();
  displayControls.setLayout(new BoxLayout(displayControls, 0));
  displayControls.setBounds(10, 40, 470, 50);
  nchc = new JButton("Standings");
  nchc.setEnabled(true);
  displayControls.add(nchc);
  nchc.addActionListener(new ButtonResponder());
  commercial = new JButton("Show comm.");
  commercial.setEnabled(true);
  commercial.addActionListener(new ButtonResponder()); 
  showBoard = new JButton("Show scoreboard");
  showBoard.setEnabled(true);
  showBoard.addActionListener(new ButtonResponder());
  hideGraphics = new JButton("Hide All");
  hideGraphics.setEnabled(true);
  hideGraphics.addActionListener(new ButtonResponder());
  displayControls.add(commercial);
  displayControls.add(showBoard);
  displayControls.add(hideGraphics);
  displayControls.setVisible(true);
  controlPanel.add(displayControls);

  JPanel sponsorPanel = new JPanel();
  sponsorPanel.setBounds(0, 10, 550, 30);
  sponsorPanel.setLayout(new BoxLayout(sponsorPanel, 0));
  textField = new JTextField("McCullough Hyde Memorial Hospital - IMG Sports Network");
  textField.setEditable(true);
  sponsorPanel.add(textField);
  sponsorButton = new JButton("Show Sponsor");
  sponsorButton.addActionListener(new ButtonResponder());
  sponsorPanel.add(sponsorButton);

  controlPanel.add(sponsorPanel);
}

public void makeStatusPanel() {
  status1 = new JPanel();
  status2 = new JPanel();
  status3 = new JPanel();
  status1.setBounds(500, 10, 300, 50);
  status2.setBounds(500, 60, 300, 50);
  status3.setBounds(700, 30, 100, 50);
  awayName = new JLabel(game.getAwayTeamName());
  homeName = new JLabel(game.getHomeTeamName());
  awayScore = new JLabel(""+game.getAwayTeamScore());
  homeScore = new JLabel(""+game.getHomeTeamScore());
  clock = new JLabel();
  status1.add(awayName);
  status2.add(homeName);
  status1.add(awayScore);
  status2.add(homeScore);
  status3.add(clock);
  controlPanel.add(status1);
  controlPanel.add(status2);
  controlPanel.add(status3);
}

public void makeScoreControl() {
  JLabel title = new JLabel("Score Controls");
  JPanel scoreTitle = new JPanel();
  scoreTitle.setBounds(550, 180, 100, 20);
  scoreTitle.add(title);
  controlPanel.add(scoreTitle);
  scoreControl = new JPanel();
  scoreControl.setBounds(520, 200, 150, 70);

  scoreControl.setLayout(new GridLayout(2, 2));
  awayUp1 = new JButton("Away +");
  awayUp1.setEnabled(true);
  awayDown1 = new JButton("Away -");
  awayDown1.setEnabled(true);
  homeUp1 = new JButton("Home +");
  homeUp1.setEnabled(true);
  homeDown1 = new JButton("Home -");
  homeDown1.setEnabled(true);
  awayUp1.addActionListener(new ButtonResponder());
  awayDown1.addActionListener(new ButtonResponder());
  homeUp1.addActionListener(new ButtonResponder());
  homeDown1.addActionListener(new ButtonResponder());

  scoreControl.add(awayUp1);
  scoreControl.add(homeUp1);
  scoreControl.add(awayDown1);
  scoreControl.add(homeDown1);
  controlPanel.add(scoreControl);


  JPanel sogControl = new JPanel();
  sogControl.setBounds(300, 200, 200, 50);
  sogControl.setLayout(new GridLayout(2, 2));
  aSog1 = new JButton("SOG +1");
  aSog1.addActionListener(new ButtonResponder());
  sogControl.add(aSog1);
  hSog1 = new JButton("SOG +1");
  hSog1.addActionListener(new ButtonResponder());
  sogControl.add(hSog1);
  setAsog = new JButton("SOG -1");
  setAsog.addActionListener(new ButtonResponder());
  sogControl.add(setAsog);
  setHsog = new JButton("SOG -1");
  setHsog.addActionListener(new ButtonResponder());
  sogControl.add(setHsog);

  controlPanel.add(sogControl);

  JPanel asoglabel = new JPanel();
  asoglabel.setBounds(300, 180, 90, 20);
  awayShotsOnGoal = new JLabel();
  asoglabel.add(awayShotsOnGoal);

  JPanel hsoglabel = new JPanel();
  hsoglabel.setBounds(410, 180, 90, 29);
  homeShotsOnGoal = new JLabel();
  hsoglabel.add(homeShotsOnGoal);
  controlPanel.add(asoglabel);
  controlPanel.add(hsoglabel);
}


public void makeGraphicsControl() {
  JPanel graphicsSector = new JPanel();
  graphicsSector.setLayout(null);
  graphicsSector.setBounds(10, 100, 600, 600);


  homeTeam = new JRadioButton();
  homeTeam.setEnabled(true);
  homeTeam.addActionListener(new ButtonResponder());
  awayTeam = new JRadioButton();
  awayTeam.setEnabled(true);
  awayTeam.addActionListener(new ButtonResponder());
  sogButton = new JRadioButton("SOG");
  sogButton.addActionListener(new ButtonResponder());
  sogButton.setEnabled(true);
  ppButton = new JRadioButton("PP");
  ppButton.setEnabled(true);
  ppButton.addActionListener(new ButtonResponder());
  gameStats = new JRadioButton("Game");
  gameStats.setEnabled(true);
  gameStats.addActionListener(new ButtonResponder());
  season  = new JRadioButton("Season");
  season.setEnabled(true);
  season.addActionListener(new ButtonResponder());
  summary = new JRadioButton("Summary");
  summary.setEnabled(true);
  summary.addActionListener(new ButtonResponder());
  goal = new JRadioButton("Goal");
  goal.setEnabled(true);
  goal.addActionListener(new ButtonResponder());
  penalty = new JRadioButton("Penalty");
  penalty.setEnabled(true);
  penalty.addActionListener(new ButtonResponder());

  ButtonGroup teams = new ButtonGroup();
  teams.add(homeTeam);
  teams.add(awayTeam);
  JPanel teamButtons = new JPanel();
  teamButtons.setLayout(new BoxLayout(teamButtons, 1));
  teamButtons.add(awayTeam);
  teamButtons.add(homeTeam);
  teamButtons.setBounds(0, 110, 200, 50);
  graphicsSector.add(teamButtons);

  ButtonGroup statType = new ButtonGroup();
  statType.add(ppButton);
  statType.add(sogButton);
  JPanel locationButtons = new JPanel();
  locationButtons.setLayout(new BoxLayout(locationButtons, 1));
  locationButtons.add(ppButton);
  locationButtons.add(sogButton);
  locationButtons.setBounds(0, 180, 100, 50);
  graphicsSector.add(locationButtons);

  ButtonGroup data = new ButtonGroup();
  data.add(gameStats);
  data.add(season);
  JPanel dataButtons = new JPanel();
  dataButtons.setLayout(new BoxLayout(dataButtons, 1));
  dataButtons.add(gameStats);
  dataButtons.add(season);
  dataButtons.setBounds(175, 110, 100, 50);
  graphicsSector.add(dataButtons);

  ButtonGroup gameData = new ButtonGroup();
  gameData.add(summary);
  gameData.add(goal);
  gameData.add(penalty);
  JPanel gameButtons = new JPanel();
  gameButtons.setLayout(new BoxLayout(gameButtons, 1));
  gameButtons.add(summary);
  gameButtons.add(goal);
  gameButtons.add(penalty);
  gameButtons.setBounds(175, 180, 100, 100);
  graphicsSector.add(gameButtons);


  //--------NAME SELECTORS-------//
  ArrayList<String> homeNames = new ArrayList<String>();
  ArrayList<String> awayNames = new ArrayList<String>();
  for (Player p:homeTEAM.getPlayers()) {
    homeNames.add(p.getName());
  }
  for (Player p:awayTEAM.getPlayers()) {
    awayNames.add(p.getName());
  }

  JPanel nameSelectors = new JPanel();
  nameSelectors.setLayout(new GridLayout(3, 3));
  nameSelectors.setBounds(10, 400, 500, 150);
  playerSelectH = new JComboBox(homeNames.toArray());
  playerSelectH.setEnabled(false);
  playerSelectA = new JComboBox(awayNames.toArray());
  playerSelectA.setEnabled(false);
  awayNames.add("UNASSISTED");
  homeNames.add("UNASSISTED");
  assist1A= new JComboBox(awayNames.toArray());
  assist2A= new JComboBox(awayNames.toArray());
  assist1H= new JComboBox(homeNames.toArray());
  assist2H= new JComboBox(homeNames.toArray());
  assist1A.setEnabled(false);
  assist2A.setEnabled(false);
  assist1H.setEnabled(false);
  assist2H.setEnabled(false);
  nameSelectors.add(playerSelectA);
  nameSelectors.add(assist1A);
  nameSelectors.add(assist2A);
  nameSelectors.add(playerSelectH);
  nameSelectors.add(assist1H);
  nameSelectors.add(assist2H);
  penaltyNames = new JComboBox(penalties.toArray());
  penaltyNames.setEnabled(false);
  nameSelectors.add(penaltyNames);
  awayNames.set(awayNames.size()-1, "EMPTY");
  homeNames.set(homeNames.size()-1, "EMPTY");
  aGoalie = new JComboBox(awayNames.toArray());
  hGoalie = new JComboBox(homeNames.toArray());
  nameSelectors.add(aGoalie);
  nameSelectors.add(hGoalie);
  graphicsSector.add(nameSelectors);


  JPanel graphicsButtons = new JPanel();
  graphicsButtons.setBounds(2, 260, 290, 80);
  graphicsButtons.setLayout(new GridLayout(3, 2));
  showSB = new JButton("Show SB");
  hideSB = new JButton("Hide SB");
  showTeamStat = new JButton("Show Team");
  hideTeamStat = new JButton("Hide Team");
  showLT = new JButton("Show LT");
  hideLT = new JButton("Hide LT");
  submit = new JButton("Submit");
  save = new JButton("Save");
  graphicsButtons.add(showSB);
  graphicsButtons.add(showLT);
  graphicsButtons.add(showTeamStat);
  graphicsButtons.add(hideSB);
  graphicsButtons.add(hideLT);
  graphicsButtons.add(hideTeamStat);
  graphicsButtons.add(submit);
  graphicsButtons.add(save);
  hideSB.setEnabled(false);
  hideLT.setEnabled(false);

  showSB.addActionListener(new ButtonResponder());
  hideSB.addActionListener(new ButtonResponder());
  showLT.addActionListener(new ButtonResponder());
  hideLT.addActionListener(new ButtonResponder());
  showTeamStat.addActionListener(new ButtonResponder());
  hideTeamStat.addActionListener(new ButtonResponder());
  submit.addActionListener(new ButtonResponder());
  save.addActionListener(new ButtonResponder());

  graphicsSector.add(graphicsButtons);

  controlPanel.add(graphicsSector);
}

public void makePenaltyControl() {
  JPanel penalties = new JPanel();
  penalties.setBounds(550, 300, 100, 90);
  penalties.setLayout(new GridLayout(5, 2));
  ap2 = new JButton("+2");
  ap4 = new JButton("+4");
  ap5 = new JButton("+5");
  hp2 = new JButton("+2");
  hp4 = new JButton("+4");
  hp5 = new JButton("+5");
  deleteA = new JButton("X");
  deleteH = new JButton("X");
  showPPClock = new JButton ("Show");
  ap2.addActionListener(new ButtonResponder());
  ap4.addActionListener(new ButtonResponder());
  ap5.addActionListener(new ButtonResponder());
  hp2.addActionListener(new ButtonResponder());
  hp4.addActionListener(new ButtonResponder());
  hp5.addActionListener(new ButtonResponder());
  showPPClock.addActionListener(new ButtonResponder());
  deleteA.addActionListener(new ButtonResponder());
  deleteH.addActionListener(new ButtonResponder());

  penalties.add(ap2);
  penalties.add(hp2);
  penalties.add(ap4);
  penalties.add(hp4);
  penalties.add(ap5);
  penalties.add(hp5);
  penalties.add(deleteA);
  penalties.add(deleteH);
  penalties.add(showPPClock);
  controlPanel.add(penalties);

  JPanel ppTracker = new JPanel();
  ppTracker.setBounds(320, 300, 180, 60);
  ppTracker.setLayout(new GridLayout(2, 2));
  aPPg = new JButton("A. PP G");
  hPPg = new JButton("H. PP G");
  aPPf = new JButton("A. PP F");
  hPPf = new JButton("H. PP F");

  aPPg.addActionListener(new ButtonResponder());
  hPPg.addActionListener(new ButtonResponder());
  aPPf.addActionListener(new ButtonResponder());
  hPPf.addActionListener(new ButtonResponder());

  ppTracker.add(aPPg);
  ppTracker.add(hPPg);
  ppTracker.add(aPPf);
  ppTracker.add(hPPf);

  controlPanel.add(ppTracker);
}

public void makeStandings() {
  standingsUI = new JFrame("Standings - W-L-T-SOW");
  standingsUI.setBounds(0, 0, 400, 400);
  for (int i=0; i<teams.length;i++) {
    teamNames[i] = new JComboBox(teams);
  }

  for (int i=0; i<teams.length;i++) {
    for (int j=0; j < 4 ; j++) {
      recordNums[i][j] = new JTextField();
      recordNums[i][j].setText("0");
    }
  }

  standingsUI.setLayout(new GridLayout(teams.length, 4));
  for (int i=0;i<teams.length;i++) {
    standingsUI.add(teamNames[i]);
    for (int j=0;j<4;j++) {
      standingsUI.add(recordNums[i][j]);
    }
  }
}



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




/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Scoreboard {
  private Color homeTeamColor;
  private Color awayTeamColor;
  private int black;
  private int yellow;
  private int darkerYellow;
  private int x;
  private int y;
  private int cX;
  private int cY;
  private int ltX;
  private int sX;
  private int sY;
  private int awayColor;
  private int homeColor;

  public Scoreboard(String awayTeam, String homeTeam) {
    this.x = width/2 -(560);//tweak
    this.y = 90;//tweak
    this.cX = width/2 - 480;
    this.cY = height - 200;
    this.ltX = width/2 - 400;
    this.sX = width/2 - 400;
    this.sY = 50;
    black = color(0, 0, 0);
    yellow = color(255, 255, 0);
    darkerYellow = color(188, 188, 0);

    awayTeamColor = JColorChooser.showDialog(null, awayTeam, Color.white);
    awayColor = color(awayTeamColor.getRed(), awayTeamColor.getGreen(), awayTeamColor.getBlue());
    homeTeamColor = JColorChooser.showDialog(null, homeTeam, Color.red);
    homeColor = color(homeTeamColor.getRed(), homeTeamColor.getGreen(), homeTeamColor.getBlue());
  }

  public void displayScoreboard(String awayTeamName, int awayTeamScore, String homeTeamName, int homeTeamScore, int period, String clock) {
    String periodStr = "";
    if (period==1)
      periodStr = "1st";
    else if (period==2)
      periodStr = "2nd";
    else if (period==3)
      periodStr = "3rd";
    else if (period==5)
      clock = "SHOOTOUT";
    else periodStr = "OT";
    image(sb, x, y, 1112, 54);//tweak
    image(maa, x+34, y+4, 73, 46);//tweak
    textFont(myFont);      
    //--I was getting a random undrawn line due to the math, so that's
    //why the for loop is odd. Just accept it.
    for (float i = 0; i<254; i +=6.4f) {//tweak
      stroke(lerpColor(awayColor, black, i/255));
      line(x+116, round(y+7+(i/6.4f)), x+378, round(y+7+(i/6.4f)));//tweak 
      stroke(lerpColor(homeColor, black, i/255));
      line(x+474, round(y+7+(i/6.4f)), x+734, round(y+7+(i/6.4f)));//tweak
    }
    noStroke();
    fill(255);//tweak
    textAlign(LEFT);
    text(awayTeamName, x+118, y+43);//tweak
    text(homeTeamName, x+477, y+43);//tweak
    textAlign(CENTER);
    textFont(myFont);
    text(awayTeamScore, x+419, y+43);//tweak
    text(homeTeamScore, x+775, y+43);//tweak
    fill(0);
    if (!clock.equals("FINAL")&&!clock.equals("SHOOTOUT")) {
      text(periodStr, x+880, y+43);//tweak
      text(clock, x+1027, y+43);//tweak
    }
    else {
      text(clock, x+968, y+43);//tweak
    }
  }

  public void displayCommercial(String awayTeamName, int awayTeamScore, String homeTeamName, int homeTeamScore, int period, String clock) {
    String periodStr = "";
    if (period==1)
      periodStr = "1st";
    else if (period==2)
      periodStr = "2nd";
    else if (period==3)
      periodStr = "3rd";
    else periodStr = "OT";

    //--I was getting a random undrawn line due to the math, so that's
    //why the for loop is odd. Just accept it.
    textFont(myFont);
    image(statBar, cX+160, cY-47, 642, 56);
    text("Miami All-Access", cX+480, cY-6);
    //image(maa, cX+80, cY-24);
    for (float i = 0; i<254; i +=3.82f) {

      stroke(lerpColor(awayColor, black, i/255));
      line(cX+160, round(cY+9+(i/3.82f)), cX+801, round(cY+9+(i/3.82f)));//tweak 
      stroke(lerpColor(homeColor, black, i/255));
      line(cX+160, round(cY+76+(i/3.82f)), cX+801, round(cY+76+(i/3.82f)));//tweak
    }
    textFont(font24);
    noStroke();
    fill(255);
    textAlign(LEFT);
    text(awayTeamName, cX+164, cY+60);//tweak
    text(homeTeamName, cX+164, cY+120);//tweak
    textAlign(CENTER);
    text(awayTeamScore, cX+690, cY+60);//tweak
    text(homeTeamScore, cX+690, cY+120);//tweak
    fill(255);
    textFont(myFont);
    image(ppBar, cX+502, cY+140, 300, 48);
    if (!clock.equals("FINAL")) {
      text(periodStr, cX+590, cY+176);
      text(clock, cX+700, cY+176);
    }
    else {
      text(clock, cX+650, cY+176);
    }
  }

  public void showPenalty() {
    for (float i = 0; i<254; i +=6.4f) {//tweak
      stroke(lerpColor(yellow, darkerYellow, i/255));
      line(x+839, round(y+50+(i/6.4f)), x+1088, round(y+50+(i/6.4f)));//tweak
    }
    //image(ppBar, x+494, y+31, 148, 24);
    noStroke();
    fill(0);
    textAlign(CENTER, CENTER);
    textFont(font16);
    text("PENALTY", x+967, y+72);//tweak
  }

  public void showPPBarHome(String ppType, Clock c) {
    image(ppBar, x+472, y+52, 345, 38);//tweak
    textFont(font16);
    fill(255);
    textAlign(LEFT, CENTER);
    text(ppType, x+480, y+70);//tweak
    text(c.toStringPP(), x+746, y+70);//tweak
  }

  public void showPPBarAway(String ppType, Clock c) {
    image(ppBar, x+113, y+52, 345, 38);//tweak
    textFont(font16);
    fill(255);
    textAlign(LEFT, CENTER);
    text(ppType, x+122, y+70);//tweak
    text(c.toStringPP(), x+383, y+70);//tweak
  }

  public void showPPBarNeutral(String ppType, Clock c) {
    image(ppBar, x+837, y+54, 257, 38);//tweak
    textFont(font16);
    fill(255);
    textAlign(LEFT, CENTER);
    text(ppType, x+850, y+72);//tweak
    text(c.toStringPP(), x+1018, y+72);//tweak
  }

  public void showSponsorInfo(String sponsorInfo) {
    image(statBar, x, y-48, 732, 49);//tweak
    textAlign(LEFT, CENTER);
    fill(255);
    textFont(font16);
    if (textWidth(sponsorInfo)>=732)//tweak
      textFont(font14);
    if (textWidth(sponsorInfo)>=732)//tweak
      textFont(font12);
    if (textWidth(sponsorInfo)>=732)//tweak
      textFont(font10);

    text(sponsorInfo, x+15, y-22);
  }

  public void showStatsBar(String stat) {
    image(statBar, x+112, y-48, 732, 49);//tweak
    fill(255);
    textFont(font16);
    if (textWidth(stat)>=732)//tweak
      textFont(font14);
    if (textWidth(stat)>=732)//tweak
      textFont(font12);
    if (textWidth(stat)>=732)//tweak
      textFont(font10);

    textAlign(CENTER, CENTER);
    text(stat, x+56+422, y-22);//tweak
  }

  public void showLowerThird(Player player, String team, String type) {
    image(sg, ltX, cY+58, 800, 124);
    for (float i = 0; i<=255; i +=3.82f) {
      if (team.equals("HOME"))
        stroke(lerpColor(homeColor, black, i/255));
      else stroke(lerpColor(awayColor, black, i/255));
      line(ltX, cY+(i/3.82f), ltX+800, cY+(i/3.82f));
    }
    //image(maa, ltX, cY);
    fill(255);
    textAlign(CENTER, CENTER);
    textFont(font24);

    //text(player.getNumber(), ltX+70, cY+17);
    text(player.getName(), ltX+400, cY+34);
    text(player.getNumber(), ltX+760, cY+34);



    fill(0);
    textFont(font18);
    if (type.equals("SEASON SUMMARY")) {
      if (!player.getPosition().equals("G")) {
        text("GP", ltX+120, cY+90);
        text("G", ltX+240, cY+90);
        text("A", ltX+360, cY+90);
        text("PTS", ltX+480, cY+90);
        text("P", ltX+600, cY+90);
        text("PIM", ltX+720, cY+90);

        fill(255);
        textFont(myFont);
        text(player.getGamesPlayed(), ltX+120, cY+146);
        text(player.getShots().getGoals(), ltX+240, cY+146);
        text(player.getShots().getAssists(), ltX+360, cY+146);
        text(player.getShots().getPts(), ltX+480, cY+146);
        text(player.getPenalty().getPenalties(), ltX+600, cY+146);
        text(player.getPenalty().getPim(), ltX+720, cY+146);
      }

      else {
        text("GP", ltX+60, cY+90);
        text("W-L", ltX+180, cY+90);
        text("SV", ltX+320, cY+90);
        text("SV PCT", ltX+470, cY+90);
        text("GA", ltX+620, cY+90);
        text("GAA", ltX+740, cY+90);

        fill(255);
        textFont(myFont);
        text(player.getGamesPlayed(), ltX+60, cY+146);
        text(player.getGoalie().getWins() +"-"+player.getGoalie().getLosses(), ltX+180, cY+146);
        text(player.getGoalie().getSaves(), ltX+320, cY+146);
        text(player.getSavePct(), ltX+470, cY+146);
        text(player.getGoalie().getGa(), ltX+620, cY+146);
        text(player.getGoalie().getGaa(), ltX+740, cY+146);
      }
    }

    if (type.equals("GAME SUMMARY")) {
      if (!player.getPosition().equals("G")) {

        text("G", ltX+180, cY+90);
        text("A", ltX+300, cY+90);
        text("PTS", ltX+420, cY+90);
        text("P", ltX+540, cY+90);
        text("PIM", ltX+660, cY+90);

        fill(255);
        textFont(myFont);

        text(player.getGoalsGame(), ltX+180, cY+146);
        text(player.getAssistsGame(), ltX+300, cY+146);
        text(player.getPtsGame(), ltX+420, cY+146);
        text(player.getPenaltiesGame(), ltX+540, cY+146);
        text(player.getPimGame(), ltX+660, cY+146);
      }

      else {
        text("GA", ltX+200, cY+90);
        text("SV", ltX+400, cY+90);
        text("SHOTS", ltX+600, cY+90);


        fill(255);
        textFont(myFont);
        text(player.getGa(), ltX+200, cY+146);
        text(player.getSaves(), ltX+400, cY+146);
        text(player.getGa() + player.getSaves(), ltX+600, cY+146);
      }
    }
  }

  public void showStandingsGraphic() {
    image(standingsGraphic, sX, sY, 800, 980);
    textFont(myFont);
    fill(255);
    textAlign(CENTER);
    text("NCHC STANDINGS", sX+400, sY+40);//tweak
    text("W", sX+550, sY+110);//tweak
    text("L", sX+615, sY+110);//tweak
    text("T", sX+680, sY+110);//tweak
    text("PTS", sX+755, sY+110);//tweak
    for (int i=0;i<teamNames.length;i++) {
      textAlign(LEFT);
      text((String)teamNames[i].getSelectedItem(), sX+21, sY+140+75*(i+1));//tweak
      textAlign(CENTER);
      for (int j=0; j<3; j++)
        text(recordNums[i][j].getText(), sX+ 550+(65*j), sY+140+75*(i+1));//tweak
      text(Integer.parseInt(recordNums[i][0].getText())*3 + Integer.parseInt(recordNums[i][2].getText()) + Integer.parseInt(recordNums[i][3].getText()), 
      sX+755, sY+140+75*(i+1));//tweak
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "GraphicsProgramXML" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
