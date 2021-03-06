package com.example.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentUpdateBinding
import com.example.todoapp.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val sharedViewModel: SharedViewModel by viewModels()
    private val toDoViewModel: ToDoViewModel by viewModels()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding?.args = args
        setHasOptionsMenu(true)
        binding?.currentPrioritiesSpinner?.onItemSelectedListener = sharedViewModel.listener
        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() { //обновление Item
        val title = binding?.currentTitleEt?.text.toString()
        val description = binding?.currentDescriptionEt?.text.toString()
        val getPriority = binding?.currentPrioritiesSpinner?.selectedItem.toString()
        val validation = sharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val updatedItem = ToDoData(args.currentItem.id, title, sharedViewModel.parsePriority(getPriority), description)
            toDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), R.string.successfully_updated, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), R.string.please_fill_out_all_fields, Toast.LENGTH_SHORT).show()
        }
    }


    private fun confirmItemRemoval() { //показать AlertDialog для подтверждения удаления всех элементов из таблицы базы данных
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(R.string.yes) { _, _ ->
            toDoViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(), "Успешно удалено: ${args.currentItem.title}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(R.string.no) { _, _ -> }
        builder.setTitle("Удалить '${args.currentItem.title}'?")
        builder.setMessage("Вы уверены, что хотите удалить '${args.currentItem.title}'?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}