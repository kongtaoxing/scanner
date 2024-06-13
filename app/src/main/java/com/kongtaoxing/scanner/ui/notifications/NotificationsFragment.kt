package com.kongtaoxing.scanner.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.kongtaoxing.scanner.R
import com.kongtaoxing.scanner.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val notificationsViewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel =
//                ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        notificationsViewModel.qrCodeBitmap.observe(viewLifecycleOwner, Observer { bitmap ->
            binding.qrCodeImage.setImageBitmap(bitmap)
        })

        binding.createButton.setOnClickListener {
            val inputText = binding.textInput.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show()
            } else {
                notificationsViewModel.generateQRCode(inputText)
            }
            hideKeyboard(it)
        }

        return root
    }

    private fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}