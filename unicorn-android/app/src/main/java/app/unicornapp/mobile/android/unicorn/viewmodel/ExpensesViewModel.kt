package app.unicornapp.mobile.android.unicorn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.unicornapp.mobile.android.unicorn.model.Expense
import app.unicornapp.mobile.android.unicorn.model.ExpenseCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


class ExpensesViewModel: ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>> (
        listOf(
            Expense("1", "Coffee", 3.50, 123, ExpenseCategory.FOOD),
            Expense("2", "Tea", 3.50, 123, ExpenseCategory.FOOD),
            Expense("3", "Bus", 3.50, 123, ExpenseCategory.FOOD),
            Expense("4", "Subway", 3.50, 123, ExpenseCategory.FOOD),
        )
    )

    val expenses: StateFlow<List<Expense>> = _expenses

    private val _selectedCategory = MutableStateFlow(ExpenseCategory.ALL)

    fun selectCategory(category: ExpenseCategory?) {
        if (category != null) {
            _selectedCategory.value = category
        }
    }

    fun total(expenses: List<Expense>): Double {
        return calculateTotal(expenses)
    }

    val filteredExpenses: StateFlow<List<Expense>> =
        combine(_expenses, _selectedCategory) { expense, cateogry ->
            filterExpenses(expense, cateogry)
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    // Business Logic
    fun calculateTotal(expenses: List<Expense>): Double {
        return expenses.sumOf { it.amount }
    }

    fun filterExpenses(
        expenses: List<Expense>,
        category: ExpenseCategory
    ): List<Expense> {
        if (category == ExpenseCategory.ALL) {
           return expenses
        }
        return expenses.filter { expense ->
            expense.category == category
        }
    }
}