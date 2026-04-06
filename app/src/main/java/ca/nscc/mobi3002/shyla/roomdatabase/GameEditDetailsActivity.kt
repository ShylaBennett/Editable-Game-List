package ca.nscc.mobi3002.shyla.roomdatabase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.nscc.mobi3002.shyla.roomdatabase.databinding.ActivityGameEditDetailsBinding

class GameEditDetailsActivity : AppCompatActivity() {

    lateinit var _binding: ActivityGameEditDetailsBinding
    private val binding get() = _binding
    private lateinit var gamesRepository: GamesRepository
    private lateinit var editGame: VideoGames

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityGameEditDetailsBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gamesRepository= GamesRepository(applicationContext)
        val consoleID: Int = this.intent.getIntExtra("ca.nscc.mobi3002.shyla.gameID", -1)

//        gamesRepository = GamesRepository(applicationContext)
        if (consoleID != -1){
        editGame = gamesRepository.getSingleGame(consoleID)
            binding.editGame = editGame
        }

        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSave.setOnClickListener {
            setResult(900)
            gamesRepository.updateGame(editGame)
            finish()
        }
        binding.btnConsoleInfo.setOnClickListener {
            if (editGame.ConsoleID != -1) {
                val intent = Intent(this, GameConsoleActivity::class.java)
//                intent.putExtra("ca.nscc.mobi3002.shyla.consoleID", editGame.ConsoleID)
                intent.putExtra("ca.nscc.mobi3002.shyla.gameDescription", editGame.GameDescription)
                intent.putExtra("ca.nscc.mobi3002.shyla.consoleID", editGame.ConsoleID)

                startActivity(intent)
            }
        }
    }
}