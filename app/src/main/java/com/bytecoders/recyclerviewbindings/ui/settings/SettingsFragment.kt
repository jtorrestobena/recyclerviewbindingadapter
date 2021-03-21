package com.bytecoders.recyclerviewbindings.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.bytecoders.recyclerviewbindings.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}