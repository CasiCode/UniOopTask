package com.oop_task.presentation

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.data.db.DatabaseClient
import com.oop_task.databinding.FragmentGameLogBinding
import com.oop_task.databinding.FragmentXmlBinding
import com.oop_task.engine.Fight
import com.oop_task.engine.IOEngine
import com.oop_task.engine.entities.MonsterEntity
import com.oop_task.engine.entities.PlayerEntity
import com.oop_task.values.NonNumericValues.XML_FILE_NAME
import kotlinx.coroutines.launch
import java.io.File

class XmlFragment: Fragment() {
    private val binding by viewBinding(FragmentXmlBinding::bind)
    private var navigationController: NavigationController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_xml, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController =
            (parentFragment as? NavigationController) ?: (activity as? NavigationController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val xml = File("${requireContext().filesDir}/xml/${XML_FILE_NAME}")
            binding.textview.text = xml.readText()
        } catch (e: Exception) {
            Log.d("XML_FRAGMENT", e.toString())
        }
        binding.textview.movementMethod = ScrollingMovementMethod()
    }

    companion object {
        @JvmStatic
        fun newInstance() = XmlFragment()
    }
}