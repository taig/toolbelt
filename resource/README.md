# Toolbelt Resource

> Resolve Android resources

```scala
import io.taig.android.resource.implicits._

// getResource.getString( R.string.my_string )
R.string.my_string.as[String]

// getResource.getText( R.string.my_string )
R.string.my_string.as[CharSequence]

// getResource.getDrawable( R.drawable.my_drawable )
R.drawable.my_drawable.as[Drawable]
```

See the `ResourceResolver` type class instances for a list of all supported types.