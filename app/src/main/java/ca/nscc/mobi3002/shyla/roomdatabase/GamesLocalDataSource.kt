package ca.nscc.mobi3002.shyla.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Database([GameConsoles::class, VideoGames::class], version = 1)
abstract class GameDatabase: RoomDatabase() {
    abstract fun consolesDAO(): consolesDAO
    abstract fun gamesDAO(): gamesDAO

}

@Entity(tableName="Consoles")

data class GameConsoles (
    @PrimaryKey(autoGenerate=true) var ConsoleID:Int,
    @ColumnInfo("Console")
    var ConsoleName:String,
    var ConsoleDesc:String,
    var ImageID:Int

    )

@Entity(tableName="Games")
data class VideoGames (
    @PrimaryKey(autoGenerate=true) var GameID:Int,
    @ColumnInfo("Game")
    var GameName:String,
    var GameDescription:String,
    var ImageID:Int,
    var GamePrice:Float,
    var GenreText:String,
    var ConsoleID: Int
)

@Dao
interface consolesDAO {
    @Query("SELECT * FROM Consoles")
    fun getAllConsoles(): List<GameConsoles>
    @Query("SELECT * FROM Consoles WHERE ConsoleID = :ConsoleID")
    fun getSingleConsole(ConsoleID: Int): GameConsoles
    @Insert
    fun InsertConsoles(vararg gameConsoles: GameConsoles)


}

@Dao
interface gamesDAO {
    @Query("SELECT * FROM Games")
    fun getAllGames(): List<VideoGames>

    @Query("SELECT * FROM Games WHERE GameID = :GameID")
    fun getSingleGame(GameID: Int): VideoGames
    @Insert
    fun InsertGames(vararg game: VideoGames)

    @Update
    fun updateGame(game: VideoGames)
}
