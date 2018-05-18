package logic

import java.util.*

class Calculator {

    companion object {

        var numbersStack: Stack<Double> = Stack()
        var operatorsStack: Stack<Char> = Stack()

        fun calculate(expression: String) : Double{
            numbersStack.clear()
            operatorsStack.clear()
            var result = 0.0
            splitExpression(expression.replace("(-", "(0-"))
            return result
        }

        private fun splitExpression(expression: String) {
            var number: String = String()
            for (symbol in expression) {
                if (symbol.isDigit() || symbol == '.') {
                    number = number.plus(symbol)

                } else {
                    if (!number.isEmpty()) {
                        numbersStack.push(number.toDouble())
                    }
                    operator(symbol)
                    number = String()
                }

            }
            if (!number.isEmpty()) {
                numbersStack.push(number.toDouble())
            }
            println(numbersStack)
            println(operatorsStack)
        }

        private fun operator(operator: Char) {
            operatorsStack.push(operator)
        }

    }

}