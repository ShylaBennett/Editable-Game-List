package ca.nscc.mobi3002.shyla.roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.nscc.mobi3002.shyla.roomdatabase.databinding.ActivityGameListBinding

class GamesAdapter(val games: List<VideoGames>, val gameItemClickHandler: GameClickHandler? = null): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesAdapter.GameViewHolder {
        val view = ActivityGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(view, gameItemClickHandler)
    }

    override fun onBindViewHolder(holder: GamesAdapter.GameViewHolder, position: Int) {
        holder.bindGame(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
    inner class GameViewHolder(val binding: ActivityGameListBinding, val gameItemClickHandler: GameClickHandler? = null): RecyclerView.ViewHolder(binding.root){
        init {
            if(gameItemClickHandler != null){
                this.itemView.setOnClickListener { gameItemClickHandler.onGameItemClick(games[this.bindingAdapterPosition].GameID) }
            }
        }
    fun bindGame(games: VideoGames) {
        binding.game = games
    }
    }

    interface GameClickHandler{
        fun onGameItemClick(gameID: Int)
    }
}