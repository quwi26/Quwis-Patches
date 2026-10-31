<h1 align="center">Noam's Morphe Patches Stockfish+</h1>

<p align="center">
  A fork of <a href="https://github.com/ImNoammm/Noams-Morphe-Patches">Noam's Morphe Patches</a> for chess.com only,
  with a fourth Game Review option: Stockfish on your computer.
</p>

<p align="center">
  <a href="https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/releases/latest"><img alt="Latest release" src="https://img.shields.io/github/v/release/quwi26/Noams-Morphe-Patches-StockfishPlus?label=release"></a>
  <img alt="Morphe patches" src="https://img.shields.io/badge/Morphe-patches-3DDC84?logo=android&logoColor=white">
  <a href="LICENSE"><img alt="GPL-3.0" src="https://img.shields.io/badge/license-GPL--3.0-lightgrey"></a>
</p>

Add this source to [Morphe](https://morphe.software) Manager once:

```
https://raw.githubusercontent.com/quwi26/Noams-Morphe-Patches-StockfishPlus/main/patches-bundle.json
```

## What changed from Noam's

- **Game Review gets a fourth reviewer: “Stockfish on your computer.”** Instead of searching on the phone, the
  review is searched by [`sf-bridge`](#stockfish-on-your-computer) on your computer over the local network: the same
  Stockfish 19, on all of that machine's threads, and the same Lichess-style marks come back. A review that took
  a minute on the phone takes seconds.
- **The phone and the computer have their own depth** (Fast 100k, Standard 300k, Deep 1M, Maximum 5M nodes a move):
  more than 100k is rarely worth it on a phone, while 300k is a fraction of a second on a computer.
- **A benchmark for the computer** in the settings: times those four budgets in a row, with Stockfish's start-up
  (paid once per review) measured separately.
- **Great moves**, chess.com's “!”: the engine's own best move when it was also the only one that kept the
  game good (PrathxmOp's `greatFind` rule, on the second-best line's expected points). It needs two lines
  from the engine, so it has a switch in the settings; Lichess's own reviews are left as Lichess sends them.
- **Brilliant moves**, chess.com's “!!”: a best or nearly best move that offers material for good
  (PrathxmOp's rule, with the sacrifice read by static exchange on the position after the move — in the
  engine's line an ordinary trade is briefly down a piece too, which used to mark quiet moves). It costs
  the engine nothing extra. The settings list the brilliant moves the reviews saved on this phone
  found — chess.com counts its own Insights on its servers, and only for Diamond.
- The patches for other apps (Block Blast, Gboard, Geocaching) are **not** in this bundle.

Everything else is Noam's work, unchanged: chess.com 4.10.18 with arrows in games, board and accent colours,
AMOLED black, the website's Arcade animations, custom Home and bottom bar, time controls under a minute,
offline Stockfish analysis on the phone, Lichess reviews and offline Lichess puzzles, every bot unlocked,
no daily Play Coach limit, Premium content, no ads or rating prompts — all switched on and off under
**More → Noam's Patches**.

## Stockfish on your computer

The computer runs `sf-bridge`, which starts a Stockfish per connection and passes UCI through a plain TCP socket:

```
server: sf-bridge 1 cores 12
client: auth <token>
server: auth ok
... plain UCI from there
```

Then in the app: **More → Noam's Patches → Game Review → Stockfish on your computer**, and fill in

- **Computer address** — `192.168.1.10:7892` (the computer's address on the same Wi-Fi);
- **Token** — the one sf-bridge prints when it starts (`token.txt` next to it);
- **Computer Stockfish depth** — its own budget, apart from the phone's;
- **Computer Stockfish threads** — Auto uses every core the computer reports, with 32 MB of hash each.

Press **Test the connection**; it should answer “The computer answered: its Stockfish is ready.”
**Benchmark your computer** then runs 100k, 300k, 1M and 5M nodes on one position each, over one engine,
with the start-up time kept separate — on an i5-12450H that is 0.57 s to start and 0.09 / 0.14 / 0.32 /
1.49 s for the four budgets. The review chooser offers **Stockfish on your computer** next to chess.com,
Local Stockfish and Lichess.

### The bridge itself

It is in [`bridge/`](bridge/README.md) of this repository: one `server.js` with no dependencies, plus
`test-tcp.mjs` and `bench-tcp.mjs` to check it from a terminal. In short:

1. Install [Node.js](https://nodejs.org) and download [Stockfish](https://stockfishchess.org/download/);
2. put `server.js` and `stockfish.exe` in one folder and run `node server.js`;
3. allow TCP 7892 through the firewall (the exact command is in [bridge/README.md](bridge/README.md));
4. type the address and the token it prints into the app, and press **Test the connection**.

Nothing is sent anywhere else: the bridge only waits for your phone on your own network, and the
phone talks to your computer on that network only.

## Credits

All patches are [Noam's](https://github.com/ImNoammm/Noams-Morphe-Patches) (GPL-3.0), which itself builds on
PrathxmOp's work; the computer reviewer was added in this fork. If you want the patches for other apps too,
use Noam's own source.

## Building

```
./gradlew :patches:generatePatchesList   # patch list, then...
./gradlew :patches:buildAndroid          # ...the bundle with classes.dex (assemble alone has no dex)
```

The bundle lands in `patches/build/libs/patches-<version>.mpp`. Building needs JDK 21+, the Android SDK
(`ANDROID_HOME`), and GitHub Packages credentials for the Morphe plugin (`gpr.user` / `gpr.key` in
`~/.gradle/gradle.properties`).
