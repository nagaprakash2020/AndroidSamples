package com.learn.appsamples.oneTimeEvents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.learn.appsamples.R
import com.learn.appsamples.databinding.ActivityMainBinding
import com.learn.appsamples.databinding.ActivityOneTimeEventBinding
import com.learn.appsamples.oneTimeEvents.OneTimeViewModel.Event.ErrorEvent
import com.learn.appsamples.oneTimeEvents.OneTimeViewModel.Event.ToastMessage
import kotlinx.coroutines.flow.collect

class OneTimeEventActivity : AppCompatActivity() {

    private val viewModel: OneTimeViewModel by viewModels()
    private lateinit var binding: ActivityOneTimeEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneTimeEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showSnack.setOnClickListener {
            viewModel.triggerEvent()
        }

        observeData()
    }

    private fun observeData() {
        lifecycleScope.launchWhenResumed {
            viewModel.eventsFlow.collect { event->
                when(event){
                    is ToastMessage -> Snackbar.make(binding.root, event.message, Snackbar
                            .LENGTH_SHORT).show()
                    is ErrorEvent -> TODO()
                }
            }
        }
    }
}