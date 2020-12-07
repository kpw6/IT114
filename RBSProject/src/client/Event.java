package client;

public interface Event {
    void onClientConnect(String clientName, String message);

    void onClientDisconnect(String clientName, String message);

    void onMessageReceive(String clientName, String message);

    void onChangeRoom();
    
    void onChoiceMade(String Choice1, String Choice2);
    
    void onSyncPlayers(String clientName);
    
    void onPlayerConnect(String clientName);
    
    void onSetCountdown(String message, int duration);
    
    void onToggleLock(boolean isLock);

    
}