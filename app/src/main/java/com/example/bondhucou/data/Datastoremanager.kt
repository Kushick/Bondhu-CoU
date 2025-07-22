package com.example.bondhucou.data

import android.content.Context
import com.example.bondhucou.data.DataStoreManager
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.set

val Context.dataStore by preferencesDataStore("bondhu_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        val IS_DONATED = booleanPreferencesKey("is_donated")
        val DONATE_DATE = stringPreferencesKey("donate_date")

        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

        val NAME = stringPreferencesKey("name")
        val DEPARTMENT = stringPreferencesKey("department")
        val SESSION = stringPreferencesKey("session")
        val BLOOD_GROUP = stringPreferencesKey("blood_group")
        val CONTACT_NUMBER = stringPreferencesKey("contact_number")
    }

    val name: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[NAME] ?: "" }

    val department: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[DEPARTMENT] ?: "" }

    val session: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[SESSION] ?: "" }

    val bloodGroup: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[BLOOD_GROUP] ?: "" }

    val contactNumber: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[CONTACT_NUMBER] ?: "" }


    val isDonated: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_DONATED] ?: false }

    val donateDate: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[DONATE_DATE] ?: "" }

    suspend fun saveDonationStatus(isDonated: Boolean, donateDate: String) {
        context.dataStore.edit { preferences ->
            preferences[IS_DONATED] = isDonated
            preferences[DONATE_DATE] = donateDate
        }
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_LOGGED_IN] ?: false }

    suspend fun saveLoginState(loggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = loggedIn
        }
    }

    suspend fun clearLoginState() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = false
        }
    }

    suspend fun saveUserInfo(
        name: String,
        department: String,
        session: String,
        bloodGroup: String,
        contactNumber: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[NAME] = name
            preferences[DEPARTMENT] = department
            preferences[SESSION] = session
            preferences[BLOOD_GROUP] = bloodGroup   
            preferences[CONTACT_NUMBER] = contactNumber
        }
    }

    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { it[IS_LOGGED_IN] = value }
    }

    fun isLoggedInFlow(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[IS_LOGGED_IN] ?: false
        }
    }
}
