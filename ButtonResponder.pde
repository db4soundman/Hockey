private class ButtonResponder implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    loop();
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

