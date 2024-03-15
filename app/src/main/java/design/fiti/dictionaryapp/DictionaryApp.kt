package design.fiti.dictionaryapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//setting up DI

@HiltAndroidApp
class DictionaryApp : Application()