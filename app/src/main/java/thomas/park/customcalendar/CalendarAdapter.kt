package thomas.park.customcalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var item = ArrayList<CalendarItem>()

    private var mWidth = 0
    private var mHeight = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calendar, parent, false)

        return CalendarViewHolder(itemView)
    }

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        if(mWidth != 0 && mHeight != 0) {
            holder.dayCardView.layoutParams.apply {
                width = mWidth
                height = mHeight
            }
        }

        val textColor = when(position % 7) {
            0 -> {
                R.color.sunday
            }
            6 -> {
                R.color.saturday
            }
            else -> {
                android.R.color.black
            }
        }
        item[position].apply {
            holder.scheduleDot.apply {
                if(!hasSchedule) visibility = View.GONE
            }
            holder.dayTxt.apply{
                setTextColor(ContextCompat.getColor(context, textColor))
                alpha = 1.0f
                text = day
                if(!isThisMonth) {
                    alpha = 0.4f
                }

            }}
    }

    fun update(item: ArrayList<CalendarItem>) {
        this.item.addAll(item)
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dayCardView = itemView.findViewById(R.id.dayCardView) as ConstraintLayout
        val dayTxt = itemView.findViewById(R.id.dayTxt) as AppCompatTextView
        val scheduleDot = itemView.findViewById(R.id.dot) as View
    }
}