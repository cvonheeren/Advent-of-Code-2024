class Limits {
    def grid
    def rows
    def cols
    def startPosition
    def guard
    def obstacle
    def actualDir
    def directions = [
            [-1, 0], // Up
            [1, 0],  // Down
            [0, -1], // Left
            [0, 1],  // Right
        ]

    Limits(grid) {
        this.grid = grid
        this.rows = grid.size()
        this.cols = grid[0].size()
        this.startPosition = startPos()
        this.obstacle = "#"
        setDir() // Set the direction after finding the starting position
    }

    def startPos() {
        def position = [-1, -1]
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (this.grid[row][col] == '^' || this.grid[row][col] == '<' 
                    || this.grid[row][col] == '>' || this.grid[row][col] == 'v') {
                        
                        position = [row, col]
                        this.guard = this.grid[row][col]
                        break
                }
            }
            if (position[0] != -1) break 
        }
        return position
    }

    int play() {
        //println "Initial Direction: ${actualDir}"  // Debugging output
        while (true) {
            //println "Current Position: (${startPosition[0]}, ${startPosition[1]}), Direction: ${actualDir}"
            if (!walk(startPosition[0], startPosition[1], actualDir[0], actualDir[1])) {
                break; // Exit the loop if walk returns false
            }
        }
        grid.each { fila ->
            println fila.join(' ')
        }
        return countMarks()
    }

    boolean walk(int x, int y, int dx, int dy) {
        int newX = x + dx
        int newY = y + dy

        // Check for out of bounds
        if (newX < 0 || newX >= rows || newY < 0 || newY >= cols) {
            return false
        }

        // Check for obstacle
        if (grid[newX][newY] == obstacle) {
            //println "Obstacle encountered at (${newX}, ${newY})."
            swapGuard()
            setDir() // Update direction after hitting an obstacle
            return true // Continue walking after changing direction
        }

        // Move the guard to the new position (if no obstacle)
        // Mark the path with 'X'
        grid[newX][newY] = 'X'
        startPosition[0] = newX
        startPosition[1] = newY
        return true
    }

    void setDir() {
        if (guard == "^") {
            actualDir = directions[0]
        } else if (guard == "v") {
            actualDir = directions[1]
        } else if (guard == "<") {
            actualDir = directions[2]
        } else if (guard == ">") {
            actualDir = directions[3]
        }
    }

    void swapGuard() {
        if (guard == "^") {
            guard = ">"
        } else if (guard == "v") {
            guard = "<"
        } else if (guard == "<") {
            guard = "^"
        } else if (guard == ">") {
            guard = "v"
        }
    }

    int countMarks() {
        int count = 0
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 'X') {
                    count++
                }
            }
        }
        return count
    }
}

def input = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
"""

def grid = input.trim().split('\n').collect{ it.toList() }
//println grid
def myGame = new Limits(grid)
println myGame.play()