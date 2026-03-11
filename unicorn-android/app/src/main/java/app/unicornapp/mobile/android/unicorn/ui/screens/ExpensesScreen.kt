package app.unicornapp.mobile.android.unicorn.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.unicornapp.mobile.android.unicorn.model.Expense
import app.unicornapp.mobile.android.unicorn.model.ExpenseCategory
import app.unicornapp.mobile.android.unicorn.viewmodel.ExpensesViewModel

@Composable
fun ExpensesScreenContent(
    viewModel: ExpensesViewModel
) {
    val filteredExpenses by viewModel.filteredExpenses.collectAsState()

    Column(

    ) {
        CategoryFilter { category ->
            viewModel.selectCategory(category)
        }
        Text("Total: ${viewModel.total(filteredExpenses)}")
        LazyColumn(

        ) {
            items(filteredExpenses) { expense ->
                ExpenseRow(expense)
            }
        }
    }
}




// ExpenseRow
@Composable
fun ExpenseRow(
    expense: Expense
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column() {
            Text(expense.title)
            Text(expense.amount.toString())
        }
    }
}

// CategoryFilter
@OptIn(ExperimentalStdlibApi::class)
@Composable
fun CategoryFilter(
    onCategorySelected: (ExpenseCategory?) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = {onCategorySelected(null)}) {
           Text("All")
        }
        ExpenseCategory.entries.forEach { expenseCategory ->
            Button(
                onClick = {onCategorySelected(expenseCategory)}
            ) {
                Text(expenseCategory.name)
            }
        }
    }
}