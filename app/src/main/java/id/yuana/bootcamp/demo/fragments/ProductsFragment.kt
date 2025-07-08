package id.yuana.bootcamp.demo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.yuana.bootcamp.demo.adapters.ProductAdapter
import id.yuana.bootcamp.demo.data.source.Module
import id.yuana.bootcamp.demo.databinding.FragmentProductsBinding
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val productApi = Module.productApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            runCatching { productApi.getObjects() }
                .onSuccess {
                    val productNames = it.map { product -> product.name }.toTypedArray()
                    val productAdapter = ProductAdapter(productNames)

                    binding.recyclerViewProducts.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = productAdapter
                    }

                }
                .onFailure {
                    //Todo: error handling
                    Log.e("ProductsFragment", "Error fetching products: ${it.message}")
                }
        }

    }
}