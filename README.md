# Ayoayo Game (Text-Based Java Implementation)

## Overview

This is a text-based Java implementation of the traditional Ayoayo board game, created as part of the Software Developer Take-Away Assignment.

The game allows two players to take turns picking pits, sowing seeds, and following the standard rules of Ayoayo including special rules for extra turns and capturing. The implementation adheres to object-oriented programming principles, with a clean separation of concerns between the `Player` and `Ayoayo` classes.

## Approach

The project is structured into three main files:

- Player.java: Represents a player, their six pits, and their seed store.
- Ayoayo.java: Contains the core game logic including sowing, capturing, ending conditions, and board management.
- Main.java: Demonstrates how the game can be played via method calls in a simple simulation.

### Key Concepts:
- **Encapsulation**: All class data members are private and accessed through methods.
- **Game Rules**: Fully implemented as per the official Ayoayo rules including:
  - Extra turn if the last seed lands in the player’s store.
  - Capturing if the last seed lands in an empty pit and the opposite pit has seeds.
  - Game ends when one player has no seeds in their pits.


## Assumptions Made

- Each pit is initialized with **4 seeds**.
- Players are always added in the order: **Player 1 first, then Player 2**.
- Player 1 owns the **bottom row**, Player 2 owns the **top row** (as per original game layout).
- Input is not interactive (per the assignment) but tested through a static `Main.java` class.
- playGame() method is called with player index (1 or 2) and pit index (1 to 6), matching the instructions.

## How to Run This Project

### Prerequisites:
- Java JDK 17 or higher installed
- Git (optional, for cloning)
- Terminal or command prompt

---

### 1. Clone the Repository

If you have Git installed, run:

```bash
git clone https://github.com/your-username/ayoayo-game.git
cd ayoayo-game
```
If you don’t have Git, you can:

Click the green "Code" button on the GitHub page

Download the ZIP

Extract it and open the folder in your terminal

### 2. Compile the Project
Make sure you're inside the project root directory, then run:
```bash
javac -d out src/com/ayoayo/*.java
```
This compiles all your .java files and places the .class files inside the out directory.
### 3. Run the Game
```bash
java -cp out com.ayoayo.Main
```
You should see the game start running in your terminal and simulate some sample moves.


## Example output
player 1 take another turn

player1:

store: 10

[0, 0, 2, 7, 7, 6]

player2:

store: 2

[5, 0, 1, 1, 0, 7]

Game has not ended


