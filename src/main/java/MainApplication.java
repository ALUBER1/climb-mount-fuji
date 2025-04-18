import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class MainApplication extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("CLIMB MOUNT FUJI");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
