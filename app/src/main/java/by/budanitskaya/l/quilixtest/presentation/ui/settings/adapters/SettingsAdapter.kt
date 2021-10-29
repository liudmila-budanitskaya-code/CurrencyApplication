package by.budanitskaya.l.quilixtest.presentation.ui.settings.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.quilixtest.databinding.HeaderItemBinding
import by.budanitskaya.l.quilixtest.databinding.SettingsItemBinding
import by.budanitskaya.l.quilixtest.presentation.models.SettingsModel
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository


class SettingsAdapter(
    private val settingsRepository: SettingsRepository,
    private val settingsData: List<SettingsModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding = HeaderItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                SettingHeaderViewHolder(binding)
            }
            else -> {
                val binding = SettingsItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                SettingsViewHolder(binding, settingsData, settingsRepository)
            }
        }
    }

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is SettingsViewHolder -> holder.bind()
            is SettingHeaderViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = settingsData.size


    class SettingsViewHolder(
        private val binding: SettingsItemBinding,
        private val settingsData: List<SettingsModel>,
        private val settingsRepository: SettingsRepository
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.textViewCurrencyName.text = settingsData[adapterPosition].name
            binding.textViewChars.text = settingsData[adapterPosition].charCode
            binding.switchSettings.setOnCheckedChangeListener { buttonView, isChecked ->
                settingsRepository.setBoolean(settingsData[adapterPosition].charCode, isChecked)
            }
            Log.d("bind", "bind: ${settingsData[adapterPosition].isOn}")
            binding.switchSettings.isChecked = settingsData[adapterPosition].isOn
        }
    }

    class SettingHeaderViewHolder(
        binding: HeaderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            // do nothing
        }
    }
}