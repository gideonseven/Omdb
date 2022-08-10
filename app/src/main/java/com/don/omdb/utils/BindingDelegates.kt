package com.don.omdb.utils

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

//for fragment
//https://gist.github.com/jamiesanson/478997780eb6ca93361df311058dc5c2
fun <T> Fragment.bindingDelegates(initialise: () -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

        // A backing property to hold our value
        private var binding: T? = null

        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            // Observe the View Lifecycle of the Fragment
            this@bindingDelegates
                .viewLifecycleOwnerLiveData
                .observe(this@bindingDelegates) { newLifecycleOwner ->
                    viewLifecycleOwner
                        ?.lifecycle
                        ?.removeObserver(this)

                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            binding = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            // Return the backing property if it's set, or initialise
            return this.binding ?: initialise().also {
                this.binding = it
            }
        }
    }

//for activity binding that have activity layout [kotlin] from
//https://gist.github.com/gmk57/aefa53e9736d4d4fb2284596fb62710d
inline fun <T : ViewBinding> AppCompatActivity.bindingDelegates(crossinline factory: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }

