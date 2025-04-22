package com.ruavee.mentalcountingapp.data.utils

import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.pow

object MathUtils {
    fun generateProblem(level: Int): String {
        val range = 10.0.pow(level).toInt()
        var problem = ""
        var result = Double.NaN

        do {
            val a = (-range..range).random()
            val b = (-range..range).random()
            val sign = listOf('+', '-', '*', '/').random()

            if (sign == '/' && (b == 0 || a % b != 0)) continue

            problem = if (b >= 0) "$a $sign $b" else "$a $sign ($b)"

            result = ExpressionBuilder(problem)
                .build()
                .evaluate()
        } while (result % 1 != 0.0)

        return problem
    }
}