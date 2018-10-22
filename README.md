# StuffWeLike
Android project created using Kotlin with MVP pattern and using a lot of 3rd party libraries.

Kotlin
----
Kotlin version is [1.2.30](https://blog.jetbrains.com/kotlin/2018/03/kotlin-1-2-30-is-out/).

Libraries
---------

 * Kotlin - http://kotlinlang.org
 * Dagger2 - http://google.github.io/dagger
 * Retrofit - http://square.github.io/retrofit
 * Glide - https://github.com/bumptech/glide
 * RxJava - https://github.com/ReactiveX/RxJava
 * RxAndroid - https://github.com/ReactiveX/RxAndroid
 * Espresso - https://developer.android.com/training/testing/espresso/
 * Mockito - https://github.com/mockito/mockito

App Explanation
----
This app is used for used to select which item they like. User will be provided by several items loaded from Home24 API and then decide whether they like it or not one item at a time. After all items is show, the app will show how much items they've like and user can review all the liked items in the review screen.

Activities
----

### StartActivity
This activity is used as a launcher activity. In this screen, user can start the selection by pressing Start Selection.

### SelectionActivity
This activity is used as the main functionality for the app, that is picking which items user like. User will be shown one item at a time until all the items shown. For each item there will a button for user to like it or skip it. After all the items shown, user can see how much items they've liked. User can go to the review screen by pressing Review Selection button

### ReviewActivity
This activity shown all the items user have previously shown in selection screen. For items which user have pressed like, there will be a like icon in that item. User can aslo switch the view either List or Grid style.
