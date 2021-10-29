package by.budanitskaya.l.quilixtest.presentation.ui.settings

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import by.budanitskaya.l.quilixtest.databinding.FragmentSettingsBinding
import by.budanitskaya.l.quilixtest.presentation.ui.settings.adapters.SettingsAdapter
import by.budanitskaya.l.quilixtest.utils.CustomItemTounchCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private lateinit var binding: FragmentSettingsBinding

    private val itemTouchHelper by lazy {
        val itemTouchCallback = CustomItemTounchCallback()
        ItemTouchHelper(itemTouchCallback)
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
        val adapter = SettingsAdapter(settingsRepository)
        adapter.differ.submitList(list)
        with(binding){
            itemTouchHelper.attachToRecyclerView(recyclerSettingsInfoList)
            recyclerSettingsInfoList.adapter = adapter
            recyclerSettingsInfoList.layoutManager = LinearLayoutManager(requireContext())
            recyclerSettingsInfoList.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ok, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_ok -> {
                settingsRepository.persistTemporaryChanges()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        super.onStop()
        settingsRepository.clearTemporaryStorage()
    }
}