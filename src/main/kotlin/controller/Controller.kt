package controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import java.net.URL
import java.util.*

class Controller : Initializable {

    var dotAllowed : Boolean = true

    @FXML
    var expressionText : TextField = TextField()

    override fun initialize(location: URL?, resource: ResourceBundle?) {

    }

    @FXML
    fun digitPressed(event: ActionEvent) {

        val button : Button = event.source as Button
        expressionText.text = expressionText.text + button.text
    }

    @FXML
    fun dotPressed() {
        val lastSymbol : Char = getLastSymbol()
        if (Character.isDigit(lastSymbol) && dotAllowed) {
            expressionText.text = expressionText.text + '.'
            //TODO: allow dot after symbols + - / * ( or )
            dotAllowed = false
        }


    }

    fun getLastSymbol() : Char {
        return expressionText.text[expressionText.text.length - 1]
    }

}