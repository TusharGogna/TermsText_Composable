package com.mdoc.termsandconditiondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mdoc.termsandconditiondemo.ui.theme.TermsAndConditionDemoTheme
import com.mdoc.tnc_compose.TermsText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TermsAndConditionDemoTheme {
                TermsText(
                    termsURL = "https://www.stackoverflow.com",
                    policyURL = "https://www.google.com"
                )
            }
        }
    }

}
