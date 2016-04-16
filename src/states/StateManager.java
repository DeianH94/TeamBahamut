package states;

public class StateManager {
    //It will hold what state we currently want to tick() and render()
    private static States currentState = null;

    //Changes the current state of the game
    public static void setState(States state) {
        currentState = state;
    }

    //Gets the current state of the game
    public static States getState() {
        return currentState;
    }
}
