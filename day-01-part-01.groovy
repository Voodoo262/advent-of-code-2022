// https://adventofcode.com/2022/day/1
def current = 0, max = 0;
def file = new File('day-01.input.txt');
file.withReader { reader ->
    def line = null;
    // Loop through the file one line at a time, attempting to parse each line into an int
    // If parsing fails, we'll assume we encountered a blank/empty line, which means we need
    // to reset our "current" counter. Every time we reset our counter, or once we reach the
    // end of the file, we'll update our max to be the greater of the previous max or
    // current counter
    while ((line = reader.readLine()) != null) {
        try {
            current += Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            max = Math.max(max, current);
            current = 0;
        }
    }
    max = Math.max(max, current);
}
println(max);
