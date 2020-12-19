package server;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.BaseRPSDesign;
import core.Countdown;

public class Room extends BaseRPSDesign implements AutoCloseable {
    private static SocketServer server;// used to refer to accessible server functions
    private String name;
    private final static Logger log = Logger.getLogger(Room.class.getName());

    // Commands
    private final static String COMMAND_TRIGGER = "/";
    private final static String CREATE_ROOM = "createroom";
    private final static String JOIN_ROOM = "joinroom";
    
    ServerThread thread;

    public Room(String name) {
	this.name = name;
    }

    public static void setServer(SocketServer server) {
	Room.server = server;
    }

    public String getName() {
	return name;
    }

    private List<ServerThread> clients = new ArrayList<ServerThread>();

    protected synchronized void addClient(ServerThread client) {
	client.setCurrentRoom(this);
	if (clients.indexOf(client) > -1) {
	    log.log(Level.INFO, "Attempting to add a client that already exists");
	}
	else {
	    clients.add(client);
	    if (client.getClientName() != null) {
		client.sendClearList();
		sendConnectionStatus(client, true, "joined the room " + getName());
		updateClientList(client);
	    }
	}
    }

    private void updateClientList(ServerThread client) {
	Iterator<ServerThread> iter = clients.iterator();
	while (iter.hasNext()) {
	    ServerThread c = iter.next();
	    if (c != client) {
		boolean messageSent = client.sendConnectionStatus(c.getClientName(), true, null);
	    }
	}
    }

    protected synchronized void removeClient(ServerThread client) {
	clients.remove(client);
	if (clients.size() > 0) {
	    // sendMessage(client, "left the room");
	    sendConnectionStatus(client, false, "left the room " + getName());
	}
	else {
	    cleanupEmptyRoom();
	}
    }

    private void cleanupEmptyRoom() {
	// If name is null it's already been closed. And don't close the Lobby
	if (name == null || name.equalsIgnoreCase(SocketServer.LOBBY)) {
	    return;
	}
	try {
	    log.log(Level.INFO, "Closing empty room: " + name);
	    close();
	}
	catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    protected void joinRoom(String room, ServerThread client) {
	server.joinRoom(room, client);
    }

    protected void joinLobby(ServerThread client) {
	server.joinLobby(client);
    }
    
    public void joinGameRoom(ServerThread client) {
     server.joinRoom("game", client);
    }

    /***
     * Helper function to process messages to trigger different functionality.
     * 
     * @param message The original message being sent
     * @param client  The sender of the message (since they'll be the ones
     *                triggering the actions)
     */
    private boolean processCommands(String message, ServerThread client) {
	boolean wasCommand = false;
	try {
	    if (message.indexOf(COMMAND_TRIGGER) > -1) {
		String[] comm = message.split(COMMAND_TRIGGER);
		log.log(Level.INFO, message);
		String part1 = comm[1];
		String[] comm2 = part1.split(" ");
		String command = comm2[0];
		if (command != null) {
		    command = command.toLowerCase();
		}
		String roomName;
		switch (command) {
		case CREATE_ROOM:
		    roomName = comm2[1];
		    if (server.createNewRoom(roomName)) {
			joinRoom(roomName, client);
		    }
		    wasCommand = true;
		    break;
		case JOIN_ROOM:
		    roomName = comm2[1];
		    joinRoom(roomName, client);
		    wasCommand = true;
		    break;
		}
	    }
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	return wasCommand;
    }

    // TODO changed from string to ServerThread
    protected void sendConnectionStatus(ServerThread client, boolean isConnect, String message) {
	Iterator<ServerThread> iter = clients.iterator();
	while (iter.hasNext()) {
	    ServerThread c = iter.next();
	    boolean messageSent = c.sendConnectionStatus(client.getClientName(), isConnect, message);
	    if (!messageSent) {
		iter.remove();
		log.log(Level.INFO, "Removed client " + c.getId());
	    }
	}
    }

    /***
     * Takes a sender and a message and broadcasts the message to all clients in
     * this room. Client is mostly passed for command purposes but we can also use
     * it to extract other client info.
     * 
     * @param sender  The client sending the message
     * @param message The message to broadcast inside the room
     */
    protected void sendMessage(ServerThread sender, String message) {
	log.log(Level.INFO, getName() + ": Sending message to " + clients.size() + " clients");
	if (processCommands(message, sender)) {
	    // it was a command, don't broadcast
	    return;
	}
	Iterator<ServerThread> iter = clients.iterator();
	while (iter.hasNext()) {
	    ServerThread client = iter.next();
	    boolean messageSent = client.send(sender.getClientName(), message);
	    if (!messageSent) {
		iter.remove();
		log.log(Level.INFO, "Removed client " + client.getId());
	    }
	}
    }
    
    private List<ClientPlayer> readyList = new ArrayList<ClientPlayer>();
    
    public int totalyReady() {
    	return readyList.size();
    }
    public void addToReadyList(ClientPlayer cp) {
    	if(!readyList.contains(cp)) {
    		readyList.add(cp);
    		for(ClientPlayer cps : readyList) {
    			System.out.println(cps.getClientName());
    		}
    	}
    }
    public void removeFromReadyList(ClientPlayer cp) {
   		readyList.remove(cp);	
    }
    public void sendtotalReady(int total) {
    	Iterator<ClientPlayer> iter = readyList.iterator();
    	while (iter.hasNext()) {
    	    ClientPlayer client = iter.next();
    	    boolean messageSent = client.getServerThread().sendReadyAndAmount(true, total);
    	    if (!messageSent) {
    		iter.remove();
    	    }
    	}
    }

    public ClientPlayer getNextPlayer(ClientPlayer cp) {
    	for(int x = 0; x < readyList.size(); x++) {
    		if (readyList.get(x).equals(cp)) {
    			if (x % 2 == 0) {
    				return readyList.get(x+1);
    			}
    			else if(x % 2 == 1) {
    				return readyList.get(x-1);
    			}
    		}
    	}

    	return cp;
    }
    
    protected void sendCountdown(String message, int duration) {
    	Iterator<ClientPlayer> iter = readyList.iterator();
    	while (iter.hasNext()) {
    		ClientPlayer client = iter.next();
    	    boolean messageSent = client.client.sendCountdown(message, duration);
    	    if (!messageSent) {
    		iter.remove();
    	    }
    	}
        }
    /***
     * Will attempt to migrate any remaining clients to the Lobby room. Will then
     * set references to null and should be eligible for garbage collection
     */
    @Override
    public void close() throws Exception {
	int clientCount = clients.size();
	if (clientCount > 0) {
	    log.log(Level.INFO, "Migrating " + clients.size() + " to Lobby");
	    Iterator<ServerThread> iter = clients.iterator();
	    Room lobby = server.getLobby();
	    while (iter.hasNext()) {
		ServerThread client = iter.next();
		lobby.addClient(client);
		iter.remove();
	    }
	    log.log(Level.INFO, "Done Migrating " + clients.size() + " to Lobby");
	}
	server.cleanupRoom(this);
	name = null;
	// should be eligible for garbage collection now
    }
	//rock = 0
	//paper = 1
	//scizzors = 2
	//if return 1 player wins, if 0 other wins, 2 is a tie.
	
	public int gameDecision(int one, int two)
	{
		if(one == 1 && two == 0)
		{
			return 1;
		}
		else if(one == 1 && two == 2)
		{
			return 0;
		}
		else if(one == 2 && two == 0)
		{
			return 0;
		}
		else if(one == 2 && two == 1)
		{
			return 1;
		}
		else if(one == 0 && two == 1)
		{
			return 0;
		}
		else if(one == 0 && two== 2)
		{
			return 1;
		}
		else if(one == 3)
		{
			return 0;
		}
		else if(two == 3)
		{
			return 1;
		}
		return 2;
		
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
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}


}