package com.rcl.jetchanger.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rcl.jetchanger.di.retrofit.models.CurrencyModel
import com.rcl.jetchanger.di.retrofit.models.CurrencyPair

@Composable
fun MainConvertScreen() {
    val viewModel = viewModel { MainComponentVM() }
    val list = viewModel.CurrenciesList
    val selectedPair by viewModel.selectedPairs
    val inputValue by viewModel.inputValue
    val outputValue by viewModel.outputValue

    var hidden by remember { mutableStateOf(true) }
    var which by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inputValue,
            onValueChange = viewModel::updateInputValue,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Card(modifier = Modifier.clickable {
            which = 0
            hidden = false
        }) {
            Text(
                text = selectedPair.first, modifier = Modifier.padding(10.dp)
            )
        }
        Card(modifier = Modifier.clickable {
            which = 1
            hidden = false
        }) {
            Text(
                text = selectedPair.second, modifier = Modifier.padding(10.dp)
            )
        }
        TextButton(
            onClick = viewModel::calculate
        ) {
            Text("=")
        }

        Card {
            Text(
                text = outputValue,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    if (!hidden) {
        CurrencySelectionDialog(list = list, onDismiss = {
            hidden = true
        }, onCurrencySelected = {
            when (which) {
                0 -> viewModel.updatePair(
                    CurrencyPair(
                        first = it.key, second = selectedPair.second
                    )
                )

                1 -> viewModel.updatePair(
                    CurrencyPair(
                        first = selectedPair.first, second = it.key
                    )
                )
            }
        })
    }
}

@Composable
fun CurrencySelectionDialog(
    list: List<CurrencyModel>,
    onDismiss: () -> Unit,
    onCurrencySelected: (CurrencyModel) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val filteredList = remember { mutableStateListOf<CurrencyModel>() }

    LaunchedEffect(null) {
        filteredList.addAll(list)
    }

    AlertDialog(onDismissRequest = onDismiss, title = { Text(text = "Select Currency") }, text = {
        Column {
            OutlinedTextField(
                placeholder = { Text("Type..") },
                value = text, onValueChange = { it ->
                    text = it
                    if (it.isEmpty()) {
                        filteredList.clear()
                        filteredList.addAll(list)
                    } else {
                        filteredList.clear()
                        filteredList.addAll(list.filter {
                            it.key.contains(text, true) || it.fullName.contains(text, true)
                        })
                    }
                }, label = { Text("Search") }, modifier = Modifier.fillMaxWidth()
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                items(filteredList) { currency ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCurrencySelected(currency)
                            onDismiss()
                        }) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = currency.key, modifier = Modifier.padding(end = 10.dp)
                            )
                            Text(
                                text = currency.fullName,
                            )
                        }
                    }
                }
            }
        }
    }, confirmButton = {
        TextButton(
            onClick = onDismiss
        ) {
            Text("Close")
        }
    })
}