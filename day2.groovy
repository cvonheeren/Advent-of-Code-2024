def input = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9"""
def listRaw = input.split('\n')
def securePlants = 0

listRaw.each { line ->
    def list2 = line.split(" ").collect { it.toInteger() }
    
    if (isValidWithOneSkip(list2)) {
        securePlants++
    }
}

println securePlants

def isValidWithOneSkip(list) {
    if (isValid(list)) {
        //println list
        return true
    }
    
    for (int i = 0; i < list.size(); i++) {
        def modifiedList = list[0..<i] + list[(i + 1)..<list.size()]
        if (isValid(modifiedList)) {
            //println modifiedList
            return true
        }
    }
    return false
}

def isValid(list) {
    def isAsc = isAsc(list)
    
    for (int i = 1; i < list.size(); i++) {
        if (isAsc) {
            if (list[i - 1] >= list[i]) {
                return false
            }
        } else {
            if (list[i - 1] <= list[i]) {
                return false
            }
        }
        
        if (Math.abs(list[i] - list[i - 1]) > 3) {
            return false
        }
    }
    return true
}

def isAsc(list) {
    if (list[0] == list[1]) {
        return null
    }
    return list[0] < list[1]
}