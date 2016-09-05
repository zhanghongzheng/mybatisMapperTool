import com.hydra.tool.config.ConfigUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by lee on 15/10/30.
 */
public class TestConfig {
    public static void main(String[] args) {
        Map<String, String> conf = ConfigUtil.getConfigsByFile("kafka", "kafka");
        System.out.printf(conf.get("zk.custom.encoding"));


        System.out.println(new Date());
        System.out.println(new Date().getTime());
        System.out.println(new BigDecimal(123456789.02).toString());

        Double d=123456789.02D;
        System.out.println(d.toString());
    }
}
