package Main;

import Input.KeyManager;
import Input.MouseManager;
import Worlds.WorldBase;


/**
 * Created by AlexVR on 7/1/2018.
 * Made by the people, for the people...
 */

public class Handler {

	//Member data
    private GameSetUp game;
    private WorldBase world;

    //Constructor
    public Handler(GameSetUp game){
        this.game = game;
    }

    //member data
    //getters accessors
    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public GameSetUp getGame() {
        return game;
    }

    //setters mutators
    public void setGame(GameSetUp game) {
        this.game = game;
    }

    //getters
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public WorldBase getWorld() {
        return world;
    }

    //setters
    public void setWorld(WorldBase world) {
        this.world = world;
    }


}
