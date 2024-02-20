 

//       question2 (b)




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class question2b {

    public static List<Integer> getIndividuals(int totalIndividuals, int[][] intervals, int firstPerson) {
        List<Integer> individualsWhoKnowSecret = new ArrayList<>();
        Set<Integer> knownIndividuals = new HashSet<>();
        knownIndividuals.add(firstPerson);

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            Set<Integer> newIndividuals = new HashSet<>();

            for (int i = start; i <= end; i++) {
                if (knownIndividuals.contains(i)) {
                    for (int j = 0; j < totalIndividuals; j++) {
                        if (!knownIndividuals.contains(j)) {
                            newIndividuals.add(j);
                        }
                    }
                }
            }

            knownIndividuals.addAll(newIndividuals);
        }

        individualsWhoKnowSecret.addAll(knownIndividuals);
        return individualsWhoKnowSecret;
    }

    public static void main(String[] args) {
        int totalIndividuals = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;

        List<Integer> individuals = getIndividuals(totalIndividuals, intervals, firstPerson);

        System.out.println("Individuals who will eventually know the secret: " + individuals);
    }
}
