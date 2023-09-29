package com.mdoc.termsandconditiondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mdoc.termsandconditiondemo.ui.theme.TermsAndConditionDemoTheme
import com.mdoc.tnc_compose.TermsText

class MainActivity : ComponentActivity() {
    private val termsArray =
        arrayListOf("I agree to the", "Terms and Conditions", "&", "Privacy Policies")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TermsAndConditionDemoTheme {
                TermsText(
                    stringArray = termsArray,
                    termsURL = "https://www.stackoverflow.com",
                    policyURL = "https://www.google.com"
                )
            }
        }
    }

}
