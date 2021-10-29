package by.budanitskaya.l.quilixtest.presentation.ui.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import by.budanitskaya.l.quilixtest.databinding.HeaderItemBinding
import by.budanitskaya.l.quilixtest.databinding.SettingsItemBinding
import by.budanitskaya.l.quilixtest.presentation.models.SettingsModel


class SettingsAdapter(
    private val settingsRepository: SettingsRepository
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<SettingsModel>() {

        override fun areItemsTheSame(oldItem: SettingsModel, newItem: SettingsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SettingsModel, newItem: SettingsModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

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
                SettingsViewHolder(binding, settingsRepository)
            }
        }
    }

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        when (holder) {
            is SettingsViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((SettingsModel) -> Unit)? = null

    inner class SettingsViewHolder(
        private val binding: SettingsItemBinding,
        private val settingsRepository: SettingsRepository
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SettingsModel) {
            with(binding) {
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
                textViewCurrencyName.text = item.name
                textViewChars.text = item.charCode

                switchSettings.setOnCheckedChangeListener { buttonView, isChecked ->
                    settingsRepository.temporarilySave(item.charCode, isChecked)
                }
                switchSettings.isChecked =
                    settingsRepository.tempApplyTemporaryChanges(item).isOn
            }
        }
    }

    class SettingHeaderViewHolder(
        binding: HeaderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    fun moveItem(from: Int, to: Int) {
        val list = differ.currentList.toMutableList()
        val fromLocation = list[from]
        list.removeAt(from)
        if (to < from) {
            list.add(to, fromLocation)
        } else {
            list.add(to, fromLocation)
        }
        differ.submitList(list)
    }
}