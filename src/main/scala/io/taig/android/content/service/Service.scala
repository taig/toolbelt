package io.taig.android.content.service

import io.taig.android.content.Contextual

trait Service
        extends android.app.Service
        with Contextual {
    override implicit def context = getApplicationContext
}