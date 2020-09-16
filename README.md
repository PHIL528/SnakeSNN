# SnakeSNN

![Alt text](EclipseSnake2.png?raw=true "Title")

To install in Eclipse:
1) Add Egit
File > New > Project > Maven > Check out Maven Projects from SCM > Click m2e Marketplace > Search egit > Click & Finish.
2) Install SnakePRJ
File > New > Project > Maven > Check out Maven Projects from SCM > put in https://github.com/PHIL528/SnakeSNN for git > Finish
3) Configure SnakePKG.SnakeMain as Main Class
Run > Run Configurations > Project: Browse for "SnakeSNN" && Main Class: Browse for "SnakePKG.SnakeMain"


I built this project with Processing PDE, a Java compatible display system. You can either play snake, or let a spiking neural network (in this case resevoir computer) learn how to play snake. It learns/evolves by randomly adjusting connections and testing if the adjustment improves the score, and keeps the one which is better. 


To Play Snake:
1) Run
2) Click the display window
3) Press Ctrl, and then use the Up/Down/Left/Right arrow keys


To create a new SNN and train it:
1) (Do not change "static int cores = 1;" or it will devolve)
2) Run
3) Click the display window
4) Press s for SNN mode
5) Press p to create a new file    //This will overwrite the old file if one exists
6) Press d to start evolution
7) Use {(FAST) j, k, l (SLOW)}  
8) Press t to save and terminate. It will automatically save every so often


To use the existing SNN file:
1) (Do not change "static int cores = 1;" or it will devolve)
2) Run 
3) Click the display window
4) Press s for SNN mode
5) Press d to start evolution 



