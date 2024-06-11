package com.kongtaoxing.scanner.ui.dashboard

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.text.set
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.kongtaoxing.scanner.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.   textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.scanButton.setOnClickListener {
            IntentIntegrator.forSupportFragment(this).initiateScan()
        }

        binding.scanResult.setOnLongClickListener {
            val clipboard = ContextCompat.getSystemService(requireContext(), ClipboardManager::class.java)
            val clip = ClipData.newPlainText("Scan Result", binding.scanResult.text)
            clipboard?.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "结果已复制到剪贴板", Toast.LENGTH_SHORT).show()
            true
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                binding.scanResult.text = result.contents
            } else {
                Toast.makeText(requireContext(), "扫描取消", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}