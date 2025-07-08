package id.yuana.bootcamp.demo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import id.yuana.bootcamp.demo.fragments.ProductAddFragment
import id.yuana.bootcamp.demo.fragments.ProductDetailFragment
import id.yuana.bootcamp.demo.fragments.ProductsFragment
import kotlinx.serialization.Serializable

/**
 * fragments as Destinations in the navigation graph.
 */
@Serializable
object ProductsRoute

@Serializable
data class ProductDetailRoute(
    val postId: String
)

@Serializable
object ProductAddRoute


fun NavGraphBuilder.buildNavGraph() {
    fragment<ProductsFragment, ProductsRoute> {
        label = "Products"
    }

    fragment<ProductDetailFragment, ProductDetailRoute>() {
        label = "Product Detail"
    }

    fragment<ProductAddFragment, ProductAddRoute> {
        label = "Add Post"
    }
}