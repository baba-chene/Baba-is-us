### Graphical User Interface

Root package for all libgdx-related and graphic-related classes.


#### How to develop your game:

A "game" is either an `ApplicationListener` (libgdx's way) or a `GameState` (Jérémy's way).
* For the `ApplicationListener`, by the way you should make your class extend `ApplicationAdapter`, create your stuff (i.e. a batch, textures) in the `create()` method and render the screen in the `render()` method.
* For the `GameState`, create a class extending `GameState`. Create your stuff in the constructor and go in the `MainGame` class, where you need to put the line `push( new YourGameState() );` in the `create()` method, so that your game state will be the active one. Don't forget that the `MainGame` has to be instanciated and launched in the `DesktopLauncher` class.

#### How to add a new entity type and its texture:

* Baba class holds the list of all enity id, and a new name here.
* Rsrc class holds references to textures (TextureRegion) and manages them all,
add a texture field here.
* Still in Rsrc, program its initialization in the loadEverything() method.
* Rsrc also contains a function that maps ID from Baba to textures, add the
mapping in the switch/case.
* Add the texture with the others. This is done with libGDX's texture packer tool. See the DesktopLauncher class and libGDX's documentation.
