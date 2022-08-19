package com.example.core.data.source.local.repository

//class FavoritedRepository @Inject constructor(application: Application) :
//    AbstractFavoritedRepository() {
//    override val mFavoritedDao: FavoritedDao
//    override val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//
//    init {
//        val db = FavoritedDatabase.getDatabase(application)
//        mFavoritedDao = db.favoritedDao()
//    }
//
//    override fun getAllFavorites(): LiveData<List<FavoritedStory>> = mFavoritedDao.getAllFavorites()
//
//    override fun insert(user: FavoritedStory) {
//        executorService.execute { mFavoritedDao.insert(user) }
//    }
//
//    override fun delete(user: FavoritedStory) {
//        executorService.execute { mFavoritedDao.delete(user) }
//    }
//
//    override fun isStoryExist(id: String): Boolean = mFavoritedDao.isStoryExist(id)
//}