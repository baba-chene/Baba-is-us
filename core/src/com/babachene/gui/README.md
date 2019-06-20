### Graphical User Interface

Root package for all libgdx-related and graphic-related classes.


#### How to develop your game:

A "game" is either an `ApplicationListener` (libgdx's way) or a `GameState` (Jérémy's way).
* For the `ApplicationListener`, by the way you should make your class extend `ApplicationAdapter`, create your stuff (i.e. a batch, textures) in the `create()` method and render the screen in the `render()` method.
* For the `GameState`, create a class extending `GameState`. Create your stuff in the constructor and go in the `MainGame` class, where you need to put the line `push( new YourGameState );` in the `create()` method, so that your game state will be the active one. Don't forget that the `MainGame` has to be instanciated and launched in the `DesktopLauncher` class.
