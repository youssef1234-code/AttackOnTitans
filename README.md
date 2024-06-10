# Attack on Titan: Utopia

## Overview
Attack on Titan: Utopia is a java-based game developed as part of a Computer Programming Lab course at the German University in Cairo. The game involves strategic deployment of weapons and titans in different lanes to protect a base from being overrun by titans.

## Features
- **Titans**: Multiple types of titans, each with unique abilities.
- **Weapons**: Various weapons with distinct attack features.
- **Lanes**: Titans and weapons interact within different lanes, and the danger levels are dynamically updated.
- **Game Phases**: The game progresses through different phases, each with increasing difficulty.

## Installation

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Java FX 11 or higher
- IDE or text editor for Java development

### Steps
1. **Download the repository**: Clone the repository from GitHub.
   ```sh
   git clone https://github.com/youssef1234-code/AttackOnTitans.git
   ```
2. **Extract the files**: If you have downloaded a ZIP file, extract it to your desired location.

3. **Navigate to the project directory**:
   ```sh
   cd attack-on-titan-utopia
   ```

4. **Compile the project**:
   ```sh
   javac -d bin src/**/*.java
   ```

5. **Run the game**:
   ```sh
   java -cp bin game.gui.MainMenu
   ```

## Usage
The game is played through the console. Players will deploy weapons, manage resources, and fend off titans as they progress through different phases of the game. Here are some key actions:

1. **Purchase Weapon**: Deploy a specific weapon in a selected lane.
2. **Pass Turn**: Skip buying weapons and proceed with the game actions.
3. **Check Game Status**: Determine if the game is over or proceed to the next turn.
4. **Automate Turn**: Determine automatically the most efficient decision in that turn

## Class Structure

### Interfaces
- **Attackee**: Methods for objects that can be attacked.
  - `boolean isDefeated()`
  - `int takeDamage(int damage)`
- **Attacker**: Methods for objects that can attack.
  - `int attack(Attackee target)`
- **Mobil**: Methods for objects that can move.
  - `boolean hasReachedTarget()`
  - `boolean move()`

### Key Classes
- **Titans**: Different types of titans with special features.
- **Weapons**: Different types of weapons with unique attack mechanisms.
- **Lane**: Manages titans and weapons within a lane.
- **TitanRegistry**: Spawns titans based on the registry code.
- **WeaponRegistry**: Builds weapons based on the registry code.
- **WeaponFactory**: Manages the purchase of weapons.
- **Battle**: Represents the game engine that manages the game flow.

## Contribution
We welcome contributions from the community. Here’s how you can help:

1. **Fork the repository** on GitHub.
2. **Clone your fork**:
   ```sh
   git clone https://github.com/yourusername/attack-on-titan-utopia.git
   ```
3. **Create a new branch**:
   ```sh
   git checkout -b feature-branch
   ```
4. **Make your changes** and commit them:
   ```sh
   git commit -m "Description of your changes"
   ```
5. **Push your changes**:
   ```sh
   git push origin feature-branch
   ```
6. **Create a pull request** on GitHub.

## Game Preview
![image](https://github.com/youssef1234-code/AttackOnTitans/assets/58553094/dd23e3c8-567d-4afa-b108-5985932ce360)





![image](https://github.com/youssef1234-code/AttackOnTitans/assets/58553094/7d02b4f4-cd02-4964-a5c1-8fadd943241d)





![image](https://github.com/youssef1234-code/AttackOnTitans/assets/58553094/acfb880e-f993-4370-ac27-0fc9edabcd32)




![image](https://github.com/youssef1234-code/AttackOnTitans/assets/58553094/707828d2-4df1-442e-9e31-0b04386276b6)



## License
This project is licensed under the MIT License. See the LICENSE file for more details.

## Acknowledgements
- Prof. Dr. Slim Abdennadher
- Dr. Nourhan Ehab
- Dr. Ahmed Abdelfattah
- German University in Cairo, Media Engineering and Technology Department

For any issues or questions, please open an issue on GitHub.
