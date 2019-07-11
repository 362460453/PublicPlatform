package cn;

import cn.mapper.GoodsMapper;
import cn.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-03-04 4:13 PM
 * @since v8.0
 **/
@SpringBootApplication
@MapperScan("cn.mapper")
@Slf4j
public class MoneyDemoApplication implements ApplicationRunner {
    @Autowired
    private GoodsMapper goodsMapper;

    public static void main(String[] args) {
        SpringApplication.run(MoneyDemoApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Goods goods = Goods.builder().name("白桃薯条").price(Money.of(CurrencyUnit.of("CNY"), 20)).build();
        Long id = goodsMapper.save(goods);
        log.info("Goods {} => {}", id, goods);

        goods = goodsMapper.findById(id);
        log.info("Goods {}", goods);
    }
}
