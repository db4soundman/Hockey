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
  private int sX;
  private int sY;
  private color awayColor;
  private color homeColor;

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
    for (float i = 0; i<254; i +=6.4) {//tweak
      stroke(lerpColor(awayColor, black, i/255));
      line(x+116, round(y+7+(i/6.4)), x+378, round(y+7+(i/6.4)));//tweak 
      stroke(lerpColor(homeColor, black, i/255));
      line(x+474, round(y+7+(i/6.4)), x+734, round(y+7+(i/6.4)));//tweak
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

      stroke(lerpColor(awayColor, black, i/255));
      line(cX+160, round(cY+9+(i/3.82)), cX+801, round(cY+9+(i/3.82)));//tweak 
      stroke(lerpColor(homeColor, black, i/255));
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
      stroke(lerpColor(yellow, darkerYellow, i/255));
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
    for (float i = 0; i<=255; i +=3.82) {
      if (team.equals("HOME"))
        stroke(lerpColor(homeColor, black, i/255));
      else stroke(lerpColor(awayColor, black, i/255));
      line(ltX, cY+(i/3.82), ltX+800, cY+(i/3.82));
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

