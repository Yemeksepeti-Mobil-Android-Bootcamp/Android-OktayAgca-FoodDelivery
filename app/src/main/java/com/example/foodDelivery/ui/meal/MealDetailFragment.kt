package com.example.foodDelivery.ui.meal

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodDelivery.data.entity.meal.Meal
import com.example.foodDelivery.databinding.FragmentMealDetailBinding
import com.example.foodDelivery.utils.Resource
import com.example.foodDelivery.utils.gone
import com.example.foodDelivery.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    private val viewModel: MealDetailViewModel by viewModels()
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var mealId: String
    private var adapter: IngredientsRecyclerViewAdapter = IngredientsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        initViews()
        setOnClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMeal()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter =null
        _binding = null
    }


    private fun setData(ingredients: ArrayList<String>) {
        adapter.setIngredientList(ingredients)
    }

    private fun initViews() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            mealIngredientTextView.gone()
            cardView.gone()
            orderButton.gone()
            progressBar.show()

        }
    }
    private fun setOnClickListeners() {
        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            orderButton.setOnClickListener {
                createOrder()
            }
        }
    }
    private fun getMeal() {
        viewModel.getMeal().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                    val meal = it.data!!.data
                    initViewsWithData(meal)
                    setData(meal.ingredients)
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("${it.message}")
                        .setPositiveButton("ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                    dialog.show()
                }
            }
        })
    }

    private fun createOrder() {
        viewModel.postOrder(mealId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Success")
                        .setMessage("Your order has been created")
                        .setPositiveButton("ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                    dialog.show()
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("${it.message}")
                        .setPositiveButton("ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                    dialog.show()
                }
            }
        })
    }

    private fun initViewsWithData(meal: Meal) {
        mealId = meal.id
        viewModel.meal = meal
        binding.apply {
            mealIngredientTextView.show()
            cardView.show()
            orderButton.show()
            progressBar.gone()
            Glide.with(imageView.context)
                .load(meal.image).into(imageView)
            mealNameTextView.text = meal.name
            val description = "Meal Description"
            mealDescriptionTextView.text = description
            mealPriceTextView.text = meal.price
        }
    }
}