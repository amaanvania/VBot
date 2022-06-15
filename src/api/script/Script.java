package api.script;

import api.BotContext;
import api.bridge.wrappers.utility.Timing;

import static api.script.Script.ScriptState.RUNNING;

public abstract class Script implements Runnable
{
    private final long startTime;
    private ScriptState state = null;
    public static String user = "";
    public static String pw = "";
    public static int jailFlag = 0;

    public enum ScriptState
    {
        RUNNING, PAUSED, STOPPED
    }

    public Script()
    {
        startTime = System.currentTimeMillis();
        state = RUNNING;
    }
    /**
     * @return script runtime
     */
    public long getRuntime()
    {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Sets the script's state
     */
    public void setScriptState(ScriptState state)
    {
        this.state = state;
        System.out.println("Script state set to " + state.toString());
    }

    /**
     * on false, script will terminate
     *
     * @return true to start the script
     */
    public abstract boolean onStart();

    /**
     * @return void
     */
    public abstract void onStop();

    /**
     * the body of the script
     *
     * @return sleep duration
     */
    public abstract int loop();

    @Override
    public final void run()
    {
        if (onStart())
        {
            int duration;
            while (this.state != ScriptState.STOPPED)
            {
                if (state == ScriptState.PAUSED)
                {
                    Timing.sleep(250);
                }

                     else {
                        duration = loop();
                        if (duration < 0) {
                            state = ScriptState.STOPPED;
                        } else {
                            Timing.sleep(duration);
                        }
                    }
                }

        }
        onStop();
        BotContext.engine.setRunningScript(null);
        BotContext.engine.frame.stop.setEnabled(false);
        BotContext.engine.frame.pause.setEnabled(false);
        BotContext.engine.frame.play.setEnabled(true);
    }

}
