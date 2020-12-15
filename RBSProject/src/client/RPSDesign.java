package client;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import core.BaseRPSDesign;
import core.Countdown;
import client.User;

public class RPSDesign extends BaseRPSDesign implements Event {
	
	private static final long serialVersionUID = -1121202275148798015L;
	private final static Logger log = Logger.getLogger(RPSDesign.class.getName());
	RPSDesign self;
	CardLayout card;
	JFrame frame;
	User user;
	JPanel game = new JPanel();
	JPanel waitScreen = new JPanel();
	JPanel resultScreen = new JPanel();
	JButton spectate;
	int totalReady, choice = 3, decision;
	Countdown timer;
	String rival;
	JLabel waitingPlayers = new JLabel("Players ready: " + totalReady + "/2 needed");
	JLabel countdown = new JLabel("Timer: ");
	JLabel against = new JLabel("Playing against: " + rival);
	JLabel result = new JLabel();
	Boolean waitCreated = false, firstPerson = false, gameCreated = false, resultCreated = false;
	
	public RPSDesign() {
		self = this;
		card = new CardLayout();
		setLayout(card);
		gameStart();
	}
	
	public void gameStart() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Rock, Paper, Scizzors");
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	SocketClient.sendMessage("has joined a game");
		    	SocketClient.sendReady(true);

			}

		});
		JButton spectateButton = new JButton("Spectate");
		spectateButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {

			}

		});
		panel.add(spectateButton);
		panel.add(title);
		panel.add(startButton);
		self.add(panel);
		
		
		
	}
	
	public void gameWaitScreen()
	{
		if(!waitCreated) {
			
			JLabel waitingMsg = new JLabel("Waiting for other players to connect");
			JButton startGames = new JButton("Start");
			
			waitScreen.add(waitingMsg);
			waitScreen.add(waitingPlayers);
			if (firstPerson) {
				waitScreen.add(startGames);
			}
			self.add(waitScreen);
			startGames.addActionListener(new ActionListener() {
	
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	if (totalReady >= 2) {
			    		SocketClient.startCountdown();
			    	}
			    }
			});
		}	
	}
	
	public void gamePlayScreen() {	
	if (!gameCreated) {
		JButton rockButton = new JButton("Rock");
		JButton paperButton = new JButton("Paper");
		JButton scizzorsButton = new JButton("Scizzors");
		JButton skipButton = new JButton("Skip");
		JLabel against = new JLabel("Playing against: " + rival);

		game.add(rockButton);
		game.add(paperButton);
		game.add(scizzorsButton);
		game.add(skipButton);
		game.add(against);
		
		self.add(game);
		//Scizzors button press
		scizzorsButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	choice = 2;
		    	resultsScreen();
		    	self.next();

			}

		});
		//rock button press
		rockButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	choice = 0;
		    	resultsScreen();
		    	self.next();


			}

		});
		//paper button press
		paperButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	choice = 1;
		    	resultsScreen();
		    	self.next();

			}

		});
		//skip button press
		skipButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	self.gotoMenu();

			}

		});
	}
	}
	public void spectateScreen() {
		
	}
	
	public void resultsScreen() {
		if (!resultCreated) {
			JLabel waitForResults = new JLabel("Waiting for round to finish");
			resultScreen.add(waitForResults);
			self.add(resultScreen);
		}
	}
	
	
	 @Override
	 public void quit() {
	 
	 }
	 
	void next() {
		card.next(self);
	}
	void previous() {
		card.previous(self);
	}
	void gotoMenu() {
		card.first(self);
	}
	void gotoCard(JPanel p) {
	}
	public void addResults(String results) {

	}
	
	
    public void onSetCountdown(String message, int duration) {
    	against.setText("Playing against: " + rival);
    	game.remove(against);
    	game.add(against);
        gamePlayScreen();
        gameCreated = true;
        self.next();
    	timer = new Countdown(message, duration, (x) -> {
    	   SocketClient.sendChoice(choice);
    	   if(!resultScreen.isShowing()) {
    		   self.next();
    	   }
    	});
    }
	@Override
	public void onClientConnect(String clientName, String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClientDisconnect(String clientName, String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMessageReceive(String clientName, String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onChangeRoom() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onChoiceMade(int Choice) {
		
	}
	@Override
	public void onSyncPlayers(String clientName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void awake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lateUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerConnect(String clientName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSetResults(String results) {
		result.setText(results);
		resultScreen.remove(result);
		resultScreen.add(result);
		JButton spectate = new JButton("Spectate");
		JButton returns = new JButton("Return");
		if (decision == 0 || decision == 3) {
			resultScreen.remove(returns);
	    	resultScreen.add(spectate);
	    	SocketClient.sendRemoveReady();
		}
		else {
			resultScreen.remove(spectate);
	    	resultScreen.add(returns);
		}
		spectate.addActionListener(new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	

		    }
		});
		returns.addActionListener(new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
	}

	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReady(String clientName, boolean isReady) {
		
	}

	@Override
	public void onSetTotalReady(int totalReady) {
		this.totalReady = totalReady;
		waitingPlayers.setText("Players ready: " + totalReady + "/2 needed");
		waitScreen.remove(waitingPlayers);
		waitScreen.add(waitingPlayers);
		if(totalReady == 1) {
			firstPerson = true;
		}
    	gameWaitScreen();
    	waitCreated = true;
    	if (!waitScreen.isShowing()){
    		self.next();
    	}
	}

	@Override
	public void onSetOtherPlayer(String rival) {
		this.rival = rival;
		
	}

	@Override
	public void onSetDecision(int decision) {
		this.decision = decision;
		
	}




}
