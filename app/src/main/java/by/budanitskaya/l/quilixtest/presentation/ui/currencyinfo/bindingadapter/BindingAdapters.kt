package by.budanitskaya.l.quilixtest.presentation.ui.currencyinfo.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import by.budanitskaya.l.quilixtest.R


@BindingAdapter("error_message")
fun setErrorMessage(textView: TextView, isError: Boolean) {
    if (isError) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.currency_info_error_message)
    } else {
        textView.visibility = View.INVISIBLE
    }
}