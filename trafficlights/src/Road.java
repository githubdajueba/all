import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 交通灯管理项目的需求
 *     模拟实现十字路口的交通灯管理系统逻辑，具体要求如下：
 *
 * 1. 异步随机生成按照各个路线行驶的车辆。
 *            例如：
 *
 *                    由北向驶往南向的车辆----直行
 *
 *                    由西向驶往南向的车辆----右转
 *
 *                    由东向驶往南向的车辆----左转
 *
 * 2. 信号灯忽略黄灯，只考虑红灯和绿灯。
 * 3. 应考虑左转车辆受信号灯控制，右转车辆不受信号灯控制。
 * 4. 具体信号灯控制逻辑与现实生活中普通交通灯控制逻辑相同，不考虑特殊情况下的控制逻辑：
 *         南北车辆与东西车辆交替放行，同方向车辆等待应先放行直行车辆，后放行左转车辆。
 *
 * 5. 每辆车通过路口的时间为1秒（提示：可通过线程sleep的方式模拟）。
 * 6. 随机生成车辆时间间隔以及红绿灯交换时间间隔自定，可以设置。
 * 7. 不要求实现GUI，只考虑系统逻辑实现，可通过log方式展现程序运行结果。
 */

/**
 * 1）每个Road对象都有一个name成员变量来代表方向，有一个vehicles（交通工具）成员变量来代表方向上的车辆。
 * 2）在Road对象的构造方法中启动一个线程每隔一个随机的时间向vehicles集合中增加一辆车（用一个“路线名_id”
 *
 *    形式的字符串进行表示）。
 * 3）在Road对象的构造方法中启动一个定时器，每隔一秒检查该方向上的灯是否为绿，是则打印车辆集合和将集合中的第一辆车移除掉。使用scheduleAtFixedRate方法。
 */
public class Road {
    List<String> vehicles = new ArrayList<>();
    private String name= null;

    public Road(String name){
        this.name = name;
        // 模拟汽车上路
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(((long)(
                            Math.random()*10)+1)*1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                for(int i = 0;i<1000;i++){

                    vehicles.add(Road.this.name+"_"+i);


                }
            }
        });
        // 定时器,模拟汽车穿过路口
        // 创建定时器
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        if(vehicles.size()>0){
                            // 获取灯的状态
                         boolean lighted =    Lamp.valueOf(Road.this.name)
                                    .isLighted();
                         if(lighted){
                             System.out.println(vehicles.
                                     remove(0)+"  is travling");
                         }
                        }
                    }
                },
                1, //延迟1秒后开始执行任务
                1, //每隔1秒执行一次
                TimeUnit.SECONDS
        );
    }

}























