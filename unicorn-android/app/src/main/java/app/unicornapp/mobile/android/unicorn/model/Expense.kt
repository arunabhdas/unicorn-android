package app.unicornapp.mobile.android.unicorn.model

data class Expense(
    val id: String,
    val title: String,
    val amount: Double,
    val date: Long,
    val category: ExpenseCategory
)

enum class ExpenseCategory {
    ALL,
    FOOD,
    TRANSPORT,
    CLOTHING
}