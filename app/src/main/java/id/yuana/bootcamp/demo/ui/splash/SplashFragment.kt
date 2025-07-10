package id.yuana.bootcamp.demo.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import id.yuana.bootcamp.demo.DemoApp
import id.yuana.bootcamp.demo.R
import id.yuana.bootcamp.demo.ui.UiEffect

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = SplashViewModel(
            (requireActivity().application as DemoApp).appModule.provideAuthRepository()
        )

        viewModel.uiEffect.observe(this, Observer { effect ->
            when (effect) {
                is UiEffect.Navigate -> findNavController().navigate(effect.directions)
                else -> {}
            }
        })
        viewModel.onEvent(SplashEvent.OnLoad)
    }
}