package by.budanitskaya.l.quilixtest.presentation.ui.currencyinfo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.databinding.FragmentCurrencyInfoBinding
import by.budanitskaya.l.quilixtest.presentation.models.CurrencyPresentationModel
import by.budanitskaya.l.quilixtest.presentation.ui.MainActivity
import by.budanitskaya.l.quilixtest.presentation.ui.currencyinfo.adapters.CurrencyInfoAdapter
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
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun setUpRecyclerView(
        list: List<CurrencyPresentationModel>,
        dates: Pair<String, String>
    ) {
        val adapter = CurrencyInfoAdapter(dates, list)
        binding.recyclerCurrencyInfoList.adapter = adapter
        binding.recyclerCurrencyInfoList.visibility = View.VISIBLE
    }

    private fun initObservers() {
        viewModel.currencyDataList.observe(viewLifecycleOwner, {
            setUpRecyclerView(it, viewModel.getDates() ?: Pair("", ""))
        })

        viewModel.isMenuVisible.observe(viewLifecycleOwner, {
            setHasOptionsMenu(false)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_settings -> {
                (activity as MainActivity).navController
                    .navigate(R.id.action_currencyInfoFragment_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}