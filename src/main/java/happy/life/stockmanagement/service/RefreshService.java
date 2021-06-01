package happy.life.stockmanagement.service;

import happy.life.stockmanagement.model.StockWrapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * Created
 * by zhuoheng
 * on 5/31/21 at 11:49 AM
 */
@Service
@Setter
@Getter
public class RefreshService {
   private final ConcurrentMap<StockWrapper, Integer> stocksToRefresh;
   private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
   private final StockService stockService;

   @Autowired
   public RefreshService(StockService stockService) {
      this.stocksToRefresh = new ConcurrentHashMap<>();
   }

   public void setRefreshTask() {
      Runnable refreshStocks = () -> {
         stocksToRefresh.forEach((stock, refreshPeriod) -> {
            if (null != refreshPeriod) {
               Duration duration = Duration.ofMinutes(refreshPeriod);
               // When last updated time is before refresh period, do update
               if (stock.getLastUpdatedTime().isBefore(LocalDateTime.now().minus(duration))) {
                  System.out.println("Stock " + stock.getStock() + " is being refreshed");
                  // To update
                  StockWrapper refreshedStock = stockService.findStock(stock.getTicker());
                  stocksToRefresh.remove(stock);
                  stocksToRefresh.put(refreshedStock, (int) duration.getSeconds() / 60);
                  System.out.println("Refresh of stock " + stock.getStock() + " is done");
               }
            }
         });
      };
      scheduler.scheduleAtFixedRate(refreshStocks,0, 1, TimeUnit.MINUTES);
   }
}
