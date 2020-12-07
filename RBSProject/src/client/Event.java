package client;

public interface Event {
    void onClientConnect(String clientName, String message);

    void onClientDisconnect(String clientName, String message);

    void onMessageReceive(String clientName, String message);

    void onChangeRoom();
    
    void onChoiceMade(int Choice);
    
    void onSyncPlayers(String clientName);
    
    void onPlayerConnect(String clientName);
    
    void onSetCountdown(String message, int duration);
    
    void onToggleLock(boolean isLock);
    
    void onSetResults(String decision);

    
}