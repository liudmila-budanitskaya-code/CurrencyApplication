package by.budanitskaya.l.quilixtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initObservers()
    }

    private fun initObservers() {
        viewModel.data.observe(this, {
            val textView = findViewById<TextView>(R.id.text_view_data)
            textView.text = it.listCurrencyInfo?.get(0)?.name
        })
    }
}