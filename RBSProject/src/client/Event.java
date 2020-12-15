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
    
    void onSetResults(String results);
    
    void onReady(String clientName, boolean isReady);
    
    void onSetTotalReady(int totalReady);
    
    void onSetOtherPlayer(String rival);
    
    void onSetDecision(int decision);
    
}