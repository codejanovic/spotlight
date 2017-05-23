package sample;

import java.net.URL;

public interface SpotlightApp {
    default URL mainViewLocation() {
        return getClass().getResource("/main.fxml");
    }
}
