
    //                   question4(b)



import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class question4b {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;

        // Perform in-order traversal
        while (curr != null || !stack.isEmpty()) {
            // Traverse left subtree and store nodes along the way
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // Pop the top node from the stack
            curr = stack.pop();
            // Check if result list size is less than k
            if (result.size() < k) {
                result.add(curr.val);
            } else if (Math.abs(curr.val - target) < Math.abs(result.get(0) - target)) {
                // If the current node is closer to target then the farthest value in result, replace it
                result.remove(0);
                result.add(curr.val);
            } else {
                // If the current node is farther from target, break the loop
                break;
            }
            // Move to the right subtree
            curr = curr.right;
        }

        return result;
    }

    public static void main(String[] args) {
        // Construct the example binary search tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        question4b solution = new question4b();
        double target = 3.8;
        int x = 2;
        List<Integer> closestValues = solution.closestKValues(root, target, x);
        System.out.println("Closest values to " + target + " are: " + closestValues); //Output:3,4
    }
}