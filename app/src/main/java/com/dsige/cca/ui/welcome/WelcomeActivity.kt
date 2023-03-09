package com.dsige.cca.ui.welcome

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.dsige.cca.R
import com.dsige.cca.databinding.ActivityWelcomeBinding
import com.dsige.cca.dialogs.LoadingDialog
import com.dsige.cca.ui.bases.baseActivity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : BaseActivity() {

    private var binding: ActivityWelcomeBinding? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun initLoading() {
        loadingDialog = LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        val navController = findNavController(R.id.container_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}