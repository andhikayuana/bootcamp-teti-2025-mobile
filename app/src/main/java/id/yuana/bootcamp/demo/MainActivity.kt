package id.yuana.bootcamp.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import id.yuana.bootcamp.demo.data.source.JokesBapack2Api
import id.yuana.bootcamp.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val jokesApi by lazy {
        JokesBapack2Api.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getRandomJoke()

        //onclick binding.textJoke
        binding.textJoke.setOnClickListener {
            getRandomJoke()
        }
    }

    private fun getRandomJoke() {
        lifecycleScope.launch {
//
//            try {
//                val response = jokesApi.randomJoke()
//                binding.textJoke.text = response.data
//            } catch (e: Exception) {
//                Log.d("MainActivity", "Error fetching joke: ${e.message}")
//                binding.textJoke.text = getString(R.string.unable_to_fetch_joke)
//            }

            runCatching { jokesApi.randomJoke() }
                .onSuccess {
                    binding.textJoke.text = it.data
                }
                .onFailure {
                    Log.d("MainActivity", "Error fetching joke: ${it.message}")
                    binding.textJoke.text = getString(R.string.unable_to_fetch_joke)
                }

        }
    }
}