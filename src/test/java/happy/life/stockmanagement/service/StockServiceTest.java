package happy.life.stockmanagement.service;

import happy.life.stockmanagement.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created
 * by zhuoheng
 * on 5/31/21 at 11:40 AM
 */
@SpringBootTest
class StockServiceTest {
   @Autowired
   private StockService stockLookupService;

   @Test
   public void invoke() throws IOException {
      final StockWrapper stock = stockLookupService.findStock("AAPL");
      System.out.println(stock.getStock());
      System.out.println(String.format("Price of %s is %f at %s", stock.getStock(), stockLookupService.findPrice(stock).doubleValue(),  LocalDateTime.now()));
   }
}