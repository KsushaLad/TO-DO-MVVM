package com.example.todoapp.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentAddBinding
import com.example.todoapp.fragments.SharedViewModel

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener //прослушиватель выбранного элемента
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() { //вставка в БД
        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()
        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if(validation){
            val newData = ToDoData( //вставка данных в БД
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription)
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment) //обратная навигация
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}