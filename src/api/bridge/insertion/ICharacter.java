package api.bridge.insertion;

public interface ICharacter extends IRenderable
{
    int getLoopCycle();

    int getPathQueueSize();

    int getInteractingIndex();

    int getAnimation();

    int getHealthLevel();

    int getCurrentPray();

    int getMaxHealth();

    int getSceneX();

    int getSceneY();

    int[] getQueueX();

    int[] getQueueY();

    String getOverheadText();
}
