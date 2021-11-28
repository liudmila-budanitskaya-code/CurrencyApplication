package by.budanitskaya.l.quilixtest.presentation.ui.currencyinfo

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.budanitskaya.l.quilixtest.ConnectivityReceiver
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.databinding.FragmentCurrencyInfoBinding
import by.budanitskaya.l.quilixtest.presentation.models.CurrencyPresentationModel
import by.budanitskaya.l.quilixtest.presentation.ui.MainActivity
import by.budanitskaya.l.quilixtest.presentation.ui.currencyinfo.adapters.CurrencyInfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyInfoFragment : Fragment(), ConnectivityReceiver.ConnectivityReceiverListener {

    private val viewModel: CurrencyInfoViewModel by lazy {
        ViewModelProvider(this).get(
            CurrencyInfoViewModel::class.java
        )
    }

    private lateinit var binding: FragmentCurrencyInfoBinding

    private val connectivityreceiver by lazy {
        ConnectivityReceiver()
    }

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
        activity?.registerReceiver(connectivityreceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        ConnectivityReceiver.connectivityReceiverListener = this
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

        viewModel.isError.observe(viewLifecycleOwner, {
            setHasOptionsMenu(!it)
            if(it == false){
                binding.recyclerCurrencyInfoList.visibility = View.VISIBLE
            } else {
                binding.recyclerCurrencyInfoList.visibility = View.GONE
            }
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

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        viewModel.fetchData()
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(connectivityreceiver)
    }
}