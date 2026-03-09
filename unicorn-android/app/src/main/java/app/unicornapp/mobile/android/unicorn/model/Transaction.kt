package app.unicornapp.mobile.android.unicorn.model

import java.time.ZoneId

import java.time.format.DateTimeFormatter

data class Transaction(
    val id: String,
    val merchant: String,
    val amount: Double,
    val timestamp: Long
)

fun filterLargeTransactions(
    transactions: List<Transaction>,
    minAmount: Double
): List<Transaction> {
    return transactions.filter { it.amount > minAmount }
}