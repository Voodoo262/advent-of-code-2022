// https://adventofcode.com/2022/day/2#part2
def file = new File('day-02.input.txt');
int getPointsForRound(Character opponentInput, Character yourInput) {
    def opponentValue = (int)(opponentInput - 'A'.charAt(0)) + 1;
    def yourValue = -1;
    switch (yourInput) {
        case 'X':
            // You lose
            if (opponentValue == 1) {
                yourValue = 3;
            } else {
                yourValue = opponentValue - 1;
            }
            break;
        case 'Y':
            // You tie
            yourValue = opponentValue;
            break;
        case 'Z':
            // You win
            if (opponentValue == 3) {
                yourValue = 1;
            } else {
                yourValue = opponentValue + 1;
            }
            break;
    }
    if (opponentValue == yourValue) {
        // Tied
        return 3 + yourValue;
    }
    if (yourValue == 1 && opponentValue == 3 || yourValue == opponentValue + 1) {
        // You won
        return 6 + yourValue;
    }
    // You lost
    return 0 + yourValue;
}

def sum = 0;
file.withReader { reader ->
    def line = null;
    while ((line = reader.readLine()) != null) {
        def strategy = line.tokenize(' ');
        def opponentStrategy = strategy[0].charAt(0);
        def yourStrategy = strategy[1].charAt(0);
        sum += getPointsForRound(opponentStrategy, yourStrategy);
    }
}
println(sum);
