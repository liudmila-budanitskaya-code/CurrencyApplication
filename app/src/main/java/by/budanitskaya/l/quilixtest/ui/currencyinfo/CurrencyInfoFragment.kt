package by.budanitskaya.l.quilixtest.ui.currencyinfo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.databinding.FragmentCurrencyInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyInfoFragment : Fragment() {

    private val viewModel: CurrencyInfoViewModel by lazy {
        ViewModelProvider(this).get(
            CurrencyInfoViewModel::class.java
        )
    }
    private lateinit var binding: FragmentCurrencyInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_currency_info, container, false
        )

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.message.observe(viewLifecycleOwner, {
            binding.textViewData.text = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}