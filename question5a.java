//                         Question 5 (a)



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class question5a {
    private double[][] distances;
    private int numAnts;
    private int numIterations;
    private double decayFactor;
    private double alpha;
    private double beta;
    private int numCities;
    private double[][] pheromoneLevels;
    private List<Integer> bestPath;
    private double bestDistance;

    public question5a(double[][] distances, int numAnts, int numIterations, double decayFactor, double alpha, double beta) {
        this.distances = distances;
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.decayFactor = decayFactor;
        this.alpha = alpha;
        this.beta = beta;
        this.numCities = distances.length;
        this.pheromoneLevels = new double[numCities][numCities];
        this.bestPath = new ArrayList<>();
        this.bestDistance = Double.POSITIVE_INFINITY;
        initializePheromones();
    }

    private void initializePheromones() {
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromoneLevels[i][j] = 1.0 / (numCities * numCities);
            }
        }
    }

    public void solve() {
        Random random = new Random();
        for (int iter = 0; iter < numIterations; iter++) {
            List<List<Integer>> antPaths = new ArrayList<>();
            double[] antDistances = new double[numAnts];
            for (int antIndex = 0; antIndex < numAnts; antIndex++) {
                List<Integer> path = constructSolution(random.nextInt(numCities));
                antPaths.add(path);
                antDistances[antIndex] = calculateDistance(path);
                if (antDistances[antIndex] < bestDistance) {
                    bestDistance = antDistances[antIndex];
                    bestPath = new ArrayList<>(path);
                }
            }
            updatePheromones(antPaths, antDistances);
            decayPheromones();
        }
    }

    private List<Integer> constructSolution(int startingCity) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[numCities];
        path.add(startingCity);
        visited[startingCity] = true;
        while (path.size() < numCities) {
            int currentCity = path.get(path.size() - 1);
            int nextCity = selectNextCity(currentCity, visited);
            path.add(nextCity);
            visited[nextCity] = true;
        }
        path.add(startingCity); // Return to the starting city
        return path;
    }

    private int selectNextCity(int currentCity, boolean[] visited) {
        double[] probabilities = new double[numCities];
        double totalProbability = 0;
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromoneLevels[currentCity][i], alpha) *
                        Math.pow(1.0 / distances[currentCity][i], beta);
                totalProbability += probabilities[i];
            }
        }
        double randomValue = Math.random() * totalProbability;
        double cumulativeProbability = 0;
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                cumulativeProbability += probabilities[i];
                if (cumulativeProbability >= randomValue) {
                    return i;
                }
            }
        }
        return -1; // Should not reach here
    }

    private double calculateDistance(List<Integer> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += distances[path.get(i)][path.get(i + 1)];
        }
        return distance;
    }

    private void updatePheromones(List<List<Integer>> antPaths, double[] antDistances) {
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    double deltaPheromone = 0;
                    for (int k = 0; k < numAnts; k++) {
                        List<Integer> path = antPaths.get(k);
                        double distance = antDistances[k];
                        if (path.contains(i) && path.contains(j)) {
                            deltaPheromone += 1 / distance;
                        }
                    }
                    pheromoneLevels[i][j] += deltaPheromone;
                }
            }
        }
    }

    private void decayPheromones() {
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromoneLevels[i][j] *= decayFactor;
            }
        }
    }

    public List<Integer> getBestPath() {
        return bestPath;
    }

    public double getBestDistance() {
        return bestDistance;
    }

    public static void main(String[] args) {
        double[][] distances = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int numAnts = 10;
        int numIterations = 100;
        double decayFactor = 0.5;
        double alpha = 1;
        double beta = 2;

        question5a antColony = new question5a(distances, numAnts, numIterations, decayFactor, alpha, beta);
        antColony.solve();

        List<Integer> bestPath = antColony.getBestPath();
        double bestDistance = antColony.getBestDistance();

        System.out.println("Best path: " + bestPath);
        System.out.println("Best distance: " + bestDistance);
    }
}
