package com.example.bdingredientes.ui.theme

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.AnyRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.bdingredientes.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ExoPlayerViewModel : ViewModel(){
    private val _exoPlayer : MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val exoPlayer = _exoPlayer.asStateFlow()

    private val _duracion  = MutableStateFlow(0)
    //val duracion = _duracion.asStateFlow()

    private val _progreso = MutableStateFlow(0)
    //val progreso = _progreso.asStateFlow()

    fun crearExoPlayer(context: Context){
        _exoPlayer.value = ExoPlayer.Builder(context).build()
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }

    fun hacerSonarMusica(context: Context) {
        val mediaItem = MediaItem.fromUri(obtenerRuta(context, R.raw.build_station))
        _exoPlayer.value!!.setMediaItem(mediaItem)
        _exoPlayer.value!!.playWhenReady = true

        _exoPlayer.value!!.addListener(object : Player.Listener{
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> {

                        _duracion.value = _exoPlayer.value!!.duration.toInt()

                        viewModelScope.launch {
                            while (isActive) {
                                _progreso.value = _exoPlayer.value!!.currentPosition.toInt()
                                delay(1000)
                            }
                        }
                    }
                    Player.STATE_BUFFERING -> {

                    }
                    Player.STATE_ENDED -> {
                        Player.STATE_READY
                    }
                    Player.STATE_IDLE -> {
                    }
                }

            }
        }
        )
    }
    override fun onCleared() {
        _exoPlayer.value!!.release()
        super.onCleared()
    }

    fun pausarOSeguirMusica() {
        if(!_exoPlayer.value!!.isPlaying ){
            _exoPlayer.value!!.play()
        }else{
            _exoPlayer.value!!.pause()
        }
    }
@Throws(Resources.NotFoundException::class)
fun obtenerRuta(context: Context, @AnyRes resId: Int): Uri {
    val res: Resources = context.resources
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId)
    )
}

}
