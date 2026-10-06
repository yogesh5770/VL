public class Benchmark {
    public static void main(String[] args) {
        System.out.println("Starting Benchmark Execution...");
        long startTime = System.nanoTime();
        
        long sum = 0;
        long limit = 500000000L; // 500 million iterations
        for (long i = 1; i <= limit; i++) {
            sum += (i % 10);
        }
        
        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        
        System.out.println("==========================================");
        System.out.println("Result Sum: " + sum);
        System.out.println("Total Execution Time: " + String.format("%.4f", durationSeconds) + " seconds");
        System.out.println("==========================================");
    }
}
