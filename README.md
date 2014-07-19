# Switchable Preference Category
*A Scala on Android library*

A custom PreferenceCategory that has an additional Switch to toggle its children

**Install**

This project is an Android library project meaning that you have to clone and reference it by yourself.

**Use in xml**

````
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

     <com.taig.android.preference.SwitchableCategory
         android:defaultValue="true"
				 android:key="my_key"
			 	 android:title="@string/my_title" />

</PreferenceScreen>
````