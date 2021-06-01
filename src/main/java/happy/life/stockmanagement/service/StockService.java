package happy.life.stockmanagement.service;

import happy.life.stockmanagement.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created
 * by zhuoheng
 * on 5/31/21 at 11:15 AM
 */
@Service
@AllArgsConstructor
public class StockService {
   private final RefreshService refreshService;
   public StockWrapper findStock(final String ticker) {
      try {
         return new StockWrapper(YahooFinance.get(ticker), ticker);
      } catch (IOException e) {
         System.out.println(String.format("Failed getting price for %s", ticker));
      }
      return null;
   }

   public List<StockWrapper> findStocks(final List<String> tickers) {
      return tickers.stream().filter(Objects::nonNull).map(this::findStock).collect(Collectors.toList());
   }

   public BigDecimal findPrice(final StockWrapper stockWrapper) throws IOException {
      return stockWrapper.getStock().getQuote(true).getPrice();
   }
}
