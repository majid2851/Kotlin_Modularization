package com.majid2851.ui_herolist.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyRow(){
    Row(
        modifier = Modifier
            .padding(bottom = 0.dp)
            .fillMaxWidth()
        ,
    ){
        Text(
            text = "",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
