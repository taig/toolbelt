package io.taig.android.content.application

import io.taig.android.content.Contextual

trait Application
        extends android.app.Application
        with Contextual {
    override implicit def context = this
}