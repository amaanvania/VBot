package api.interfaces.listeners.game;

public interface GameMessageListener
{
    void messageReceived(String sender, int type, String message);
}
