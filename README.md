## DnD dungeon game

This project is a turn-based battle simulation inspired by Dungeons & Dragons, developed as the final project for an Object-Oriented Programming course at Ben-Gurion University.

It demonstrates OOP principles and the use of key design patterns such as **Visitor** & **Observer**.

## Features

- Wide range of player characters and monsters.
- Turn-based combat system with actions and abilities.
- Modular design with support for future expansions.

## Technologies & Concepts

Language: Java
- OOP: Inheritance, encapsulation, polymorphism
- Design Patterns:
  - **Visitor** – for applying diffrent actions when a unit interacts with a tile.
  - **Observer** – for tracking and updating units (player & enemies) current states.
 
## How to Play

The game features a simple, text-based interface (GUI was not the main focus of the project, but the architecture allows for easily plugging in a more advanced GUI later).

When the game starts, you'll be prompted to choose your character by entering the number corresponding to one of the displayed character names.

Once the game begins, a map will be printed, and you'll see your position marked with an @ symbol.
Enemies appear as other characters — capital letters represent stronger (tougher) enemies.

On your turn, you can:

    Move using the keys:

        w = up

        a = left

        s = down

        d = right

    Use your special ability by pressing e

Combat is automatic:

    If you walk into a monster, you'll attack it.

    If a monster walks into you, it'll attack you.

Watch out!
Some monsters are invisible most of the time and may only appear occasionally. If you walk into one while it's hidden... it might be your last move.

There are 4 progressively challenging levels in the game.

Good luck, adventurer!

## How to Run

```bash
# Clone the repository
git clone https://github.com/nadavs/DnD-Battle-Simulator.git

# Open in your preferred IDE (IntelliJ / Visual Studio / etc.)

# Run the main class to start a battle simulation


