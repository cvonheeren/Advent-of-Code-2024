class Limits {
    def grid
    def original
    def rows
    def cols
    def startPosition
    def safe
    def guard
    def obstacle
    def actualDir
    def directions = [
            [-1, 0],
            [1, 0],
            [0, -1],
            [0, 1],
        ]

    Limits(grid) {
        this.grid = grid
        this.original = grid.collect { it.collect { it } }
        this.rows = grid.size()
        this.cols = grid[0].size()
        this.startPosition = startPos()
        this.safe =  this.startPosition
        this.obstacle = "#"
        setDir()
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
        while (true) {
            if (!walk(startPosition[0], startPosition[1], actualDir[0], actualDir[1])) {
                break;
            }
        }
        def positions = []
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            def row = grid[rowIndex]
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                if (row[colIndex] == 'X') {
                    positions << [rowIndex, colIndex]
                }
            }
        }
        int trappedCount = 0
        
        for (def pos : positions) {
            reset()
            grid[pos[0]][pos[1]] = '#'
            def i = 0
            while (true) {
                if (!walk(startPosition[0], startPosition[1], actualDir[0], actualDir[1])) {
                    i = 0
                    break
                } else if (i > 10000) {
                    trappedCount++
                    break
                } else{
                    i++
                }     
            }
            grid[pos[0]][pos[1]] = 'X'
        }

        return trappedCount
    }

    boolean walk(int x, int y, int dx, int dy) {
        int newX = x + dx
        int newY = y + dy
        
        if (newX < 0 || newX >= rows || newY < 0 || newY >= cols) {
            return false
        }

        if (grid[newX][newY] == obstacle) {
            swapGuard()
            setDir()
            return true
        }
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
    
    void reset(){
        this.grid = original.collect { it.collect { it } }
        this.startPosition = startPos()
        setDir()
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
def myGame = new Limits(grid)
println myGame.play()