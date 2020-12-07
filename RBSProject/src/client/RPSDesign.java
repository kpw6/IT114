package client;


import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.BaseRPSDesign;
import core.Countdown;
import client.User;

public class RPSDesign extends BaseRPSDesign implements Event {
	
	private static final long serialVersionUID = -1121202275148798015L;
	RPSDesign self;
	CardLayout card;
	JFrame frame;
	User user;
	Countdown timer;
	String decision;
	
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
		    	gamePlayScreen();
		    	self.next();

			}

		});
		JButton spectateButton = new JButton("Spectate");
		startButton.addActionListener(new ActionListener() {

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
		JPanel panel = new JPanel();
		JLabel waiting = new JLabel("Waiting for other players to connect");
		
		panel.add(waiting);
		this.add(panel);
			

		
	}
	
	public void gamePlayScreen()
	{
		timer = new Countdown("Time: ", 15);
		JPanel panel = new JPanel();		
		JButton rockButton = new JButton("Rock");
		JButton paperButton = new JButton("Paper");
		JButton scizzorsButton = new JButton("Scizzors");
		JButton skipButton = new JButton("Skip");
		JButton menuButton = new JButton("Return to Menu");
		JLabel scizzors = new JLabel("You chose scizzors");
		JLabel rock = new JLabel("You chose rock");
		JLabel paper = new JLabel("You chose paper");
		JLabel timeOut = new JLabel("Time ran out");


		panel.add(rockButton);
		panel.add(paperButton);
		panel.add(scizzorsButton);
		panel.add(skipButton);
		panel.add(scizzors);
		panel.add(paper);
		panel.add(rock);
		panel.add(timeOut);
		scizzors.setVisible(false);
		rock.setVisible(false);
		paper.setVisible(false);
		timeOut.setVisible(false);
		
		
		
		this.add(panel);
		//Scizzors button press
		scizzorsButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	timer.cancel();
		    	self.onChoiceMade(2);
		    	resultScreen();
		    	self.next();

			}

		});
		//rock button press
		rockButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	timer.cancel();
		    	onChoiceMade(0);
		    	resultScreen();
		    	self.next();


			}

		});
		//paper button press
		paperButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	timer.cancel();
		    	onChoiceMade(1);
		    	resultScreen();
		    	self.next();

			}

		});
		//skip button press
		skipButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	self.gotoMenu();
		    	timer.cancel();

			}

		});
		menuButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	self.gotoMenu();

			}

		});
	}
	public void resultScreen() {
		SocketClient.sendMessage(decision);
		JPanel panel = new JPanel();
		JLabel results = new JLabel(decision);
		JButton button = new JButton("Menu");
		button.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	self.gotoMenu();

			}

		});
		panel.add(button);
		panel.add(results);
		self.add(panel);
		
	}
	public void spectateScreen() {
		
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
	
	
    public void onSetCountdown(String message, int duration) {
	// TODO Auto-generated method stub
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
		SocketClient.sendChoice(Choice);
		
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
	public void onToggleLock(boolean isLock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSetResults(String decision) {
		this.decision = decision;
	}


	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String processResults(int decision) {
		// TODO Auto-generated method stub
		return null;
	}


}
