package by.budanitskaya.l.quilixtest.ui.currencyinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.quilixtest.databinding.CurrencyInfoItemBinding
import by.budanitskaya.l.quilixtest.network.models.CurrencyInfo

class CurrencyInfoAdapter(

    private val currencyData: List<CurrencyInfo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CurrencyInfoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrencyInfoViewHolder(binding, currencyData)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CurrencyInfoViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = currencyData.size


    class CurrencyInfoViewHolder(
        private val binding: CurrencyInfoItemBinding,
        private val currencyData: List<CurrencyInfo>
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind() {

        }
    }

}