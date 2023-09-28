package com.mdoc.termsandconditiondemo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mdoc.termsandconditiondemo.ui.theme.TermsAndConditionDemoTheme
import com.mdoc.tnc_compose.TermsText


class MainActivity : ComponentActivity() {
    private val termsArray =
        arrayListOf("I agree to the ", "Terms and Conditions", " & ", "Privacy Policies")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TermsAndConditionDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TermsText(
                        24F,
                        Color.BLACK,
                        Color.BLUE,
                        termsArray,
                        "https://stackoverflow.com/",
                        "https://google.com/",
                        LocalContext.current
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsPreview() {
    val termsArray =
        arrayListOf("I agree to the ", "Terms and Conditions", " & ", "Privacy Policies")

    TermsAndConditionDemoTheme {
        TermsText(
            24F,
            Color.BLACK,
            Color.BLUE,
            termsArray,
            "https://stackoverflow.com/",
            "https://google.com/",
            LocalContext.current
        )
    }
}