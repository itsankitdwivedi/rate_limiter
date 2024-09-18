package services;

import java.util.HashMap;
import java.util.Map;

import constants.RateLimiterConstant;
import utils.Pair;

public class RateLimiterService {
    Map<Integer, Pair> customerMap = new HashMap<>();

    public boolean rateLimiter(int customerId)
    {
        if(!customerMap.containsKey(customerId))
        { 
            // first time user
            Pair pair = new Pair(RateLimiterConstant.BUCKET_SIZE -1, System.currentTimeMillis() / RateLimiterConstant.TIME_WINDOW);
            customerMap.put(customerId, pair);
            return true;
        }
        // not the first time
        else{
            if(System.currentTimeMillis() / RateLimiterConstant.TIME_WINDOW <= customerMap.get(customerId).refillTime  &&  customerMap.get(customerId).bucketSizeLeft > 0)
            {
                Pair pair = new Pair(customerMap.get(customerId).bucketSizeLeft - 1, customerMap.get(customerId).refillTime);
                customerMap.put(customerId, pair);
                return true;
            }
            
            else{
                // credits
                if(customerMap.get(customerId).bucketSizeLeft > 0)
                {
                    Pair pair = new Pair((int) (RateLimiterConstant.BUCKET_SIZE * (System.currentTimeMillis() / RateLimiterConstant.TIME_WINDOW) - 1), System.currentTimeMillis() / RateLimiterConstant.TIME_WINDOW);
                    customerMap.put(customerId, pair);
                    return true;
                }
                else{
                    return false;
                }

                // if(System.currentTimeMillis() > customerMap.get(customerId).refillTime)
                // {
                //     // can be refilled
                //     Pair pair = new Pair(RateLimiterConstant.BUCKET_SIZE -1 , System.currentTimeMillis() + RateLimiterConstant.TIME_WINDOW);
                //     customerMap.put(customerId, pair);
                //     return true;
                // }
                // else{
                //     return false; // cannot be refilled 
                // }
            }
        }
    }
}
