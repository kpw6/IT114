package core;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public abstract class BaseRPSDesign extends JPanel {
	private static final long serialVersionUID = 5L;
    // flag used to terminate game loop
    protected boolean isRunning = false;
    // thread sleep time in ms (16 ms is approx 60 frames per second)
    public int SLEEP = 16;
    BaseRPSDesign bgp;
    // when true, disables certain triggers the server version doesn't need
    public boolean isServer = false;
    // by setting this we can have the instance awake() but not start until
    // startGameLoop() is called
    public boolean delayGameLoop = false;
    private final static Logger log = Logger.getLogger(BaseRPSDesign.class.getName());
    Thread gameLoop;

    // constructor triggers the various events
    public BaseRPSDesign() {
	awake();
	bgp = this;
	if (!delayGameLoop) {
	    startGameLoop();
	}
    }

    public BaseRPSDesign(boolean delay) {
	delayGameLoop = delay;
	awake();
	bgp = this;
	if (!delayGameLoop) {
	    startGameLoop();
	}
    }
    public void startGameLoop() {
    	if (gameLoop == null) {
    	    isRunning = true;
    	    gameLoop = new Thread() {
    		@Override
    		public void run() {
    		    bgp.start();
    		    if (!isServer) {
    			bgp.attachListeners();
    		    }
    		    while (isRunning) {
    			bgp.update();
    			bgp.lateUpdate();
    			if (!isServer) {
    			    bgp.repaint();
    			}

    			// give it some rest
    			try {
    			    Thread.sleep(SLEEP);
    			}
    			catch (InterruptedException e) {
    			    e.printStackTrace();
    			}
    		    }
    		    log.log(Level.INFO, "game loop terminated");
    		    bgp.quit();
    		}
    	    };
    	    gameLoop.start();
    	}
        }
    public abstract void awake();

    // called when thread is started
    public abstract void start();

    // called every frame
    public abstract void update();

    // called every frame after update has been called
    public abstract void lateUpdate();

    // called when loop exits
    public abstract void quit();
    
    public abstract void attachListeners();


}
