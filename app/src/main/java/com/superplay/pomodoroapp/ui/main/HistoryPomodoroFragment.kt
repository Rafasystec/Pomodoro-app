package com.superplay.pomodoroapp.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.superplay.pomodoroapp.R
import com.superplay.pomodoroapp.adapters.PomodoroHistoryAdapter
import com.superplay.pomodoroapp.databinding.HistoryPomodoroFragmentBinding
import com.superplay.pomodoroapp.extentions.defaultRecycleView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryPomodoroFragment : Fragment() {

    var rcHistoryList       : RecyclerView? = null
    private val viewModel   : HistoryPomodoroViewModel by viewModel()
    private var _binding    : HistoryPomodoroFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = HistoryPomodoroFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HistoryPomodoroFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRecycleView()
        viewModel.getPomodoros()
    }

    private fun initObservers(){
        viewModel.pomodoroListLiveData.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                binding.tvNoHistory.visibility = View.GONE
                rcHistoryList?.adapter = PomodoroHistoryAdapter(it) {
                }
            }else{
                binding.tvNoHistory.visibility = View.VISIBLE
            }
        })
    }

    private fun initRecycleView(){
        rcHistoryList = defaultRecycleView(requireActivity(),R.id.rcHistoryList)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPomodoros()
    }

}