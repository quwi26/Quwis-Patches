# [1.6.16](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.15...v1.6.16) (2026-10-31)

### Bug Fixes

* **Chess.com:** a sacrifice in an already won game is no longer marked Brilliant, where the old "winning outright" cutoff let a +9 position through and the mark depended on the review depth

# [1.6.15](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.14...v1.6.15) (2026-10-31)

### Bug Fixes

* **Chess.com:** Noam's Patches is back in the More tab on 4.10.23, where the row model keeps only its Kotlin constructor with default values

# [1.6.14](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.13...v1.6.14) (2026-10-31)

### Bug Fixes

* **Chess.com:** a sacrifice that leaves the game dead equal is no longer marked Brilliant: a bishop given for a pawn in a drawn endgame sat exactly on the 50% boundary of expected points

# [1.6.13](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.12...v1.6.13) (2026-10-31)

### New Features

* **Chess.com:** chess.com 4.10.23 is supported next to 4.10.18, with every patch in this bundle working on either version
* **Chess.com:** in 4.10.23 bot games, where the bar takes no buttons, a small chip over the board switches Arrows and clears the marks

### Improvements

* **Chess.com:** the engine review and the local Stockfish analysis read 4.10.23's renamed PGN parser, position accessors, move converter and review data classes
* **Chess.com:** the manager's changes button now shows this changelog instead of a single description

# [1.6.12](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.11...v1.6.12) (2026-10-31)

### New Features

* **Chess.com:** brilliant moves found by your reviews open the game at that move on chess.com when the review saved the game's address
* **Chess.com:** a "Report a wrong mark" row opens a GitHub issue with fields for the game, the move and what chess.com says

### Improvements

* **Chess.com:** a saved review keeps the game's address next to the moves, and reviews saved by an older version pick it up when the game is opened again

# [1.6.11](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.10...v1.6.11) (2026-10-31)

### Bug Fixes

* **Chess.com:** in the Brilliant check an ordinary trade no longer counts as a sacrifice, and a check only offers the checking piece

# [1.6.10](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.9...v1.6.10) (2026-10-31)

### Bug Fixes

* **Chess.com:** a pawn no longer counts as attacking every square on its file, which had marked some moves Brilliant that were not

# [1.6.9](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.8...v1.6.9) (2026-10-31)

### Improvements

* **Chess.com:** Brilliant is now checked as a real sacrifice by static exchange instead of material in the engine's line, so quiet moves are not marked for a brief loss in a trade
* **Chess.com:** a move within 2% of the best one can still be Brilliant, as chess.com puts it

# [1.6.8](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.7...v1.6.8) (2026-10-30)

### New Features

* **Chess.com:** Brilliant moves are marked in engine reviews
* **Chess.com:** Noam's Patches lists the brilliant moves your saved reviews found

# [1.6.7](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.6...v1.6.7) (2026-10-30)

### New Features

* **Chess.com:** Great moves are marked in engine reviews, with a switch in Noam's Patches
* **Chess.com:** the engine searches two lines per move, which is how a Great move is told from the only good one
* **Chess.com:** Game Review depth is chosen per reviewer

# [1.6.6](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.5...v1.6.6) (2026-10-30)

### Improvements

* **Chess.com:** the settings say "your computer" everywhere, never the author's machine
* **Chess.com:** the bridge that runs Stockfish on your computer ships in the repository, with a README and test scripts

# [1.6.5](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.4...v1.6.5) (2026-10-30)

### New Features

* **Chess.com:** the phone and the computer have their own review depth
* **Chess.com:** a benchmark button measures the bridge before a review

# [1.6.4](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.3...v1.6.4) (2026-10-30)

### Improvements

* **Chess.com:** the bundle carries chess.com patches only

# [1.6.3](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.2...v1.6.3) (2026-10-30)

### Bug Fixes

* **Chess.com:** Stockfish's threads and hash follow your computer, not the phone

# [1.6.2](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.1...v1.6.2) (2026-10-30)

### Improvements

* **Chess.com:** the bundle is called Noam's Morphe Patches Stockfish+

# [1.6.1](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.6.0...v1.6.1) (2026-10-30)

### Bug Fixes

* **Chess.com:** the bundle is built with its patch list and carries classes.dex, so Morphe loads it

# [1.6.0](https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/compare/v1.5.0...v1.6.0) (2026-10-30)

### New Features

* **Chess.com:** Game Review can run on Stockfish over your local network, on your own computer
