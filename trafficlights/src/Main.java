/**
 * 1）用for循环创建出代表12条路线的对象。
 *
 *         2）创建红绿灯控制系统对象，启动系统
 *  12个灯：
 *  *
 *  "S2N","S2W","E2W","E2S","N2S","N2E","W2E","W2N",
 *  "S2E","E2N","N2W","W2S"
 */
public class Main {
    public static void main(String[] args) {
        String[] derections = {
                "S2N", "S2W", "E2W", "E2S", "N2S", "N2E", "W2E", "W2N",
                "S2E", "E2N", "N2W", "W2S"
        };
        for(int i = 0;i< derections.length;i++){
            new Road(derections[i]);
        }
        new LampConteoller();
    }

}
