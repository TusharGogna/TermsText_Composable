package com.mdoc.tnc_compose

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import android.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import kotlin.concurrent.thread
import androidx.compose.foundation.text.ClickableText

/**
 * @Author: Tushar Gogna
 * @Date: 2023.09.29
 */

/**
 * @param stringArray is an arraylist which will pick 4 String params used to set the actual text.
 * The texts on Index[0] & Index[2] will be non clickable where as
 * the texts on Index[1] & Index[3] will be clickable and can only open URLs on Browsers.
 * @param termsURL is a String which will take in the full URL of Terms and Condition page ideally.
 * @param policyURL is a String which will take in the full URL of Privacy Policy page ideally.
 * @exception IllegalArgumentException is thrown if the stringArray size is less than 4.
 */
@Composable
fun TermsText(
    stringArray: ArrayList<String> = arrayListOf(
        "I agree to the ",
        "Terms and Conditions",
        " & ",
        "Privacy Policy"
    ),
    termsURL: String?,
    policyURL: String?
) {
    if (stringArray.size < 4) {
        thread(name = "WatchdogThread") {
            throw IllegalArgumentException("The Length of stringArray should be 4 where index values 1 and 3 are clickable.")
        }
        return
    }
    val annotatedString = buildAnnotatedString {
        val text = stringArray.joinToString(" ")
        append(text)
        val startT = text.indexOf(stringArray[1])
        val endT = startT + stringArray[1].length
        val startP = text.indexOf(stringArray[3])
        val endP = startP + stringArray[3].length
        addStyle(
            SpanStyle(
                color = androidx.compose.ui.graphics.Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            startT, endT
        )
        addStyle(
            SpanStyle(
                color = androidx.compose.ui.graphics.Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            startP, endP
        )
        termsURL?.let { addStringAnnotation("termsURL", it, startT, endT) }
        policyURL?.let { addStringAnnotation("policyURL", it, startP, endP) }
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(text = annotatedString) { offset ->
        val tUri = annotatedString.getStringAnnotations("termsURL", offset, offset)
            .firstOrNull()?.item
        val pUri = annotatedString.getStringAnnotations("policyURL", offset, offset)
            .firstOrNull()?.item
        if (tUri != null)
            uriHandler.openUri(tUri)
        else if (pUri != null)
            uriHandler.openUri(pUri)
    }
}


/**
 * @param textSize is used to set the size of the TextView. Default value is set to 16F
 * @param textColor is used to set the color to the non-clickable text.
 * @param textLinkColor is used to set the color to the clickable text.
 * @param list is an arraylist which will pick 4 String params used to set the actual text.
 * The texts on Index[0] & Index[2] will be non clickable where as
 * the texts on Index[1] & Index[3] will be clickable and can only open URLs on Browsers.
 * @param termsURL is a String which will take in the full URL of Terms and Condition page ideally.
 * @param privacyURL is a String which will take in the full URL of Privacy Policy page ideally.
 * @param context is simply the context needed to start the intent.
 */
@Composable
@JvmName("TermsTextDeprecated")
private fun TermsText(
    textSize: Float? = 16F,
    textColor: Int? = Color.BLACK,
    textLinkColor: Int? = Color.BLUE,
    list: ArrayList<String> = arrayListOf(
        "I agree to the ",
        "Terms and Conditions",
        " & ",
        "Privacy Policy"
    ),
    termsURL: String?,
    privacyURL: String?,
    context: Context?
) {
    AndroidView(
        factory = { _ ->
            val textView = TextView(context)
            textView.textSize = textSize!!
            textView

        },
        update = { textView ->
            customTextView(textView, textColor, textLinkColor, list, termsURL, privacyURL, context)
        }
    )
}


private fun customTextView(
    view: TextView,
    normalColor: Int?,
    clickColor: Int?,
    list: ArrayList<String>,
    termsURL: String?,
    privacyURL: String?,
    context: Context?
) {
    val spanTxt = SpannableStringBuilder(
        list[0]
    )
    spanTxt.setSpan(normalColor?.let { ForegroundColorSpan(it) }, 0, spanTxt.length, 0)
    spanTxt.append(list[1])
    spanTxt.setSpan(object : ClickableSpan() {
        override fun onClick(widget: View) {
            val urlT = if (!termsURL?.startsWith("http://")!! && !termsURL.startsWith("https://")) {
                "http://$termsURL"
            } else {
                termsURL
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlT))
            context?.startActivity(browserIntent)
        }
    }, spanTxt.length - list[1].length, spanTxt.length, 0)
    spanTxt.setSpan(
        clickColor?.let { ForegroundColorSpan(it) },
        spanTxt.length - list[1].length,
        spanTxt.length,
        0
    )
    spanTxt.append(list[2])
    spanTxt.setSpan(
        normalColor?.let { ForegroundColorSpan(it) },
        spanTxt.length - list[2].length,
        spanTxt.length,
        0
    )
    spanTxt.append(list[3])
    spanTxt.setSpan(object : ClickableSpan() {
        override fun onClick(widget: View) {
            val urlP =
                if (!privacyURL?.startsWith("http://")!! && !privacyURL.startsWith("https://")) {
                    "http://$privacyURL"
                } else {
                    privacyURL
                }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlP))
            context?.startActivity(browserIntent)
        }
    }, spanTxt.length - list[3].length, spanTxt.length, 0)
    spanTxt.setSpan(
        clickColor?.let { ForegroundColorSpan(it) },
        spanTxt.length - list[3].length,
        spanTxt.length,
        0
    )

    view.movementMethod = LinkMovementMethod.getInstance()
    view.setText(spanTxt, TextView.BufferType.SPANNABLE)
}