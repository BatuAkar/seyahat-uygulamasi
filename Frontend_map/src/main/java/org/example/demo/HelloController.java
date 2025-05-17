package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class HelloController {

    @FXML
    private WebView webView;

    private WebEngine webEngine;

    @FXML
    public void initialize() {
        webEngine = webView.getEngine();

        // JavaScript köprüsü
        webEngine.getLoadWorker().stateProperty().addListener((obs, old, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javafx", new JavaFXBridge());
            }
        });
    }

    // JavaScript'ten çağrılacak köprü sınıfı
    public class JavaFXBridge {
        public void citySelected(String pointType, String cityName, double lat, double lon) {
            System.out.println(pointType + " şehri seçildi: " + cityName + " (" + lat + ", " + lon + ")");
            // Burada seçilen şehirleri işleyebilirsiniz
        }
    }
}