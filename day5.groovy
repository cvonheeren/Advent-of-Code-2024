def input1 = """47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13"""

def input2 = """75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47"""

def rules = input1.split('\n')
def solutions = input2.split('\n')
def validSolutions = []
def invalidSolutions = []

for (int i = 0; i < solutions.size(); i++) {
    def nums2 = solutions[i].split(',') as List
    boolean isValid = true

    for (rule in rules) {
        def nums = rule.split('\\|')
        def firstNum = nums[0]
        def secondNum = nums[1]

        int firstIndex = nums2.indexOf(firstNum)
        int secondIndex = nums2.indexOf(secondNum)

        if (firstIndex != -1 && secondIndex != -1 && firstIndex < secondIndex) {
            continue
        } else if (firstIndex != -1 && secondIndex == -1) {
            continue
        } else if (firstIndex > secondIndex) {
            isValid = false
            break
        }
    }
    
    if (isValid) {
        validSolutions << nums2
    } else {
        invalidSolutions << nums2
    }
        
}

println calculateSolution(validSolutions)

int sol = getCont(invalidSolutions, rules)
while (1) {
    def tempSol = getCont(invalidSolutions, rules)
    if (sol != tempSol) {
        sol = tempSol
    } else {
        break
    }
}
println sol

int getCont(def invalidSolutions, def rules) {
    for (int i = 0; i < invalidSolutions.size(); i++) {
        for (rule in rules) {
            def nums2 = rule.split('\\|')
            def firstNum2 = nums2[0]
            def secondNum2 = nums2[1]
            int firstIndex2 = invalidSolutions[i].indexOf(firstNum2)
            int secondIndex2 = invalidSolutions[i].indexOf(secondNum2)
            
            def indexB = -1
            for (int j = 0; j < invalidSolutions[i].size(); j++) {
                
                if (firstIndex2 != -1 && secondIndex2 != -1 && firstIndex2 > secondIndex2) {
                    if (invalidSolutions[i][j] == secondNum2) {
                        indexB = j
                    }
                    if (indexB != -1 && invalidSolutions[i][j] == firstNum2) {
                        def temp = ""
                        temp = invalidSolutions[i][indexB]
                        invalidSolutions[i][indexB] = invalidSolutions[i][j]
                        invalidSolutions[i][j] = temp
                    }
                }
            }
        }
    }
    
    return calculateSolution(invalidSolutions)
}

int calculateSolution(def list) {
	def cont = 0
    list.each { validList ->
        int centerIndex = validList.size() / 2
        def centerNumber = validList[centerIndex]
        cont += centerNumber.toInteger()
    }
    return cont
}














