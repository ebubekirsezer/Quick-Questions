package com.ess.quickquestions.model

data class Category(
    val category: List<CategoryX> = listOf()
)

data class CategoryX(
    val id: String = "",
    val name: String = "",
    val questions: List<Question> = listOf()
)

data class Question(
    val answer: String = "",
    val id: String = "",
    val option_1: String = "",
    val option_2: String = "",
    val option_3: String = "",
    val option_4: String = "",
    val question: String = ""
)