package cn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-03-04 4:21 PM
 * @since v8.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;

}
