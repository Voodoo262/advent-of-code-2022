// https://adventofcode.com/2022/day/4
def file = new File('day-04.input.txt');
def sum = 0;
file.withReader { reader ->
    def line = null;
    while ((line = reader.readLine()) != null) {
        def (elf1range, elf2range) = line.tokenize(',');
        def (elf1startStr, elf1endStr) = elf1range.tokenize('-');
        def (elf2startStr, elf2endStr) = elf2range.tokenize('-');
        def elf1start = Integer.parseInt(elf1startStr);
        def elf1end = Integer.parseInt(elf1endStr);
        def elf2start = Integer.parseInt(elf2startStr);
        def elf2end = Integer.parseInt(elf2endStr);
        if ((elf1start >= elf2start && elf1end <= elf2end) || (elf2start >= elf1start && elf2end <= elf1end)) {
            // println("FOUND: elf1:$elf1range, elf2:$elf2range")
            sum++;
        }
    }
}
println(sum);
