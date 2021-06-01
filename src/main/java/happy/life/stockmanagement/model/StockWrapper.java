package happy.life.stockmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

/**
 * Created
 * by zhuoheng
 * on 5/31/21 at 11:28 AM
 */
@Getter
@AllArgsConstructor
@With
public class StockWrapper {
   private final Stock stock;
   private final LocalDateTime lastUpdatedTime;
   private final String ticker;

   public StockWrapper(final Stock stock, final String ticker) {
      this.stock = stock;
      this.lastUpdatedTime = LocalDateTime.now();
      this.ticker = ticker;
   }
}
