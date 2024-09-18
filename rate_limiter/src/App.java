import services.RateLimiterService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Rate limiter service!");

        RateLimiterService service = new RateLimiterService();

        for(int i =0 ; i < 7; i++)
        {   System.out.println("Iteration number " + i);
            System.out.println("Customer 1 "+ service.rateLimiter(1));
            System.out.println("Customer 2 "+ service.rateLimiter(2));
            System.out.println("Customer 3 "+ service.rateLimiter(3));
            System.out.println("Customer 4 "+ service.rateLimiter(4));
            System.out.println("Customer 5 "+ service.rateLimiter(5));
        }


    }
}
