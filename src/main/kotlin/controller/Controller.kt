package controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import logic.Calculator
import java.net.URL
import java.util.ResourceBundle

class Controller : Initializable {

    var dotAllowed: Boolean = true

    val operators = listOf('+', '-', '*', '/')

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
    fun openParenthesisPressed() {

        if (!operators.contains(getLastSymbol())) {
            if (expressionText.text == "0") {
                expressionText.clear()
                expressionText.text = expressionText.text + '('
            }
            return
        }

        expressionText.text = expressionText.text + '('
    }

    @FXML
    fun closeParenthesisPressed() {
        val lastSymbol: Char = getLastSymbol()
        if (Character.isDigit(lastSymbol)) {
            expressionText.text = expressionText.text + ')'
        }
    }

    @FXML
    fun operatorPressed(event: ActionEvent) {
        val button: Button = event.source as Button
        val lastSymbol: Char = getLastSymbol()

        if (Character.isDigit(lastSymbol) || lastSymbol == ')') {
            expressionText.text = expressionText.text + button.text
            dotAllowed = true
            return
        }

        if (button.text == "-" && lastSymbol == '(') {
            expressionText.text = expressionText.text + button.text
            dotAllowed = true
        }
    }

    @FXML
    fun equalsPressed() {
        var result = Calculator.calculate(expression = expressionText.text).toString()
        if (result.endsWith(".0")) {
            result = result.dropLast(2)
        }
        expressionText.text = result
        dotAllowed = true
    }

    private fun getLastSymbol(): Char {

        return expressionText.text[expressionText.text.length - 1]
    }

}