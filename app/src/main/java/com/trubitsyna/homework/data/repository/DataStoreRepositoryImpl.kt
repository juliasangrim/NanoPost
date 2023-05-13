package com.trubitsyna.homework.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.trubitsyna.homework.data.local.model.auth.UserData
import com.trubitsyna.homework.utils.Constants
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val userDataStore: DataStore<Preferences>
) {

    suspend fun saveUserData(userData: UserData) {
        userDataStore.edit { userInfo ->
            userInfo[stringPreferencesKey(Constants.USER_ID_KEY)] = userData.userId
            userInfo[stringPreferencesKey(Constants.TOKEN_ID_KEY)] = userData.token
        }
    }

}