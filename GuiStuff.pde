void makeClockControl() {
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

void displayControls() {
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

void makeStatusPanel() {
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

void makeScoreControl() {
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


void makeGraphicsControl() {
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

void makePenaltyControl() {
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

