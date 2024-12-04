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

    int searchWord(String word) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == word[0]) {
                    searchInAllDirections(i, j, word)
                }
            }
        }
        return count
    }

   void searchInAllDirections(int x, int y, String word) {
        def directions = [
            [-1, 0],
            [1, 0],
            [0, -1],
            [0, 1],
            [-1, -1],
            [-1, 1],
            [1, -1],
            [1, 1]
        ]

        for (def dir : directions) {
            if (searchInDirection(x, y, word, dir[0], dir[1])) {
                this.count++
            }
        }
    }

    boolean searchInDirection(int x, int y, String word, int dx, int dy) {
        for (int i = 0; i < word.length(); i++) {
            int newX = x + i * dx
            int newY = y + i * dy
            if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || grid[newX][newY] != word[i]) {
                return false
            }
        }
        return true
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
def wordToSearch = "XMAS"
def occurrences = wordSearch.searchWord(wordToSearch)

println "La palabra '${wordToSearch}' aparece ${occurrences} veces."
