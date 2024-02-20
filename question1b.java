
   //                          question 1 (b)

   



import java.util.Arrays;

public class question1b {

    public static int minTimeToBuildEngines(int[] engines, int splitCost) {
        Arrays.sort(engines); 

        int maxEngines = engines.length;
        int[] dp = new int[maxEngines + 1]; 

        
        Arrays.fill(dp, Integer.MAX_VALUE - splitCost);

       
        dp[1] = engines[0];

        
        for (int i = 2; i <= maxEngines; i++) {
            
            for (int j = i; j >= 1; j--) {
                int time = Math.max(dp[j], engines[i - 1]); 

                
                if (j > 1) {
                    time = Math.min(time, dp[j / 2] + splitCost);
                }

               
                dp[i] = Math.min(dp[i], time);
            }
        }

        return dp[maxEngines]; 
    }

    public static void main(String[] args) {
        int[] engines = {3, 4, 5, 2};
        int splitCost = 2;

        int minTime = minTimeToBuildEngines(engines, splitCost);
        System.out.println("Minimum time needed to build all engines: " + minTime);
    }
}

