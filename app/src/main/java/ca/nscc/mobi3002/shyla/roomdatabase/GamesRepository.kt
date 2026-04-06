package ca.nscc.mobi3002.shyla.roomdatabase

import android.content.Context
import androidx.room.Room
import java.io.Console

class GamesRepository(val context: Context) {
    private val gameDatabase =
        Room.databaseBuilder(context, GameDatabase::class.java, "GameDB").allowMainThreadQueries()
            .build()
    private val gamesDAO = gameDatabase.gamesDAO()
    private val consolesDAO = gameDatabase.consolesDAO()
    val consolesData = getAllConsoles()


    fun getAllGames(): List<VideoGames> {
        val gList: List<VideoGames> = gamesDAO.getAllGames()
        if (gList.isEmpty()) {
            populateGameData()
            return gamesDAO.getAllGames()


        } else {
            return gList
        }
    }

    fun getAllConsoles(): List<GameConsoles> {
        val cList: List<GameConsoles> = consolesDAO.getAllConsoles()
        if (cList.isEmpty()) {
            populateConsoleData()
            return consolesDAO.getAllConsoles()
        } else {
            return cList
        }
    }

    fun getSingleGame(GameID: Int): VideoGames{
        return gamesDAO.getSingleGame(GameID)
    }

//    fun insertGame(games: VideoGames){
//        gamesDAO.InsertGames(games)
//    }

    fun updateGame(games: VideoGames) {
        gamesDAO.updateGame(games)
    }

    fun getSingleConsole(ConsoleID: Int): GameConsoles{
        return consolesDAO.getSingleConsole(ConsoleID)
    }
//
//        fun deleteGame(games: VideoGames) {
//            gamesDAO.DeleteGame(games)
//        }

//    fun populateConsoleData() {
//        consolesDAO.InsertConsoles(GameConsoles(0, "Dave the Diver", "Dave the Diver is a fun game where you dive underwater to catch fish and explore the ocean, then run a sushi restaurant on land. You get to balance exciting diving adventures with managing and upgrading your restaurant. (Future job opportunity for Dave? Maybe.)", R.drawable.davethediver),
//            GameConsoles(0, "Animal Crossing: New Horizons", "Animal Crossing: New Horizons lets you escape to your own little island where you can build, decorate, and make friends with cute animal neighbors. You can fish, catch bugs, garden, and just enjoy life at your own pace.", R.drawable.animal_crossing_new_horizons),
//            GameConsoles(0,"Stardew Valley", "Stardew Valley lets you escape to the countryside to fix up your old farm, grow crops, raise animals, and explore the town. It’s all about living at your own pace, making friends, and enjoying the little things in life.",  R.drawable.stardew),
//            GameConsoles(0,"Skyrim",  "Skyrim is a huge open-world game where you can explore mountains, dungeons, and meet all kinds of people. You get to choose your own path, fight dragons, learn new skills, and make your own story in the world.",R.drawable.skyrim),
//            GameConsoles(0,"Story of Seasons: Grand Bazaar",  "Story of Seasons: Grand Bazaar lets you run your own farm, grow crops, raise animals, and take part in bazaars. It’s all about building relationships, exploring, and enjoying a cozy, lively country life.",R.drawable.sosgrandbazaar),
//            GameConsoles(0,"Story of Seasons: Pioneers of Olive Town",  "Story of Seasons: Pioneers of Olive Town is all about moving to a new town, fixing up your farm, and getting to know the villagers. It’s a cozy, laid-back game where you can farm, explore, and just enjoy life at your own pace.",R.drawable.sospioneersofolivetown),
//            GameConsoles(0,"Fable II", "Fable II is an RPG where your choices shape who you become in a magical world. You can fight, explore, and interact with townsfolk, and your decisions really affect the story and how people see you.",R.drawable.fable_ii),
//            GameConsoles(0,"Pokemon Violet", "Pokemon Violet lets you explore a whole new region, catch and train Pokemon, and battle other trainers. It’s all about discovering cool Pokemon, customizing your team, and going on your own adventure.",R.drawable.pokemonviolet),
//            GameConsoles(0,"Pokemon Legends: ZA", "Pokemon Legends: ZA is an exciting new Pokemon game where you explore a vibrant world, catch Pokemon in real time, and uncover a mysterious story. It is a fun combination of adventure, strategy, and exploration.",R.drawable.pokemonza),
//            GameConsoles(0,"It Takes Two", "It Takes Two is a fun and chaotic co-op game where you and a friend play as a couple who turned into dolls, solving creative puzzles together. Every level is full of challenges that keep you laughing and working as a team.",R.drawable.it_takes_two),
//            GameConsoles(0,"Palworld", "Palworld is an open-world adventure game where you befriend and collect creatures called “Pals” while exploring, crafting, and surviving. It mixes cute monsters with action-packed battles and building.",R.drawable.palworld))
//    }

    fun populateConsoleData() {
        consolesDAO.InsertConsoles(GameConsoles(0, "Nintendo Switch", "A handheld and home console released by Nintendo in 2017.", R.drawable.nswitch),
            GameConsoles(0, "Nintendo Switch 2", "The Nintendo Switch 2 is a faster, sharper version of the classic Switch. You can play it at home or on the go, and everything just feels smoother and more fun.", R.drawable.nswitch2),
            GameConsoles(0, "Sony Playstation 5", "The PlayStation 5 is built for fast load times and really smooth gameplay. It has amazing graphics and great 3D audio, making games feel more immersive than ever.", R.drawable.ps5),
            GameConsoles(0,"Xbox 360", "“The Xbox 360 is Microsoft’s classic console that brought online gaming to the living room. It has great games, smooth graphics for its time, and a ton of fun multiplayer games",  R.drawable.xbox360))

    }



    fun populateGameData(){
        //Use VideoGames, the data class for the DB
        gamesDAO.InsertGames(VideoGames(0, "Dave the Diver", "Dave the Diver is a fun game where you dive underwater to catch fish and explore the ocean, then run a sushi restaurant on land. You get to balance exciting diving adventures with managing and upgrading your restaurant. (Future job opportunity for Dave? Maybe.)", R.drawable.davethediver, 29.99f, "RPG",3),
            VideoGames(0, "Animal Crossing: New Horizons", "Animal Crossing: New Horizons lets you escape to your own little island where you can build, decorate, and make friends with cute animal neighbors. You can fish, catch bugs, garden, and just enjoy life at your own pace.", R.drawable.animal_crossing_new_horizons, 79.99f, "Life Sim",1),
            VideoGames(0,"Stardew Valley", "Stardew Valley lets you escape to the countryside to fix up your old farm, grow crops, raise animals, and explore the town. It’s all about living at your own pace, making friends, and enjoying the little things in life.",  R.drawable.stardew, 19.99f, "Farming",1),
            VideoGames(0,"Skyrim",  "Skyrim is a huge open-world game where you can explore mountains, dungeons, and meet all kinds of people. You get to choose your own path, fight dragons, learn new skills, and make your own story in the world.",R.drawable.skyrim, 59.99f, "RPG",3),
            VideoGames(0,"Story of Seasons Grand Bazaar",  "Story of Seasons: Grand Bazaar lets you run your own farm, grow crops, raise animals, and take part in bazaars. It’s all about building relationships, exploring, and enjoying a cozy, lively country life.",R.drawable.sosgrandbazaar, 79.99f, "Farming",2),
            VideoGames(0,"Story of Seasons Pioneers of Olive Town",  "Story of Seasons: Pioneers of Olive Town is all about moving to a new town, fixing up your farm, and getting to know the villagers. It’s a cozy, laid-back game where you can farm, explore, and just enjoy life at your own pace.",R.drawable.sospioneersofolivetown, 69.99f, "Farming",1),
            VideoGames(0,"Fable II", "Fable II is an RPG where your choices shape who you become in a magical world. You can fight, explore, and interact with townsfolk, and your decisions really affect the story and how people see you.",R.drawable.fable_ii, 24.99f, "RPG",4),
            VideoGames(0,"Pokemon Violet", "Pokemon Violet lets you explore a whole new region, catch and train Pokemon, and battle other trainers. It’s all about discovering cool Pokemon, customizing your team, and going on your own adventure.",R.drawable.pokemonviolet, 79.99f, "RPG",1),
            VideoGames(0,"Pokemon Legends: ZA", "Pokemon Legends: ZA is an exciting new Pokemon game where you explore a vibrant world, catch Pokemon in real time, and uncover a mysterious story. It is a fun combination of adventure, strategy, and exploration.",R.drawable.pokemonza, 89.99f, "RPG",2),
            VideoGames(0,"It Takes Two", "It Takes Two is a fun and chaotic co-op game where you and a friend play as a couple who turned into dolls, solving creative puzzles together. Every level is full of challenges that keep you laughing and working as a team.",R.drawable.it_takes_two, 39.99f, "Platformer",3),
            VideoGames(0,"Palworld", "Palworld is an open-world adventure game where you befriend and collect creatures called “Pals” while exploring, crafting, and surviving. It mixes cute monsters with action-packed battles and building.",R.drawable.palworld, 24.99f, "Open World",3))
    }



}