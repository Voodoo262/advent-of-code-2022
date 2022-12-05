// https://adventofcode.com/2022/day/1#part2
def current = 0;
def leaderboard = new PriorityQueue(new Comparator<Integer>() {
    public int compare(Integer elf1, Integer elf2) {
        return elf2 - elf1;
    }
});
def file = new File('day-01.input.txt');
file.withReader { reader ->
    def line = null;
    // Loop through the file one line at a time, attempting to parse each line into an int
    // If parsing fails, we'll assume we encountered a blank/empty line, which means we need
    // to reset our "current" counter. Every time we reset our counter, we'll add the value
    // to our queue
    while ((line = reader.readLine()) != null) {
        try {
            current += Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            leaderboard.add(current);
            current = 0;
        }
    }
    if (current > 0) {
        leaderboard.add(current);
    }
}

// Grab the top 3 entries, erroring if we have less than 3
def total = 0;
for (def i = 0; i < 3; i++) {
    def val = leaderboard.poll();
    if (val == null) {
        throw new RuntimeException("less than 3 elements in our queue");
    }
    total += val;
}
println(total);
