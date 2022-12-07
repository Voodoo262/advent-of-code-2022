// https://adventofcode.com/2022/day/3#part2
/* Solution approach:
 * Use a single map to track the number of elves that have each item. After we've looped through all the elves' items in a 
 * given group, we'll examine the map to find which item exists in all of the elves' lists. Once we've identified the item,
 * we'll increment our sum by that item's weight ("priority") and clear the map
 */
def sum = 0;
def file = new File('day-03.input.txt');
def elfCount = 3;
file.withReader { reader ->
    def line = null;
    def elfNumber = 0;
    def items = [:]; // map where key is the item and value is the number of elves that have that item. we'll only increment the value if the current elfNumber is contiguous
    def groupNumber = 0;
    while ((line = reader.readLine()) != null) {
        elfNumber++;
        line.toCharArray().each { item ->
            def numberOfElvesWithItem = 0;
            if (items.containsKey(item)) {
                numberOfElvesWithItem = items[item];
            }
            if (elfNumber - 1 == numberOfElvesWithItem) {
                items[item] = numberOfElvesWithItem + 1;
            }
        }

        if (elfNumber >= elfCount) {
            // we've reached the end of the group of elves. we need to do something here
            elfNumber = 0;
            groupNumber++;
            for (def kvp : items) {
                if (kvp.value == elfCount) {
                    // We found the item that all elves had. Let's add it to the sum
                    println("Group #$groupNumber, all elves have ${kvp.key}")
                    sum += getPriorityOfItem(kvp.key);
                    break;
                }
            }
            items.clear();
        }
    }
}
println(sum);

int getPriorityOfItem(Character item) {
    if (item >= 'a'.charAt(0) && item <= 'z'.charAt(0)) {
        return (int)(item - 'a'.charAt(0)) + 1;
    } else if (item >= 'A'.charAt(0) && item <= 'Z'.charAt(0)) {
        return (int)(item - 'A'.charAt(0)) + 27;
    }
    throw new RuntimeException("Invalid item: $item");
}
