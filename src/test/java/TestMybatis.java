import com.hydra.tool.mybatis.MybatisPlugin;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ZhengGong on 15/9/14.
 * Description
 */
public class TestMybatis {
    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, XMLParserException, IOException {
        MybatisPlugin.start();
    }
}
