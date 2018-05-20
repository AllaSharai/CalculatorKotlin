package logic

import java.util.Stack


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

                number = if (symbol.isDigit() || symbol == '.') {
                    number.plus(symbol)
                } else {
                    if (!number.isEmpty()) {
                        numbersStack.push(number.toDouble())
                    }
                    operator(symbol)
                    String()
                }

            }
            if (!number.isEmpty()) {
                numbersStack.push(number.toDouble())
            }
            println(numbersStack)
            println(operatorsStack)

            while (!operatorsStack.empty()) {
                popOperator()
            }
            return numbersStack.pop()
        }

        private fun operator(operator: Char) {

            if (operator == ')') {
                while (operatorsStack.peek() != '(') {
                    popOperator()
                }
                operatorsStack.pop()
                return
            }

            if (canPop(operator)) {
                popOperator()
            }
            operatorsStack.push(operator)
        }

        private fun popOperator() {
            val b = numbersStack.pop()
            val a = numbersStack.pop()

            when (operatorsStack.pop()) {
                '+' -> numbersStack.push(a + b)
                '-' -> numbersStack.push(a - b)
                '*' -> numbersStack.push(a * b)
                '/' -> numbersStack.push(a / b)
            }
        }

        private fun canPop(operator: Char): Boolean {
            return !operatorsStack.empty()
                    &&
                    getPriority(operator) <= getPriority(operatorsStack.peek())
                    &&
                    operator != '('
        }

        private fun getPriority(operator: Char): Int {
            when (operator) {
                '(' -> return -1
                '+', '-' -> return 1
                '*', '/' -> return 2
                ')' -> return 3
            }
            return -2
        }

    }

}