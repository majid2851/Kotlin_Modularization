package com.majid2851.kotlin_modularization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent 
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.kotlin_modularization.ui.theme.DotaInfoTheme 

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            DotaInfoTheme() 
            {
                
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DotaInfoTheme() {
        Greeting("Android")
    }
}