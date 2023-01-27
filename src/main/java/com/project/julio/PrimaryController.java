package com.project.julio;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    /**
     * Let change of controller
     * @throws IOException
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
