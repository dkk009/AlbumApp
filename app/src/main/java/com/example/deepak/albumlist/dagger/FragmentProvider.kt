package com.example.deepak.albumlist.dagger

import com.example.deepak.albumlist.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}