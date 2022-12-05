// https://adventofcode.com/2022/day/3
/*
--- Day 3: Rucksack Reorganization ---
One Elf has the important job of loading all of the rucksacks with supplies for the jungle journey. Unfortunately, that Elf didn't quite follow the packing instructions, and so a few items now need to be rearranged.

Each rucksack has two large compartments. All items of a given type are meant to go into exactly one of the two compartments. The Elf that did the packing failed to follow this rule for exactly one item type per rucksack.

The Elves have made a list of all of the items currently in each rucksack (your puzzle input), but they need your help finding the errors. Every item type is identified by a single lowercase or uppercase letter (that is, a and A refer to different types of items).

The list of items for each rucksack is given as characters all on a single line. A given rucksack always has the same number of items in each of its two compartments, so the first half of the characters represent items in the first compartment, while the second half of the characters represent items in the second compartment.

For example, suppose you have the following list of contents from six rucksacks:
<code>
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
</code>
<ul>
    <li>The first rucksack contains the items vJrwpWtwJgWrhcsFMMfFFhFp, which means its first compartment contains the items vJrwpWtwJgWr, while the second compartment contains the items hcsFMMfFFhFp. The only item type that appears in both compartments is lowercase p.</li>
    <li>The second rucksack's compartments contain jqHRNqRjqzjGDLGL and rsFMfFZSrLrFZsSL. The only item type that appears in both compartments is uppercase L.</li>
    <li>The third rucksack's compartments contain PmmdzqPrV and vPwwTWBwg; the only common item type is uppercase P.</li>
    <li>The fourth rucksack's compartments only share item type v.</li>
    <li>The fifth rucksack's compartments only share item type t.</li>
    <li>The sixth rucksack's compartments only share item type s.</li>
</ul>

To help prioritize item rearrangement, every item type can be converted to a priority:
<ul>
    <li>Lowercase item types a through z have priorities 1 through 26.</li>
    <li>Uppercase item types A through Z have priorities 27 through 52.</li>
</ul>

In the above example, the priority of the item type that appears in both compartments of each rucksack is 16 (p), 38 (L), 42 (P), 22 (v), 20 (t), and 19 (s); the sum of these is 157.

Find the item type that appears in both compartments of each rucksack. What is the sum of the priorities of those item types?
*/

/*
Solution approach:
1. Parse file one line at a time
2. Split each line in half (first compartment is characters 0 .. n/2-1, second compartment is characters n/2 .. n)
3. Index the characters (items) in each compartment by placing them into separate maps (this could be further optimized to use a single map)
4. Perform set intersection to see which items exist in both compartments (we can assume it'll be just one item)
5. Once we've identified the item, calculate its weight ("priority") and add it to the rolling sum
*/
def sum = 0;
def file = new File('day-03.input.txt');
file.withReader { reader ->
    def line = null;
    while ((line = reader.readLine()) != null) {
        def compartment1 = line.substring(0, (int)(line.length() / 2));
        def compartment2 = line.substring((int)(line.length() / 2));

        def itemsInCompartment1 = [:];
        compartment1.toCharArray().each { item ->
            if (itemsInCompartment1.containsKey(item)) {
                itemsInCompartment1[item]++;
            } else {
                itemsInCompartment1[item] = 1;
            }
        }

        def itemsInCompartment2 = [:];
        compartment2.toCharArray().each { item ->
            if (itemsInCompartment2.containsKey(item)) {
                itemsInCompartment2[item]++;
            } else {
                itemsInCompartment2[item] = 1;
            }
        }

        // set intersection (quick and dirty)
        for (def item : itemsInCompartment1.keySet()) {
            if (itemsInCompartment2.containsKey(item)) {
                //println("Found duplicate item $item with priority ${getPriorityOfItem(item)}")
                sum += getPriorityOfItem(item);
                break;
            }
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
