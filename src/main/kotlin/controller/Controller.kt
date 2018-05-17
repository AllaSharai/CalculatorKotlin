package controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import java.net.URL
import java.util.*

class Controller : Initializable {

    @FXML
    var expressionText : TextField = TextField()

    override fun initialize(location: URL?, resource: ResourceBundle?) {

    }

    @FXML
    fun digitPressed(event: ActionEvent) {

        var button : Button = event.source as Button
        expressionText.text = expressionText.text + button.text
    }

}