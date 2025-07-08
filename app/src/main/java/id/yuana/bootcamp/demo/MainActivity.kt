package id.yuana.bootcamp.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import id.yuana.bootcamp.demo.databinding.ActivityMainBinding
import id.yuana.bootcamp.demo.navigation.ProductsRoute
import id.yuana.bootcamp.demo.navigation.buildNavGraph

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            ?: throw IllegalStateException("NavHostFragment not found")
        val navController = navHostFragment.navController


        navController.graph = navController.createGraph(
            startDestination = ProductsRoute,
        ) {
            buildNavGraph()
        }
    }
}