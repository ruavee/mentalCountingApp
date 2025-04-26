package com.ruavee.mentalcountingapp.domain.usecase

import com.ruavee.mentalcountingapp.data.repository.ProblemRepository

class GenerateProblemUseCase(
    private val repository: ProblemRepository
) {
    operator fun invoke(level: Int): String = repository.generate(level)
}