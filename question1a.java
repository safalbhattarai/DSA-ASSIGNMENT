
//                       Question 1(a)


            
             


public class question1a {


    public static int findMinimumCost(int[][] costs) {
        
        if (costs != null && costs.length != 0 && costs[0].length != 0) {
            
            int venues = costs.length;
            int themes = costs[0].length;

            
            int[][] dp = new int[venues][themes];

            
            for (int i = 0; i < themes; ++i) {
                dp[0][i] = costs[0][i];
            }

           
            for (int i = 1; i < venues; ++i) {
                
                for (int j = 0; j < themes; ++j) {
                    
                    dp[i][j] = costs[i][j] + findMinCostOfPreviousVenue(dp[i - 1], j);
                }
            }

            
            int minCost = Integer.MAX_VALUE;
            for (int j = 0; j < themes; ++j) {
                minCost = Math.min(minCost, dp[venues - 1][j]);
            }

            
            return minCost;
        } else {
            return 0;
        }
    }

    
    private static int findMinCostOfPreviousVenue(int[] previousRow, int currentTheme) {
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < previousRow.length; ++j) {
            if (j != currentTheme) {
                minCost = Math.min(minCost, previousRow[j]);
            }
        }
        return minCost;
    }

    
    public static void main(String[] args) {      
        int[][] costs = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};    
        int result = findMinimumCost(costs);      
        System.out.println("Minimum cost to decorate all venues: " + result);
    }
}
