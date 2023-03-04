package com.example.machinetestspark.app.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "userDetailsPref"
    )
    private val authToken = stringPreferencesKey("authToken")
    private val userId = stringPreferencesKey("userId")
    private val email = stringPreferencesKey("email")




    suspend fun saveUserDetails(userDetails: UserDetails) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[authToken] = userDetails.authToken
            preferences[userId] = userDetails.userId
            preferences[email] = userDetails.email
        }
    }

    fun getUserFromPreferencesStore(): Flow<UserDetails> = context.userPreferencesDataStore.data
        .map { preferences ->
            UserDetails(
                authToken = preferences[authToken] ?: "",
                userId = preferences[userId] ?: "",
                email = preferences[email] ?: "",
            )
        }

    suspend fun clearFromPreferences() {
        context.userPreferencesDataStore.edit {
            it.clear()
        }
    }

}
