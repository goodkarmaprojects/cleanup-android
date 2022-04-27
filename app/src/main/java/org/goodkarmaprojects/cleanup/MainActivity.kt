package org.goodkarmaprojects.cleanup

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import org.goodkarmaprojects.cleanup.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupNavigation()
    }

    private val fullScreenViews = listOf(
        R.id.loginFragment, R.id.registerFragment, R.id.loginVerifyEmailDialog,
        R.id.registerVerifyEmailDialog, R.id.forgottenPasswordDialog
    )

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { navController, destination, _ ->
            when(fullScreenViews.contains(destination.id)){
                true -> enableFullScreen()
                false -> disableFullScreen()
            }


//            if(destination.id == R.id.loginFragment){
//                viewBinding.toolbar.visibility = View.GONE
//                //window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
//
//            }else{
//                viewBinding.toolbar.visibility = View.VISIBLE
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                window.statusBarColor = ContextCompat.getColor(this, R.color.primaryDark)
//            }
        }
    }



    private fun enableFullScreen(){
        viewBinding.toolbar.visibility = View.GONE
        window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS)
        window.setFlags(FLAG_TRANSLUCENT_NAVIGATION, FLAG_TRANSLUCENT_NAVIGATION)
    }

    private fun disableFullScreen(){
        viewBinding.toolbar.visibility = View.VISIBLE
        window.clearFlags(FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(FLAG_TRANSLUCENT_NAVIGATION)
    }

}