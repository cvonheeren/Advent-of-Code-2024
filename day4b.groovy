class WordSearch {
    def grid
    def rows
    def cols
    def count

    WordSearch(grid) {
        this.grid = grid
        this.rows = grid.size()
        this.cols = grid[0].size()
        this.count = 0
    }
 
    int check2(def tl, def tr, def bl, def br) {
        if (tl == 'M' && tr == 'M') {
            if (bl == 'S' && br == 'S') {
                return 1
            }
        }
        if (tl == 'M' && tr == 'S') {
            if (bl == 'M' && br == 'S') {
                return 1
            }
        }
        if (tl == 'S' && tr == 'M') {
            if (bl == 'S' && br == 'M') {
                return 1
            }
        }
        if (tl == 'S' && tr == 'S') {
            if (bl == 'M' && br == 'M') {
                return 1
            }
        }
        return 0
    }
    int searchWord() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j + 1 < rows && i + 1 < cols) {
                    if (grid[i + 1][j + 1] == 'A') {
                        if (j + 2 < rows && i + 2 < cols) {
                            if (check2(grid[i][j], grid[i + 2][j], grid[i][j + 2], grid[i + 2][j + 2])) {
                                this.count++
                            }
                        }
                    }
                }
            }
        }
        return count
    }
}

def input = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
"""

def grid = input.trim().split('\n').collect { it.toList() }

def wordSearch = new WordSearch(grid)
def occurrences = wordSearch.searchWord()

println "Número de coincidencias: $occurrences"