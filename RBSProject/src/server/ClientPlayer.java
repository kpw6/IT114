package server;

import client.Player;

public class ClientPlayer {
	int choice;
    public ServerThread client;
    
    public ClientPlayer(ServerThread client) {
	this.client = client;
    }
    
    public void setChoice(int choice) {
    	this.choice = choice;
    }
    
    public int getChoice() {
    	return this.choice;
    }
    public String getClientName() {
    	return client.getClientName();
    }
    public ServerThread getServerThread() {
    	return this.client;
    }

}
