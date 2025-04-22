package com.ruavee.mentalcountingapp.data.repository

import com.ruavee.mentalcountingapp.data.utils.MathUtils

class ProblemRepository {
    fun generate(level: Int): String = MathUtils.generateProblem(level)
}