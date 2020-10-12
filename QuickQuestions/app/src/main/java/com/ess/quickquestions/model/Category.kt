package com.ess.quickquestions.model

data class Categories(
    val categories: List<Category>
)

data class Category(
    val id: String,
    val name: String,
    val questions: Questions
)

data class Questions(
    val answer: String,
    val option_1: String,
    val option_2: String,
    val option_3: String,
    val option_4: String,
    val question: String
)