import com.hydra.tool.crontask.CronTaskManager;
import com.hydra.tool.crontask.constants.E;
import com.hydra.tool.crontask.model.CronResult;
import com.hydra.tool.system.Sys;

/**
 * Created by ZhengGong on 15/9/17.
 * Description
 */
public class NutzTest {

    public static void main(String[] args) {

        CronResult cronResult = CronTaskManager.me().startInterval("startInterval", 3000);
        if (cronResult.isExecute()) {
            Sys.pl("true");
            CronTaskManager.me().finishTask(cronResult.getCronTask());
        } else {
            Sys.pl("false");
        }
    }
}
