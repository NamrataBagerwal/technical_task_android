package com.android_dev_challenge.sliide.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.text.Layout
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.android_dev_challenge.sliide.R
import com.android_dev_challenge.sliide.common.util.Utility
import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse
import com.android_dev_challenge.sliide.ui.adapter.UserAdapter
import com.android_dev_challenge.sliide.viewmodel.MainActivityViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    private val errorTextView: TextView by lazy { findViewById(R.id.errorTextView) }
    private val usersRecyclerView: RecyclerView by lazy { findViewById(R.id.usersRecyclerView) }
    private val progressBar: ContentLoadingProgressBar by lazy {
        findViewById(
            R.id.contentLoadingProgressBar
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { showAddNewUserDialog() }

        showProgressBar()

        mainActivityViewModel.getUserLiveData().removeObservers(this@MainActivity)
        mainActivityViewModel.getUserLiveData().observe(
            this@MainActivity,
            { userList: List<GetUserApiResponse.User> ->
                if (userList.isNullOrEmpty())
                    showErrorView(getString(R.string.server_unavailable_error_msg))
                else {
                    hideProgressBar()
                    showUsersList()

                    val userAdapter = UserAdapter { user: GetUserApiResponse.User ->
                        val id = user.id
                        println("TAG ${user.name}")
                        true
                    }
                    usersRecyclerView.adapter = userAdapter
                    userAdapter.userList = userList
                }


            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        progressBar.show()
    }

    private fun hideProgressBar() {
        progressBar.hide()
        progressBar.visibility = View.GONE

    }

    private fun showErrorView(errorMessage: String) {
        hideProgressBar()
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = errorMessage
        usersRecyclerView.visibility = View.GONE
    }

    private fun showUsersList() {
        hideProgressBar()
        errorTextView.visibility = View.GONE
        usersRecyclerView.visibility = View.VISIBLE
    }

    private fun showAddNewUserDialog() {

        Utility.showAlertDialog(
            this@MainActivity,
            R.layout.dialog_add_new_user,
            getString(R.string.dialog_title_add_new_user),
            getString(R.string.dialog_btn_add)
        )


    }
}