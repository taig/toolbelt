# Android Toolbelt
*A Scala on Android library*

An Android library project providing essential helpers

This library is the foundation for the [Coyote Buttes Live Wallpaper](https://play.google.com/store/apps/details?id=com.taig.wallpaper.coyote_buttes)

**Install**

````
resolvers += Resolver.url( "Toolbelt", url( "http://taig.github.io/Toolbelt/release" ) )( ivyStylePatterns )

libraryDependencies += "com.taig.android" %% "toolbelt" % "0.3.2-BETA"
````

**Features**

The source is currently lacking of documentation as it is primary intended for personal use. The library's main features are:

- Common implicit conversions (`import com.taig.android.conversion._`)
- WallpaperService wrapper to clean up the mess of an API Google is providing
- Color picker preference
- Font picker preference
- Future implementation that runs callbacks on UI thread
- ImageView that supports SVGs, rounded corners and fixed aspect ratio

**Screenshots**

![Screenshot 1](http://taig.github.io/Toolbelt/asset/1.png)
![Screenshot 2](http://taig.github.io/Toolbelt/asset/2.png)
![Screenshot 3](http://taig.github.io/Toolbelt/asset/3.png)
![Screenshot 4](http://taig.github.io/Toolbelt/asset/4.png)
