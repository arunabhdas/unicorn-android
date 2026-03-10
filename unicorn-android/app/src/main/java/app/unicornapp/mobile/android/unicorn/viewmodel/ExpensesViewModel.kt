package app.unicornapp.mobile.android.unicorn.viewmodel

import app.unicornapp.mobile.android.unicorn.model.Expense
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExpensesViewModel: ViewModel() {
    val _expenses = MutableStateFlow (
        listOf(
            Expense("1", "Coffee", 1.25, 123, "Discretionary"),
            Expense("2", "Coffee", 1.25, 123, "Discretionary"),
        )
    )

    val expenses: StateFlow<List<Expense>> = _expenses

    private val _selectedCategory = MutableStateFlow("All")


}