def input = """3   4
4   3
2   5
1   3
3   9
3   3"""
def list1 = []
def list2 = []
def listRaw = input.split('\n')
listRaw.each { line ->
    def numbers = line.trim().split(/\s+/)
    if (numbers.size() == 2) {
        list1 << Integer.parseInt(numbers[0])
        list2 << Integer.parseInt(numbers[1])
    }
}

list1.sort()
list2.sort()
def distanT = 0
for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
    distanT = distanT + Math.abs(list1[i] - list2[i])
}
println "dist ${distanT}"

def similarity = 0
for (int i = 0; i < list1.size(); i++) {
    def counting = list2.count { it == list1[i] }
    similarity = similarity + (list1[i] * counting)
}
println "similarity ${similarity}"