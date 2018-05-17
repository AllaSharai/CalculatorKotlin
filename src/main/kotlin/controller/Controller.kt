package controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import java.net.URL
import java.util.*

class Controller : Initializable {

    var dotAllowed: Boolean = true

    @FXML
    var expressionText: TextField = TextField()

    override fun initialize(location: URL?, resource: ResourceBundle?) {

    }

    @FXML
    fun digitPressed(event: ActionEvent) {
        if (expressionText.text == "0") {
            expressionText.clear()
        }
        val button: Button = event.source as Button
        expressionText.text = expressionText.text + button.text
    }

    @FXML
    fun dotPressed() {
        val lastSymbol: Char = getLastSymbol()
        if (Character.isDigit(lastSymbol) && dotAllowed) {
            expressionText.text = expressionText.text + '.'
            //TODO: allow dot after symbols + - / * ( or )
            dotAllowed = false
        }
    }

    @FXML
    fun cPressed() {
        expressionText.text = "0"
    }

    @FXML
    fun cePressed() {
        expressionText.text = expressionText.text.dropLast(1)
        if (expressionText.text.isEmpty()) {
            expressionText.text = "0"
        }
    }

    @FXML
    //FIXME
    fun openParenthesisPressed() {
        if (expressionText.text == "0") {
            expressionText.clear()
        }

        expressionText.text = expressionText.text + '('
    }

    @FXML
    //FIXME
    fun closeParenthesisPressed() {
        expressionText.text = expressionText.text + ')'
    }

    private fun getLastSymbol(): Char {
        return expressionText.text[expressionText.text.length - 1]
    }

}