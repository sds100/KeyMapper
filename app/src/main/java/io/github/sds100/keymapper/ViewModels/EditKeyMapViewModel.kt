package io.github.sds100.keymapper.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sds100.keymapper.KeymapLiveData

/**
 * Created by sds100 on 04/10/2018.
 */

class EditKeyMapViewModel(id: Long,
                          application: Application) : ConfigKeyMapViewModel(application) {
    override val keyMap: KeymapLiveData = KeymapLiveData(keyMapRepository.getKeyMap(id))

    override fun saveKeymap() {
        keyMapRepository.updateKeyMap(keyMap.value!!)
    }

    class Factory(private val mId: Long,
                  private val mApplication: Application
    ) : ViewModelProvider.AndroidViewModelFactory(mApplication) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                EditKeyMapViewModel(mId, mApplication) as T
    }
}