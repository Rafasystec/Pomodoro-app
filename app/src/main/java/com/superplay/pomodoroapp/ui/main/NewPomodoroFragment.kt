package com.superplay.pomodoroapp.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.superplay.pomodoroapp.R
import com.superplay.pomodoroapp.databinding.NewPomodoroFragmentBinding
import com.superplay.pomodoroapp.services.AlarmService
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPomodoroFragment : Fragment() {

    private var _binding: NewPomodoroFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NewPomodoroFragment()
    }

    private val viewModel : NewPomodoroViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewPomodoroFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.fabStartTime.setOnClickListener {
//            requireActivity().startService(Intent(requireContext(),AlarmService::class.java))
            if(viewModel.canStop.value == null || !viewModel.canStop.value!!) {
                viewModel.startCountDown(requireActivity())
            }else{
                viewModel.stopCountDown()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers(){
        viewModel.count.observe(viewLifecycleOwner, Observer {timer ->
            binding.tvCountDown.text = timer
        })

        viewModel.canStop.observe(viewLifecycleOwner, Observer {canStop ->
            if(canStop){
                binding.fabStartTime.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_stop))
                binding.tvCountDown.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
            }else{
                binding.fabStartTime.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_play))
                binding.tvCountDown.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
//                requireActivity().stopService(Intent(requireContext(),AlarmService::class.java))
                binding.tvCountDown.text = getString(R.string._25_00)
            }
        })

    }



}