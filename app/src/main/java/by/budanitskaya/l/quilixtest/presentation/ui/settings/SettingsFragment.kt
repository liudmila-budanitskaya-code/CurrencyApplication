package by.budanitskaya.l.quilixtest.presentation.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.databinding.FragmentSettingsBinding
import by.budanitskaya.l.quilixtest.presentation.ui.settings.adapters.SettingsAdapter
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProvider(this).get(
            SettingsViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings, container, false
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val list = settingsRepository.getSettingsInfo()
        Log.d("setUpRecyclerView", "setUpRecyclerView: ${list}")
        val adapter = SettingsAdapter(settingsRepository, list)
        binding.recyclerSettingsInfoList.adapter = adapter
        binding.recyclerSettingsInfoList.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ok, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_ok -> {
                settingsRepository.saveTemporaryChanges()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}