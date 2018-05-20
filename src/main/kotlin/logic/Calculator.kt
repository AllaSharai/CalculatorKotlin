package logic

import java.util.*

class Calculator {

    companion object {

        private var numbersStack: Stack<Double> = Stack()
        private var operatorsStack: Stack<Char> = Stack()

        fun calculate(expression: String): Double {
            numbersStack.clear()
            operatorsStack.clear()
            return calculateExpression(expression.replace("(-", "(0-"))
        }

        private fun calculateExpression(expression: String): Double {
            var number = String()
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

            while (!operatorsStack.empty()){
                popOperator()
            }
            return numbersStack.pop()
        }

        private fun operator(operator: Char) {
            if (canPop(operator)) {
                popOperator()
            }
            operatorsStack.push(operator)
        }

        private fun popOperator() {
            val B = numbersStack.pop()
            val A = numbersStack.pop()

            when (operatorsStack.pop()) {
                '+' -> numbersStack.push(A + B)
                '-' -> numbersStack.push(A - B)
                '*' -> numbersStack.push(A * B)
                '/' -> numbersStack.push(A / B)
            }
        }

        private fun canPop(operator: Char): Boolean {
            return !operatorsStack.empty() && getPriority(operator) <= getPriority(operatorsStack.peek())
        }

        private fun getPriority(operator: Char): Int {
            when (operator) {
                '+', '-' -> return 1
                '*', '/' -> return 2
            }
            return -1
        }

    }

}