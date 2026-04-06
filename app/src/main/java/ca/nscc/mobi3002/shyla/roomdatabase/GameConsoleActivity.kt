package ca.nscc.mobi3002.shyla.roomdatabase

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import ca.nscc.mobi3002.shyla.roomdatabase.databinding.ActivityGameConsoleBinding

class GameConsoleActivity : AppCompatActivity() {

    lateinit var _binding: ActivityGameConsoleBinding
    private val binding get() = _binding
    private lateinit var consolesRepository: GamesRepository
    private lateinit var console: GameConsoles


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityGameConsoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mainToolbar: Toolbar = binding.mainToolbar
        setSupportActionBar(mainToolbar)

        consolesRepository = GamesRepository(applicationContext)
        val consoleID: Int = this.intent.getIntExtra("ca.nscc.mobi3002.shyla.consoleID", -1)
//        val gameID: Int = this.intent.getIntExtra("ca.nscc.mobi3002.shyla.gameID", -1)
        val editedDescription = intent.getStringExtra("ca.nscc.mobi3002.shyla.gameDescription")

        if (consoleID != -1) {
            console = consolesRepository.getSingleConsole(consoleID)
            binding.console = console
        }
        val mp: ConsoleMenuProvider = ConsoleMenuProvider()
        addMenuProvider(mp, this, Lifecycle.State.RESUMED)
    }


    inner class ConsoleMenuProvider : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.console_toolbar_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.menuBack -> {
                    finish()
                    return true
                }

                else -> return false
            }
        }
    }
}