package com.elasticjob.springboot.mapper.sqlprovider;

import java.util.List;
import java.util.Map;

/**
 * @author yanlin
 * @version v1.0
 * @className SqlProvider
 * @description TODO
 * @date 2019-07-01 2:34 PM
 **/
public class SqlProvider {
    public String updateStateList(Map<String, List<Integer>> para) {
        StringBuffer sb = new StringBuffer();
        List<Integer> idList = para.get("idList");
        sb.append("update job set state=1 ");
        if (idList != null && !idList.isEmpty()) {
            sb.append("where id in (");
            for (int i = 0; i < idList.size(); i++) {
                sb.append(idList.get(i));
                if (i < idList.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("  )");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
