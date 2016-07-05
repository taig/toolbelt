package io.taig.android

trait implicits
    extends app.implicits
    // with compatibility.implicits
    with concurrent.implicits
    with context.implicits
    with functional.implicits
    with graphic.implicits
    with intent.implicits
    with log.implicits
    with resource.implicits
    with system_service.implicits
    with unit.implicits
    with util.implicits
    with widget.implicits

object implicits extends implicits