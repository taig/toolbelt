package io.taig.android.content.application

import io.taig.android.context.Contextual

trait Application
        extends android.app.Application
        with Contextual {
    override implicit def context = this
}