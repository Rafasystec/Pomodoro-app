package com.superplay.pomodoroapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.superplay.pomodoroapp.R
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import com.superplay.pomodoroapp.util.DateTimeUtil
import java.util.*


class PomodoroHistoryAdapter(
    val historyList:List<PomodoroDTO>,
    val onItemClick: (PomodoroDTO) -> Unit
)  : RecyclerView.Adapter<PomodoroHistoryAdapter.PomodoroHistoryHolder>() {

    class PomodoroHistoryHolder(view: View) : RecyclerView.ViewHolder(view){
        val totalTime   = view.findViewById<TextView>(R.id.tvTotalTime)
        val status      = view.findViewById<TextView>(R.id.tvStatusPomodor)
        val timePassed  = view.findViewById<TextView>(R.id.tvTimePassed)
        val titleHeader = view.findViewById<TextView>(R.id.tvTitleGroup)
        val layout      = view.findViewById<ConstraintLayout>(R.id.clHistory)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PomodoroHistoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_adapter_history,parent,false)
        return PomodoroHistoryHolder(view)
    }

    override fun getItemCount() = historyList.size

    override fun onBindViewHolder(holder: PomodoroHistoryHolder, position: Int) {
        val historyItem = historyList[position]

        with(holder){
            totalTime.text  = DateTimeUtil.getFormattedTime(historyItem.initialTime,historyItem.finalTime)
            status.text     = if(historyItem.finalTime == 0L) "fineshed" else "stoped"
            timePassed.text = DateTimeUtil.getDatePassedDescription(historyItem.date, DateTimeUtil.now())
            itemView.setOnClickListener { onItemClick(historyItem) }
        }
        if(historyItem.isTheFirst){
            holder.titleHeader.visibility = View.VISIBLE
            holder.titleHeader.text = historyItem.day?.description
            holder.layout.minHeight = 80
        }else{
            holder.titleHeader.visibility = View.GONE
        }

    }


}