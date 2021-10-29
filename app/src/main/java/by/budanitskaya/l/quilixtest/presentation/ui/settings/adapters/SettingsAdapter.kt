package by.budanitskaya.l.quilixtest.presentation.ui.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.quilixtest.databinding.HeaderItemBinding
import by.budanitskaya.l.quilixtest.databinding.SettingsItemBinding
import by.budanitskaya.l.quilixtest.presentation.models.SettingsModel
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository


class SettingsAdapter(
    private val settingsRepository: SettingsRepository
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
                SettingsViewHolder(binding, settingsRepository)
            }
        }
    }

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item= differ.currentList[position]

        when (holder) {
            is SettingsViewHolder -> holder.bind(item)
            is SettingHeaderViewHolder -> holder.bind()
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((SettingsModel) -> Unit)? = null

    inner class SettingsViewHolder(
        private val binding: SettingsItemBinding,
        private val settingsRepository: SettingsRepository
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SettingsModel) {

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item)

                }
            }
            binding.textViewCurrencyName.text = item.name
            binding.textViewChars.text = item.charCode

            binding.switchSettings.setOnCheckedChangeListener { buttonView, isChecked ->
                settingsRepository.tempSave(item.charCode, isChecked)
            }
            binding.switchSettings.isChecked =
                settingsRepository.tempApplyTemporaryChanges(item).isOn
        }
    }

    class SettingHeaderViewHolder(
        binding: HeaderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            // do nothing
        }
    }

    private val differCallBack  = object : DiffUtil.ItemCallback<SettingsModel>()
    {

        override fun areItemsTheSame(oldItem: SettingsModel, newItem: SettingsModel): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SettingsModel, newItem: SettingsModel): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallBack)

    fun moveItem(from: Int, to: Int) {

        val list = differ.currentList.toMutableList()
        val fromLocation = list[from]
        list.removeAt(from)
        if (to < from) {
            list.add(to + 1 , fromLocation)
        } else {
            list.add(to - 1, fromLocation)
        }
        differ.submitList(list)


    }
}