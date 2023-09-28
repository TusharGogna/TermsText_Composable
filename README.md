# TermsText_Composable
TermsText_Composable is a small lib which will save you time when you want to implement Terms and Condition + Privacy Policy text in Compose.

# How to use:
In order to consume the library, you simply need to call the Composable "TermsText" which takes the following parameters:
 * @param textSize is used to set the size of the TextView. Default value is set to 16F
 * @param textColor is used to set the color to the non-clickable text.
 * @param textLinkColor is used to set the color to the clickable text.
 * @param list is an arraylist which will pick 4 String params used to set the actual text.
 * The texts on Index[0] & Index[2] will be non clickable where as
 * the texts on Index[1] & Index[3] will be clickable and can only open URLs on Browsers.
 * @param termsURL is a String which will take in the full URL of Terms and Condition page ideally.
 * @param privacyURL is a String which will take in the full URL of Privacy Policy page ideally.
 * @param context is simply the context needed to start the intent.

The ArrayList should look something like:
arrayListOf("I agree to the ", "Terms and Conditions", " & ",  "Privacy Policy")

Happy Coding! 
