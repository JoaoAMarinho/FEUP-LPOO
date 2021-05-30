# LPOO_T5_G57 - OVERKILL

## Game Description

The OVERKILL is a rogue-like shooter based game where you have to murder different waves of enemies, obtaining coins with each kill and having the chance to level up your power with the help of new guns.
The variety of levels will have increased difficulty with different bullet speeds and strategic enemies.

This project was developed by Beatriz Aguiar (up201906030@edu.fe.up.pt), João Marinho (up201905952@edu.fe.up.pt) and Tiago Silva (up201906045@edu.fe.up.pt) for LPOO 2020⁄21.

## Implemented Features

- **Connected Menus** - The user has the capability of browsing through the different menus including in game ones. (Ex: Main Menu, Instructions, Play, Shop and Pause).
- **Buttons** - Functional and interactive buttons.
- **Mouse and Keyboard control** - The mouse and keyboard inputs are received through the respective events and interpreted according to the current game state.
- **Player control** - The player may move with the keyboard control and shoot his gun when the left mouse button is pressed.
- **Collisions detection** - Collisions between different objects are verified. (Ex: Player, Bombs, Enemies, Obstacles).
- **Different levels** - 7 different levels with an increasing difficulty were implemented.
- **Shop interaction and money management** - The player may buy new items in the in game shop, some of which consist of new weapons, power ups and potions.
- **Animations** - Several animations are incorporated in this game, from bomb explosions to level loading and the effects of consuming potions.

## Planned Features

All the planned features were successfully implemented.

## Design

### General Structure
#### Problem in Context:
The first concern of our project was how the structure would look like. Since our game is dealling with a GUI and is divided by different gameStates some specific patterns came to mind in order to fullfil our needs the best way possible.

#### The Pattern:
Two main patterns were applied to the project, the **_Architectural Pattern_**, more specifically the Model-View-Controller style which is commonly used in a GUI and the **_State Pattern_** which is a behavioral design pattern that lets an object alter its behavior when its internal state changes.  

#### Implementation:
Regarding the implementation, we now have classes which main purpose is to store data (model), classes that control the logic of the game (controllers) and classes that are responsible for the visual effects on the screen (viewers), these types of classes associate with each other in the following manner:

<p align="center" justify="center">
  <img src="images/UML/MVC.png"/>
</p>
<p align="center">
  <b><i>Fig 1. Model, Controller and Viewer pattern design</i></b>
</p>

As for the different states, they are divided with the same methodology as the mvc style, and permite the game to alter its behavior in a simple and efficient way.

#### Consequences:
The use of these patterns in the current design allow the following benefits:
- The several states that represent the different menus become explicit in the code, instead of relying on a series of flags.
- A well organized code acknowledging the Single Responsibility Principle.
- Easy to add new features throughout the development stage.


### Observers and listeners
#### Problem in Context:  
Our game is controlled both by the mouse and the keyboard, many are the ways to receive input from these devices, for example: a thread that is running and every time it catches a signal it sends to the game itself (polling), the game being responsible for asking for input when needed, which is costly for our program since we may not send any signal to the program and unnecessary calls are made or the way we decided to implement which consists of using observers also known as listeners that are responsible for receiving the said input and redistributing it in a nicer and more usefull way to us. This takes some "weight" of the program as it will no longer need to be polling for input, as it will be properly warned when received.

#### The Pattern:
We have applied the **_Observer pattern_** which is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing. With this in mind the pattern allowed to solve the identified problems and apply a sustainable mechanism to receive any program input.

#### Implementation:
Implementation wise we store the observers in the main class (game class) and change its state according to the respective input processed by the available listener.
Though, it wasn't easy right from the start as our first attemp to implement this feature didn't act as expected. All listeners were always active, since when creating a Menu Button the listener would be activated by the newly created state, and it was far from being a structured and easy-to-read code.

<p align="center" justify="center">
  <img src="images/UML/ObsListener.png"/>
</p>
<p align="center">
  <b><i>Fig 2. Observers and Listeners screenshot</i></b>
</p>

#### Consequences:
Some consequences of using the stated pattern:
- Promotes the single responsibility principle.
- Clean code.
- Only the current game state is warned when an input is given.

### Battlefield builder
#### Problem in Context:
A battlefield consists in an aglomeration of elements such as walls, bombs, portals, a player, etc. 
Having different battlefields in the various levels, instead of having a builder to each level, a battlefield loader that reads from a file and inserts into its super class (battlefield builder) the needed elements, was the most appealing option. This implementation makes it possible to only create specific elements and also generate battlefields in different ways.

#### The Pattern:
The **_Factory Method_** and **_Builder_** are two creational design patterns, the first one provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. The second, allows you to construct complex objects step by step making a simpler code.

#### Implementation:
A factory is resposible for constructing the whole but the workers are the ones that actually execute the job. Analogously, our BattlefieldBuilder is a factory and its subclasses represent the workers.
As for the implmentation, the BattlefieldBuilder is an abstract class which knows how to construct a battlefield, however only its subclasses supply the necessary components of the battlefield. The BattlefieldLoader is one of referred subclasses that consists in a worker capable of reading different levels from different files. Another possible implementation would be a random loader that generates random components for the battlefield.
The builder pattern is implemented in all of the above classes by dividing the constructing in smaller steps.

<p align="center" justify="center">
  <img src="images/UML/BuilderLoader.png"/>
</p>
<p align="center">
  <b><i>Fig 3. Battlefield builder and loader</i></b>
</p>

These classes can be found in the following files:
- [BattlefieldBuilder](../src/main/java/com/g57/model/battlefield/BattlefieldBuilder.java)
- [BattlefieldLoader](../src/main/java/com/g57/model/battlefield/BattlefieldLoader.java)

#### Consequences:
Benefits of applying the above pattern:
- You avoid tight coupling between the creator and the concrete products.
- Open/Closed Principle. You can introduce new types of products into the program without breaking existing client code.
- You can construct objects step-by-step, defer construction steps or run steps recursively.


### Different types of commands
#### **Problem in Context:** 
In an initial and more simplified version of the current game, the diversity in between buttons was not significant. However, in the course of the development of the project, the number of buttons increased exponentially and the need to generalise the button element became more evident. That said and knowing that good software design is often based on the principle of separation of concerns, a major refactor had to be done. 

#### The Pattern:
We have applied the **_Command_** also know as Action pattern. This pattern turns a request into a stand-alone object that contains all information about the request.

#### Implementation:
Regarding the implemetation process, all the Button classes were deleted and transformed into a single **Button** with a command attribute. These **commands**  implement the same interface having an execution method that takes no parameters. This interface lets you use various commands with the same request sender, without coupling it to concrete classes of commands. As a bonus, now you can switch command objects linked to the sender, effectively changing the sender’s behavior at runtime.

<p align="center" justify="center">
  <img src="images/UML/ButtonCommand.png"/>
</p>
<p align="center">
  <b><i>Fig 4. Buttons and commands</i></b>
</p>

These classes can be found in the following files:
- [Button](../src/main/java/com/g57/model/element/button/Button.java)
- [Command](../src/main/java/com/g57/model/item/command/Command.java)

#### Consequences:
The Command Pattern allows the following consequences:
- You can decouple classes that invoke operations from classes that perform these operations (SRP).
- You can implement undo/redo.
- You can assemble a set of simple commands into a complex one.
- The code may become more complicated since you’re introducing a whole new layer between senders and receivers.


### GUI
#### Problem in Context:
Aiming for a structured and unstable (easy to change) code, we tried to make it as general as possible. The lanterna library contains various functions that aren't useful to our program, Interface Segregation Principle violation, and lacks some other functions that our interface needs. Also, if using the raw library, our game (high level module) would be directly depending on a low level module. This is a violation of the Dependency Inversion Principle (DIP). A need to implement an interface that solves these problems was born. 

#### The Pattern: 
We have applied the **_Facade_** pattern. A facade provides a simple interface to a complex subsystem which contains lots of moving parts, allowing us to only include the features that really matter.

#### Implementation:
<p align="center" justify="center">
  <img src="images/UML/GUI.png"/>
</p>
<p align="center">
  <b><i>Fig 5. Observers and Listeners screenshot</i></b>
</p>

These classes can be found in the following files:
- [Game](../src/main/java/com/g57/Game.java)
- [GUI](../src/main/java/com/g57/gui/GUI.java)
- [LanternaGUI](../src/main/java/com/g57/gui/LanternaGUI.java)

#### Consequences: 
The use of the Facade Pattern in the current design allows the following benefits:
- Isolate code from the complexity of a subsystem.
- Promotes testability and replaceability.
- Expand lanterna functionalities as well as respecting the Interface Segregation Principle.


## Known Code Smells And Refactoring Suggestions
#### **Large Class**
Some classes (e.g. Game, Battlefield, Player) contain many fields and others (e.g. GUI interface) contain many methods. In both cases, we find it justifiable as the classes require these fields, in one hand the Game class is the main class of the program and it needs to store a considerable amount of data, on the other hand various methods are needed for the interface and it wouldn't make sense to split it into two separate ones (extract method).

#### **Data Class**
All model classes are Data Classes, as they contain only fields, and no behavior (dumb classes). This is caused by the **MVC** (Model-View-Controller) architectural pattern which holds the responsibility to the controller to implement the logic functionalities of each model.
This is not a bad code smell because it only exits due to the chosen design pattern.

#### **Alternative classes with different interfaces and Lazy Classes**
When we conceived the project ideas, we aspired various enemy types with different behaviours. However, with the project development, we decided to generalize our **Enemy Class** and differenciate, the divergent characteristics, from contrasting enemies based on their fields. As this classes only differ in the values passed to the **Enemy Class** constructor and have no other significant functions they are an example of **Alternative Classes with different interfaces and Lazy Classes**.

#### **Refused bequest**
In an attempt to generalize and simplify our code, various abstract classes and interfaces were created. Nevertheless this resulted in the rising of the **Refused bequest** smell. As a result, some subclasses inherited methods from its parent classes which are neither defined nor used. For example, the [**SwapCommand Class**](../src/main/java/com/g57/model/item/command/SwapCommand.java#L31).

#### **Feature envy and message chains**
As the result of the **MVC** (Model-View-Controller) pattern some of the controllers use is narrowed to its model method calls. Our controller envies its model.
Also, in order to access a certain model's parameter it is mandatory to start by making a request to its controller.

## Testing

### Screenshot of coverage report
<p align="center" justify="center">
  <img src="images/screenshots/codeCoverage"/>
</p>
<p align="center">
  <b><i>Fig 6. Code coverage screenshot</i></b>
</p>

### Link to mutation testing report
[Mutation tests](../build/reports/pitest/202105302045/index.html)

## Self-evaluation

The work was divided in a mutual way and we all contributed with our best. It helped us to enrich our java and principle/pattern knwoledge, as well as our team work.

- Ana Beatriz Aguiar: 33.3%
- João Marinho: 33.3%
- Tiago Silva: 33.3%
