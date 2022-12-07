// https://adventofcode.com/2022/day/5
def file = new File('day-05.input.txt');
def stackCount = 0;
ArrayList<Stack<Character>> stacks;
file.withReader { reader ->
    def line = null;
    
    // Scan crate arrangement top to bottom
    while ((line = reader.readLine()) != null) {
        // If we haven't yet determined the number of stacks (meaning we're on the first line of the file), we'll calculate it and initialize the data structures
        if (stackCount == 0) {
            stackCount = (line.length() + 1)/4;
            stacks = new ArrayList<Stack<Character>>((int)stackCount);
            for (def i = 0; i < stackCount; i++) {
                stacks[i] = new Stack<Character>();
            }
        }
        if (!line.contains('[')) break;
        for (def i = 0; i < stackCount; i++) {
            def contents = line.charAt(i * 4 + 1);
            if (contents != ' '.charAt(0)) {
                stacks[i].push(contents);
            }
        }
    }

    // Reverse stacks (since we scanned them in top to bottom but we want them to be ordered bottom to top)
    for (def i = 0; i < stackCount; i++) {
        def newStack = new Stack<Character>();
        while (!stacks[i].empty()) {
            newStack.push(stacks[i].pop());
        }
        stacks[i] = newStack;
    }
    
    // Scan crane operations
    while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) continue; // skip blank line(s) in file
        def matches = line =~ /move (\d+) from (\d+) to (\d+)/
        def numberOfCratesToMove = Integer.parseInt(matches[0][1]);
        def sourceStack = Integer.parseInt(matches[0][2]);
        def targetStack = Integer.parseInt(matches[0][3]);
        for (def i = 0; i < numberOfCratesToMove; i++) {
            stacks[targetStack-1].push(stacks[sourceStack-1].pop());
        }
    }
}

// Print out name of crate on top of each stack
for (def i = 0; i < stackCount; i++) {
    print(stacks[i].pop());
}
println();
