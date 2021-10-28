package by.budanitskaya.l.quilixtest.ui.currencyinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.quilixtest.databinding.CurrencyInfoItemBinding
import by.budanitskaya.l.quilixtest.databinding.HeaderItemBinding
import by.budanitskaya.l.quilixtest.network.models.CurrencyInfo

class CurrencyInfoAdapter(

    private val currencyData: List<CurrencyInfo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding = HeaderItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = CurrencyInfoItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                CurrencyInfoViewHolder(binding, currencyData)
            }
        }
    }

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CurrencyInfoViewHolder -> holder.bind()
            is HeaderViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = currencyData.size


    class CurrencyInfoViewHolder(
        private val binding: CurrencyInfoItemBinding,
        private val currencyData: List<CurrencyInfo>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.textViewCharCode.text = currencyData[adapterPosition].charCode
            val scale = currencyData[adapterPosition].scale

            val name = currencyData[adapterPosition].name
            binding.textViewNumCodeName.text = "$scale\n$name"
            val yesterDayCurrencyInfo = currencyData[adapterPosition].rate
            binding.textViewYesterdayRate.text = yesterDayCurrencyInfo.toString()
            binding.textViewTodayRate.text = yesterDayCurrencyInfo.toString()
        }
    }

    class HeaderViewHolder(
        binding: HeaderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            // do nothing
        }
    }

}