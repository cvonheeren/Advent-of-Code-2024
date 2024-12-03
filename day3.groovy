import groovy.lang.GroovyShell

def largeString = '''
xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
'''
def cleanedString = new StringBuilder()
def flag = 0
for (int i = 0; i < largeString.size(); i++) {
    if (i <= largeString.size() - 7 && list[i..i + 6].equals("don't()")) {
        flag = 1;
        i += 6
        continue
    }
    if (i <= largeString.size() - 4 && largeString[i..i + 3] == "do()") {
        flag = 0
    }
    if (!flag)
        cleanedString << largeString[i]
}

def regex = ~/mul\((\d+),(\d+)\)/
def matches = cleanedString.toString().findAll(regex)

def shell = new GroovyShell()
def result = 0;
matches.each { match ->
    def script = """
        def mul(n1, n2) {
            return n1 * n2
        }
        return ${match}
    """    
    result = result + shell.evaluate(script)
}
println result

def mul(n1, n2) {
    return n1 * n2
}