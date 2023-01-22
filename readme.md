# Flowers Farm command line game
## About
This is a command line game in which user can create multiple *farmers* and on their behalf take care of a *flowers farm* - buy, plant, water, soil, harvest and sell *flowers*. It is written in *java* and uses *SQLite* database.
## How to run
1. Clone this repository and `cd` into `flowers_farm_game`. 
2. Compile files:
```console  
javac -d bin -cp 'lib/*' src/dao/*.java src/farm/*.java src/farmer/*.java src/market/*.java src/panels/*.java src/plants/*.java
```
3. Run `MainPanel`:
```console
java -cp 'lib/*:bin' src.panels.MainPanel
```
