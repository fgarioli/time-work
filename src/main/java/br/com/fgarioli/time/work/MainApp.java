package br.com.fgarioli.time.work;

import br.com.fgarioli.javafx.utils.Alerts;
import br.com.fgarioli.javafx.utils.Config;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private ConfigurableApplicationContext context;
    
    private FXMLLoader fxmlLoader;
    
    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/fxml/FXMLMain.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
//        stage.getIcons().add(Config.APP_IMAGE);
        stage.setTitle(Config.APP_NAME + " " + Config.APP_VERSION);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(MainApp.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
    }

    @Override
    public void stop() throws Exception {
        context.stop();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String appId = "TimeWork-1.0";
        boolean alreadyRunning;

        try {
            JUnique.acquireLock(appId);
            alreadyRunning = false;
        } catch (AlreadyLockedException e) {
            alreadyRunning = true;
        }

        if (!alreadyRunning) {
            launch(args);
//            LauncherImpl.launchApplication(MainApp.class, FXMLPreloaderController.class, args);
        } else {
            Alerts.alert("Já existe outra instância desta aplicação em execução!");
            System.exit(0);
        }
    }

}
