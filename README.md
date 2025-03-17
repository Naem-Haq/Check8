# Factions - Turn-Based Fighting Game

## Check8 - Team Members
- **Ellice Nelson** (23371323)
- **Michelle Vaz** (23233761)
- **Naem Haq** (23379243)

## Overview
Factions is a strategic turn-based fighting game where players can choose unique characters to battle against AI opponents or other players. The game integrates multiple software design patterns to enhance maintainability, scalability, and robustness.

## Game Features
- **Character Selection**: Players can choose different characters, each with unique weapons and abilities.
- **Turn-Based Combat**: Players select actions such as attacking, dodging, or using potions in a strategic manner.
- **AI Opponents**: Play against intelligent computer-controlled characters.
- **Health Management**: Monitor health bars and use potions to stay in the fight.
- **Statistics Tracking**: Track wins, losses, and overall performance.
- **Leaderboards**: Compare stats with other players.

## Design Patterns Implemented
- **Chain of Responsibility**: Handles CPU decision-making based on health status.
- **Memento**: Saves and restores game states for undo functionality.
- **Observer**: Updates health bars dynamically and tracks game progress.
- **Factory Method**: Creates characters dynamically based on player selection.
- **Command**: Encapsulates character actions into independent objects.
- **State**: Manages different phases of the game (e.g., Ready, In-Progress, Game Over).
- **Interceptor**: Pre-processes login requests for validation and logging.

## How to Play
1. **Sign Up or Log In**: Create an account or log in to track progress.
2. **Main Menu**: Select options such as starting a game, viewing stats, or choosing a character.
3. **Character Selection**: Choose a unique fighter with specialized abilities.
4. **Battle Begins**: Each turn, select an action:
   - **Attack**: Deal damage to the opponent.
   - **Dodge**: Try to evade an attack.
   - **Use Potion**: Heal or enhance attack power.
5. **Winning and Losing**: The battle ends when one fighter's health reaches zero.

## Additional Features
- **Track Stats**: View battle history and overall performance.
- **Change Characters**: Experiment with different fighters and strategies.
- **Leaderboards**: Compare skills with other players.

## Software Architecture
- **Monolithic & Modular Approach**: A structured, modular architecture that supports easy expansion.
- **Model-View-Controller (MVC)**: Enhances maintainability by separating concerns.

## Testing and Development
- **Automated Testing**: Uses Maven for automated test execution.
- **CI/CD Integration**: GitHub workflows ensure code quality and prevent faulty merges.
- **Logging**: Implements logging for debugging and tracking system behavior.
- **Version Control**: GitHub branching strategy ensures smooth collaboration.

## Installation & Setup
```sh
# Clone Repository
git clone https://github.com/Naem-Haq/Check8.git

# Build the Project
mvn clean install

# Run the Game
java -jar check8.jar
