# TermsText_Composable
TermsText_Composable is a small lib which will save you time when you want to implement Terms and Condition + Privacy Policy text in Compose.

# How to use:
In order to consume the library, you simply need to call the Composable "TermsText" which takes the following parameters:
 * @param list is an arraylist which will pick 4 String params used to set the actual text.
 * The texts on Index[0] & Index[2] will be non clickable where as
 * the texts on Index[1] & Index[3] will be clickable and can only open URLs on Browsers.
 * @param termsURL is a String which will take in the full URL of Terms and Condition page ideally.
 * @param privacyURL is a String which will take in the full URL of Privacy Policy page ideally.

The ArrayList should look something like:
arrayListOf("I agree to the ", "Terms and Conditions", " & ",  "Privacy Policy") 

If the list contains less than 4 items then IllegalArgumentException will be thrown.

# How it will appear:
It should look something like this:
![Screenshot_20230928_171844](https://github.com/TusharGogna/TermsText_Composable/assets/36148180/be577e4c-bb66-4759-bcd4-dd2d878c677c)

Happy Coding! 
