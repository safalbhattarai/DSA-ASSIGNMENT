
//                      Question 3(a)



import java.util.Collections;
import java.util.PriorityQueue;

public class question3a {
    private final PriorityQueue<Double> maxHeap; // stores the smaller half of the scores
    private final PriorityQueue<Double> minHeap; // stores the larger half of the scores

    public question3a() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addScore(double score) {
        if (maxHeap.isEmpty() || score <= maxHeap.peek()) {
            maxHeap.add(score);
        } else {
            minHeap.add(score);
        }

        // Balance the heaps to maintain the property: size difference <= 1
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double getMedianScore() {
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("No scores available.");
        }

        if (maxHeap.size() == minHeap.size()) {
            // If the number of scores is even, return the average of the two middle scores
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // If the number of scores is odd, return the middle score from the larger heap
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        question3a scoreTracker = new question3a();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1);

        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2);
    }
}
