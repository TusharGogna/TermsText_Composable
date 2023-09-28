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

/**
 * @Author: Tushar Gogna
 * @Date: 2023.09.28
 */

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
fun TermsText(
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