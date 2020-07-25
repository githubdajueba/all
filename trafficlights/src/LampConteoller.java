import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * LampController交通灯控制器
 * 1）整个系统中只能有一套交通灯控制系统，所以，LampController类最好是设计成单例。
 *  
 * 2）LampController构造方法中要设定第一个为绿的灯。
 *  
 * 3）LampController对象的start方法中将当前灯变绿，然后启动一个定时器，每隔10秒将当前灯变红和将下一个灯变绿。
 */
public class LampConteoller {
    private Lamp currentLamp; //当前灯
    // 初始化
    public LampConteoller(){
        // 默认第一个灯为S2N,
        currentLamp = Lamp.S2N;
        // 点亮
        currentLamp.light();

        // 每隔10秒,变红,下一个变绿,创建定时器线程池
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        // 灯变黑同时指向下一个变绿的灯
                        currentLamp = currentLamp.blackOut();
                    }
                },
                2, // 隔10秒执行第一次
                2, //每隔10秒执行一次
                TimeUnit.SECONDS
        );


    }
}
