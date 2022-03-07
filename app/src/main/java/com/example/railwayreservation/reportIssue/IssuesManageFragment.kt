package com.example.railwayreservation.reportIssue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentIssuesManageBinding
import com.example.railwayreservation.reportIssue.updates.ParcelizedIssueData
import com.google.firebase.database.*

class IssuesManageFragment : Fragment(), IssueAdapter.OnItemClick {

    private var _binding: FragmentIssuesManageBinding? = null
    private val binding get() = _binding!!
    private lateinit var issueRecycleView: RecyclerView
    private lateinit var issueArrayList: ArrayList<IssuesData>
    private lateinit var issueDatabase: DatabaseReference
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIssuesManageBinding.inflate(inflater, container, false)

        insertIssueCategory()

        issueRecycleView = binding.displayIssuesRecyclerView
        issueRecycleView.layoutManager = LinearLayoutManager(requireContext())

        issueArrayList = arrayListOf()

        binding.searchCategory.setOnClickListener {
            val selectCategory = binding.textFieldSearchCategory.text.toString()
            fetchIssueData(selectCategory)
        }

        binding.clearRecyclerCategory.setOnClickListener {
            try {
                if (issueRecycleView.isShown) {
                    issueArrayList.clear()
                    issueRecycleView.adapter?.notifyDataSetChanged()
                }
            }catch (e: Exception) {
                e.message
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.issueManageMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_issuesManageFragment_to_adminMainFragment)
        }
    }

    private fun insertIssueCategory() {
        val lists = resources.getStringArray(R.array.report_category_items)

        val listAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldSearchCategory.setAdapter(listAdapter)
    }

    private fun fetchIssueData(category: String) {
        issueDatabase = FirebaseDatabase.getInstance().getReference("Issues").child(category)
        issueDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issueSnapshot in snapshot.children) {
                        val issue = issueSnapshot.getValue(IssuesData::class.java)
                        issueArrayList.add(issue!!)
                    }
                    issueRecycleView.adapter = IssueAdapter(issueArrayList, this@IssuesManageFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onItemClick(data: IssuesData) {
        val issueCategory = data.issueCategory
        val issueDesc = data.issueDescription
        val issueResolve = data.issueResolve
        val trainId = data.trainId
        val trainCoachPick = data.coachPick
        val issueDate = data.issueDate
        val issueTime = data.issueTime

        val pIssueData = ParcelizedIssueData(
            issueCategory, issueDesc, issueResolve, trainId, trainCoachPick, issueDate, issueTime
        )
        val action = IssuesManageFragmentDirections.actionIssuesManageFragmentToGetUpdatesFragment(pIssueData)
        findNavController().navigate(action)
    }
}