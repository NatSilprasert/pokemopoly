
# Pokemopoly Game
Pokemopoly is a turn-based digital board game built with Java and JavaFX. It draws inspiration from classic Monopoly but replaces property mechanics with Pokémon-themed systems such as catching, battling, evolving, and using items.


This project is the final project for the course Programming Methodology I 2110215.




![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765433498/Screenshot_2568-12-11_at_01.51.59_grnmkz.png)


## Gameplay
At the beginning of the game, players first select the number of participants (2–4 players). After that, each player chooses a Profession, which determines their starting Pokémon. Once a profession is selected, the player receives:
- 2 random item cards
- 5 pokeballs
- 10 coins

### 1. Player System
There are four available professions: **Trainer, Fisher, Scientist, and Rocket.**
Each profession comes with a unique starting Pokémon, giving players different strategic advantages from the very beginning of the game

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765434076/Screenshot_2568-12-11_at_13.20.54_y9dqqf.png)

### 2. Core Gameplay
In a player's turn, they can choose one of two actions:

2.1 Roll the dice to move across the board.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765434403/Screenshot_2568-12-11_at_13.25.57_vuvp84.png)

2.2 Use an item before rolling to gain a strategic advantage.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765434472/Screenshot_2568-12-11_at_13.27.04_uyey03.png)


### 3. Board and Tile System
The board consists of 40 tiles, each with a special effect:




| **Tile Type**            | **Effect** |
|--------------------------|------------|
| **Start Tile**           | Passing this tile gives the player the option to sell Pokémon from their team for coins. |
| **Grass Tile**           | Allows players to catch wild Pokémon. The Pokémon depends on the tile’s color (5 difficulty tiers). |
| **Event Tile**           | Player draws a random Event Card which may give buffs, debuffs, or special effects. |
| **Item Tile**            | Player draws a random Item Card. |
| **City Tile**            | Players can buy items and extra Pokéballs. |
| **Daycare Tile**         | Allows players to evolve a Pokémon on their team. |
| **Cave Tile**            | Acts as a trap. The player is stuck inside and skips their next turn. |
| **Battle Tile**   | Initiates a battle with Gym Leader 1, Gym Leader 2, or a Villain. |

### 4. Catching Pokemon Mechanism
When on a Grass Tile, player can choose to catch or ignore pokemon.
If the player choose to catch a pokemon, there are 2 steps:

4.1 Select a Pokéball, each ball has special ability:
- **Red Ball:** Normal catch condition.
- **Great Ball:** Catch difficulty decreases by 1.
- **Hyper Ball:** Catch difficulty decreases by 2.

  ![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765434961/Screenshot_2568-12-11_at_13.35.45_bokbgm.png)

4.2 Roll the dice, if the roll meets or exceeds the required value, the Pokémon is caught.

Players can have **up to 6 Pokémon** on their team.Pokémon **cannot be discarded**, and the only way to remove them is by **selling them when passing the Start Tile**.

### 5. Battle System
When landing on a Battle Tile, the player may choose to fight the boss.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765435243/Screenshot_2568-12-11_at_13.40.18_ka89ud.png)

- **Attack:** The player rolls the dice; if their roll is higher than the boss's, they attack and win.
- **Rewards:** Defeating Gym Leader 1 or 2 grants a badge (worth bonus coins). If the player already owns the badge, they receive +5 coins instead. Defeating the Villain grants +10 coins.
- **Penalty for losing:** The selected Pokémon’s HP becomes 0, rendering it unusable until healed.

### 6. Cards and Items
#### 6.1 Event Cards
Event Cards are triggered when a player lands on an **Event Tile**. These cards create unexpected situations that can help or hinder the player. Event effects are applied **immediately after drawing**, and most effects resolve instantly.

#### 6.2 Item Cards
Items can be used before starting a turn to give players advantages.
Each player can hold **up to 4 items** in their hand, if the hand is full, the player may **choose to discard an item** to free space before picking up a new one.

### 7. Evolution System
Players can evolve Pokémon in their team when they land on a **Daycare Tile**, allowing them to choose one eligible Pokémon to evolve per visit, and the evolved Pokémon will have its HP fully restored.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765442377/Screenshot_2568-12-11_at_15.38.44_oayzku.png)

### 8. Shop System
Players can access the Shop when landing on a **City Tile**. In the Shop, they can purchase items or additional Pokéballs using their coins.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765443415/Screenshot_2568-12-11_at_15.56.38_dxjcky.png)

### 9. Sell Pokemon
When players pass the **Start Tile**, they are given the option to sell Pokémon from their team. Each Pokémon has a defined value, and selling it adds coins equal to its price to the player’s total.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765442470/Screenshot_2568-12-11_at_15.40.57_dqyptq.png)

### 10. Game Finished
The game ends when a set number of total turns has been reached (50 turns per player). The winner is determined by total wealth: coins + value of all Pokémon + value from badges. The player with the highest combined value at the end of the game is crowned the winner.

![App Screenshot](https://res.cloudinary.com/denjyog53/image/upload/v1765444343/Screenshot_2568-12-11_at_16.12.02_yhfi38.png)
## Run the Game
- Download JavaFX
- Download the game’s JAR file from the /build/libs folder in this repository.
- Run this command
```bash
  java --module-path PATH_TO_JAVAFX \
     --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.fxml \
     -jar pokemopoly-1-with-sources.jar
```


## Contributors
- [@NatSilprasert](https://github.com/NatSilprasert)
- [@RyuJomphol](https://github.com/RyuJomphol)

