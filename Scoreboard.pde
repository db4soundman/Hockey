import java.awt.Color;
import javax.swing.JColorChooser;

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
  private color black;
  private color yellow;
  private color darkerYellow;
  private int x;
  private int y;
  private int cX;
  private int cY;
  private int ltX;
  private int ltY;
  private int sX;
  private int sY;
  private final int STAT_LABEL_Y;
  private final int STAT_Y;
  private final int SCORE_BOARD_TEXT_Y;
  private final int STAT_BAR_LENGTH;
  private color awayColor;
  private color homeColor;
  private int upperFlareX;
  private int lowerFlareX;
  private float flareDisplacement;
  private PImage flare;

  public Scoreboard(String awayTeam, String homeTeam) {
    this.x = width/2 -(560);//tweak
    this.y = 90;//tweak
    this.cX = width/2 - 480;
    this.cY = height - 200;
    this.ltX = width/2 - 214;
    this.ltY = cY;
    this.sX = width/2 - 400;
    this.sY = 50;
    this.STAT_Y = ltY + 87;
    this.STAT_LABEL_Y = ltY+ 27;
    this.SCORE_BOARD_TEXT_Y = y + 43;
    this.STAT_BAR_LENGTH = 1112 - 10;// - 10 for buffer
    this.upperFlareX = ltX-20;
    this.lowerFlareX = ltX+400;
    this.flareDisplacement = 0;
    black = color(0, 0, 0);
    yellow = color(255, 255, 0);
    darkerYellow = color(188, 188, 0);
    flare = loadImage("LensFlare.png");

    awayTeamColor = JColorChooser.showDialog(null, awayTeam, Color.white);
    awayColor = color(awayTeamColor.getRed(), awayTeamColor.getGreen(), awayTeamColor.getBlue());
    homeTeamColor = JColorChooser.showDialog(null, homeTeam, new Color(192, 0, 29));
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
    image(sb, x, y, STAT_BAR_LENGTH, 54);//tweak
    image(maa, x+34, y+4, 73, 46);//tweak
    textFont(myFont);      
    //--I was getting a random undrawn line due to the math, so that's
    //why the for loop is odd. Just accept it.
    for (float i = 0; i<254; i +=6.4) {//tweak
      stroke(lerpColor(awayColor, black, i/355));
      line(x+116, round(y+7+(i/6.4)), x+378, round(y+7+(i/6.4)));//tweak 
      stroke(lerpColor(homeColor, black, i/355));
      line(x+469, round(y+7+(i/6.4)), x+734, round(y+7+(i/6.4)));//tweak
    }
    noStroke();
    fill(255);//tweak
    textAlign(LEFT);
    if (textWidth(awayTeamName)>262)
      textFont(font18);
    text(awayTeamName, x+118, SCORE_BOARD_TEXT_Y);//tweak
    textFont(myFont);
    text(homeTeamName, x+477, SCORE_BOARD_TEXT_Y);//tweak
    textAlign(CENTER);
    textFont(myFont);
    text(awayTeamScore, x+416, SCORE_BOARD_TEXT_Y);//tweak
    text(homeTeamScore, x+772, SCORE_BOARD_TEXT_Y);//tweak
    fill(0);
    if (!clock.equals("FINAL")&&!clock.equals("SHOOTOUT")) {
      text(periodStr, x+880, SCORE_BOARD_TEXT_Y);//tweak
      text(clock, x+1021, SCORE_BOARD_TEXT_Y);//tweak
    }
    else {
      text(clock, x+968, SCORE_BOARD_TEXT_Y);//tweak
    }
  }

  void displayCommercial(String awayTeamName, int awayTeamScore, String homeTeamName, int homeTeamScore, int period, String clock) {
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
    for (float i = 0; i<254; i +=3.82) {

      stroke(lerpColor(awayColor, black, i/355));
      line(cX+160, round(cY+9+(i/3.82)), cX+801, round(cY+9+(i/3.82)));//tweak 
      stroke(lerpColor(homeColor, black, i/355));
      line(cX+160, round(cY+76+(i/3.82)), cX+801, round(cY+76+(i/3.82)));//tweak
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
    for (float i = 0; i<254; i +=6.4) {//tweak
      stroke(lerpColor(yellow, darkerYellow, i/355));
      line(x+839, round(y+50+(i/6.4)), x+1088, round(y+50+(i/6.4)));//tweak
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
    image(statBar, x, y-48, STAT_BAR_LENGTH, 49);//tweak
    textAlign(CENTER, CENTER);
    fill(255);
    textFont(font14);
    if (textWidth(sponsorInfo)>=STAT_BAR_LENGTH)//tweak
      textFont(font12);
    if (textWidth(sponsorInfo)>=STAT_BAR_LENGTH)//tweak
      textFont(font10);

    text(sponsorInfo, x+STAT_BAR_LENGTH/2, y-22);//tweak
  }

  public void showStatsBar(String stat) {
    image(statBar, x+0, y-48, STAT_BAR_LENGTH, 49);//tweak
    fill(255);
    textFont(font16);
    if (textWidth(stat)>=STAT_BAR_LENGTH)//tweak
      textFont(font14);
    if (textWidth(stat)>=STAT_BAR_LENGTH)//tweak
      textFont(font12);
    if (textWidth(stat)>=STAT_BAR_LENGTH)//tweak
      textFont(font10);

    textAlign(CENTER, CENTER);
    text(stat, x+STAT_BAR_LENGTH/2, y-22);//tweak
  }

  public void showLowerThird(Player player, String team, String type) {
    image(sg, ltX, cY+1, 800, 121);//tweak
    tint(23, 103);//tweak
    image(flare, (upperFlareX - 383) + (++flareDisplacement) % 1160, ltY+-27, 231, 57);//tweak
    noTint();
    fill(bg);
    noStroke();
    //rect(ltX+800, ltY-14, 161, 135);//tweak
    rect(ltX - 368, ltY-40, 1162, 39);//tweak



    float iOffset = 5.4;//tweak
    for (float i = 0; i <= 255; i += iOffset) {
      stroke(lerpColor( (team.equals("HOME")) ? homeColor : awayColor, 
      black, i/355), 0);//tweak
      line(ltX+2, ltY + (i/iOffset), ltX+800, ltY + (i/iOffset));
    }
    int whiteOffset = 48;//tweak
    iOffset = 3.4;//tweak
    for (float i = 0; i <= 255; i += iOffset) {
      stroke(lerpColor( (team.equals("HOME")) ? homeColor : awayColor, 
      black, i/355), 255);//tweak
      line(ltX+2, ltY + whiteOffset + (i/iOffset), ltX+800, ltY + whiteOffset + (i/iOffset));
    }

    noStroke();
    fill(0, 91);//tweak
    rect(ltX + 2, ltY + whiteOffset, 799, 75);//tweak


    //tint( (team.equals("HOME")) ? homeColor : awayColor, 255);//tweak
    tint(255, 198);//tweak
    image(flare, (lowerFlareX+350) - (flareDisplacement % 1000), ltY+93, 231, 40);//tweak
    noTint();
    
    fill(bg);
    noStroke();
    rect(ltX+800, ltY-14, 161, 135);//tweak
    //name rectange
    for (float i = 0; i<=255; i +=2.08) {//tweak
      stroke(lerpColor( (team.equals("HOME")) ? homeColor : awayColor, 
      black, i/355));//tweak
      line(ltX-372, ltY+(i/2.08), ltX+2, ltY+(i/2.08));//tweak
    }

    //image(maa, ltX, cY);
    fill(255);
    textAlign(CENTER, CENTER);
    textFont(myFont);
    String name = player.getName();
    if (textWidth(name)>=372)//tweak
      textFont(font18);
    if (textWidth(name)>=372)//tweak
      textFont(font16);
    if (textWidth(name)>=372)//tweak
      textFont(font14);
    text(name, ltX + -184, cY + 28);//tweak
    text(player.getNumber(), ltX + -184, cY + 80);



    fill(0);
    textFont(font18);
    if (type.equals("SEASON SUMMARY")) {
      if (!player.getPosition().equals("G")) {

        text("GP", ltX + 111, STAT_LABEL_Y);//tweak
        text("G", ltX + 211, STAT_LABEL_Y);//tweak
        text("A", ltX + 318, STAT_LABEL_Y);//tweak
        text("PTS", ltX + 429, STAT_LABEL_Y);//tweak
        text("P", ltX + 547, STAT_LABEL_Y);//tweak
        text("PIM", ltX + 660, STAT_LABEL_Y);//tweak

        fill(255);
        textFont(myFont);
        text(player.getGamesPlayed(), ltX+111, STAT_Y);//tweak
        text(player.getShots().getGoals(), ltX+211, STAT_Y);//tweak
        text(player.getShots().getAssists(), ltX+318, STAT_Y);//tweak
        text(player.getShots().getPts(), ltX+429, STAT_Y);//tweak
        text(player.getPenalty().getPenalties(), ltX+547, STAT_Y);//tweak
        text(player.getPenalty().getPim(), ltX+660, STAT_Y);//tweak
      }

      else {
        text("GP", ltX+64, STAT_LABEL_Y);//tweak
        text("W-L", ltX+174, STAT_LABEL_Y);//tweak
        text("SV", ltX+284, STAT_LABEL_Y);//tweak
        text("SV PCT", ltX+430, STAT_LABEL_Y);//tweak
        text("GA", ltX+584, STAT_LABEL_Y);//tweak
        text("GAA", ltX+724, STAT_LABEL_Y);//tweak

        fill(255);
        textFont(myFont);
        text(player.getGamesPlayed(), ltX+64, STAT_Y);//tweak
        text(player.getGoalie().getWins() +"-"+player.getGoalie().getLosses(), ltX+174, STAT_Y);//tweak
        text(player.getGoalie().getSaves(), ltX+284, STAT_Y);//tweak
        text(player.getSavePct(), ltX+430, STAT_Y);//tweak
        text(player.getGoalie().getGa(), ltX+584, STAT_Y);//tweak
        text(player.getGoalie().getGaa(), ltX+724, STAT_Y);//tweak
      }
    }

    if (type.equals("GAME SUMMARY")) {
      if (!player.getPosition().equals("G")) {

        text("G", ltX+90, STAT_LABEL_Y);//tweak
        text("A", ltX+250, STAT_LABEL_Y);//tweak
        text("PTS", ltX+410, STAT_LABEL_Y);//tweak
        text("P", ltX+570, STAT_LABEL_Y);//tweak
        text("PIM", ltX+730, STAT_LABEL_Y);//tweak

        fill(255);
        textFont(myFont);

        text(player.getGoalsGame(), ltX+90, STAT_Y);//tweak
        text(player.getAssistsGame(), ltX+250, STAT_Y);//tweak
        text(player.getPtsGame(), ltX+410, STAT_Y);//tweak
        text(player.getPenaltiesGame(), ltX+570, STAT_Y);//tweak
        text(player.getPimGame(), ltX+730, STAT_Y);//tweak
      }

      else {
        text("GA", ltX+200, STAT_LABEL_Y);//tweak
        text("SV", ltX+400, STAT_LABEL_Y);//tweak
        text("SHOTS", ltX+600, STAT_LABEL_Y);//tweak


        fill(255);
        textFont(myFont);
        text(player.getGa(), ltX+200, STAT_Y);//tweak
        text(player.getSaves(), ltX+400, STAT_Y);//tweak
        text(player.getGa() + player.getSaves(), ltX+600, STAT_Y);//tweak
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

