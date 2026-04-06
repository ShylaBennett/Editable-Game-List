package ca.nscc.mobi3002.shyla.roomdatabase

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.nscc.mobi3002.shyla.roomdatabase.databinding.ActivityGameListBinding
import ca.nscc.mobi3002.shyla.roomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private lateinit var GamesRepository: GamesRepository

    private val gameEditLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == 900) {
            bindGameData()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((_binding.root))


//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mainActivity = this

//        binding.recycleViewGames.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        GamesRepository = GamesRepository(applicationContext)

        bindGameData()

    }

//    fun onButtonChangeNameClick(view: View) {
//
//    }

    fun bindGameData(){
        val gamesAdapter = GamesAdapter(GamesRepository.getAllGames(), gameClickHandler())
        binding.recycleViewGames.adapter = gamesAdapter

        binding.recycleViewGames.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }

    inner class gameClickHandler: GamesAdapter.GameClickHandler {
        override fun onGameItemClick(gameID: Int) {
            val intent: Intent = Intent(this@MainActivity, GameEditDetailsActivity::class.java)
            intent.putExtra("ca.nscc.mobi3002.shyla.gameID", gameID)
            gameEditLauncher.launch(intent)
        }
    }


}