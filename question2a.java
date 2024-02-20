
//                            question 2 (a)








public class question2a {

    public static int minMovesToEqualize(int[] machines) {
        int totalDresses = 0;
        int numOfMachines = machines.length;

        // Calculate the total number of dresses in all machines
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        // If the total number of dresses cannot be equally distributed among machines, return -1
        if (totalDresses % numOfMachines != 0) {
            return -1;
        }

        // Calculate the target number of dresses in each machine
        int target = totalDresses / numOfMachines;

        int moves = 0;
        int dressesToLeft = 0;
        int dressesToRight = 0;

        // Iterate through each machine
        for (int i = 0; i < numOfMachines; i++) {
            int diff = machines[i] - target;

            // Accumulate the dresses needed to move to the left and right of the current machine
            dressesToLeft += diff;
            dressesToRight -= diff;
            moves = Math.max(moves, Math.max(Math.abs(dressesToLeft), Math.abs(dressesToRight)));
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] machines = {1, 0, 5};
        System.out.println(minMovesToEqualize(machines)); // Output:3
    }
}