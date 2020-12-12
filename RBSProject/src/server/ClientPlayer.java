package server;

import client.Player;

public class ClientPlayer {
	int choice;
    public ClientPlayer(ServerThread client, Player player, int choice) {
	this.client = client;
	this.player = player;
	this.choice = choice;
    }

    public ServerThread client;
    public Player player;
}
