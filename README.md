# Super Mario Bros Java Implementation

A Java-based implementation of the classic Super Mario Bros game. This project recreates the nostalgic platforming experience of the original Nintendo game with modern programming techniques.

## 🎮 Features

- **Classic Mario Gameplay**: Authentic recreation of the original Super Mario Bros mechanics
- **Multiple Levels**: Explore various worlds and levels inspired by the original game
- **Power-ups**: Collect mushrooms, fire flowers, and stars with their original effects
- **Enemy AI**: Face classic enemies like Goombas, Koopas, and Piranha Plants
- **Pixel-Perfect Collision**: Responsive and accurate collision detection
- **Smooth Physics**: Realistic jumping and movement physics
- **Save Game Progress**: Continue your adventure where you left off
- **Custom Level Editor**: Create and share your own Mario levels

## 🔧 Installation

### Prerequisites
- Java JDK 11 or higher
- Maven (for dependency management)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/amabilisifi/supermario.git
   ```

2. Navigate to the project directory:
   ```bash
   cd supermario
   ```

3. Compile the project:
   ```bash
   mvn clean compile
   ```

4. Run the game:
   ```bash
   mvn exec:java -Dexec.mainClass="com.amabilisifi.supermario.SuperMarioGame"
   ```

## 🕹️ How to Play

### Controls
- **Arrow Keys**: Move Mario left/right and crouch
- **Space**: Jump
- **Z**: Run/Fire (when you have the Fire Flower)
- **P**: Pause game
- **ESC**: Open menu
- **S**: Save game

### Gameplay
1. Navigate through levels while avoiding or defeating enemies
2. Collect coins for extra lives and points
3. Find power-ups to enhance Mario's abilities
4. Break blocks to find hidden items and secret areas
5. Reach the flagpole at the end of each level to progress

## 📂 Project Structure

```
supermario/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── amabilisifi/
│   │   │           └── supermario/
│   │   │               ├── entity/
│   │   │               │   ├── Mario.java
│   │   │               │   ├── Enemy.java
│   │   │               │   └── ...
│   │   │               ├── level/
│   │   │               │   ├── Level.java
│   │   │               │   ├── LevelLoader.java
│   │   │               │   └── ...
│   │   │               ├── physics/
│   │   │               ├── graphics/
│   │   │               ├── audio/
│   │   │               ├── ui/
│   │   │               ├── util/
│   │   │               ├── SuperMarioGame.java
│   │   │               └── ...
│   │   └── resources/
│   │       ├── sprites/
│   │       ├── audio/
│   │       ├── levels/
│   │       └── ...
│   └── test/
│       └── java/
│           └── com/
│               └── amabilisifi/
│                   └── supermario/
│                       └── ...
├── pom.xml
├── LICENSE
└── README.md
```

## 🛠️ Development

### Creating Custom Levels
1. Launch the game and select "Level Editor" from the main menu
2. Design your level using the drag-and-drop interface
3. Save your level file to `src/main/resources/levels/custom/`
4. Your level will appear in the "Custom Levels" section

### Adding New Features
1. Fork the repository
2. Implement your feature or fix
3. Write tests for your implementation
4. Submit a pull request with a detailed description of your changes

## 🐛 Known Issues

- Full-screen mode may cause rendering issues on some Linux distributions
- Controller support is experimental and may not work with all controllers
- Sound may occasionally desynchronize on slower systems

## 🗺️ Roadmap

- [ ] Multiplayer support
- [ ] Additional worlds from Super Mario Bros 3
- [ ] Mobile touch controls
- [ ] Performance optimizations
- [ ] Additional characters (Luigi, Toad, etc.)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please ensure your code follows the project's coding standards and includes appropriate tests.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


For bug reports and feature requests, please use the [GitHub Issues](https://github.com/amabilisifi/supermario/issues) page.
