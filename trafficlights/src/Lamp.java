/**
 * 交通灯的lamp类
 */
public enum Lamp {

    S2N("N2S","S2W",false),
    S2W("N2E","E2W",false),
    E2W("W2E","E2S",false),

    E2S("W2N","S2N",false),
    N2S(null,null,false),N2E(null,null,false),
    W2E(null,null,false),W2N(null,null,false),
    S2E(null,null,true),
    E2N(null,null,true),
    N2W(null,null,true),
    W2S(null,null,true);

    private boolean lighted;
    private String opposite;
    private String next;

    private Lamp(){}

    private Lamp(String opposite,String next,boolean lighted){
        this.opposite = opposite;
        this.next = next;
        this.lighted = lighted;
    }

    public boolean isLighted(){
        return lighted;
    }

    public void light(){
        this.lighted = true;
        //健壮性判断，防止死循环，东对应的灯是西，西对应的灯没有。
        if(opposite!=null){
            //根据对象名称返回枚举。一个灯亮了，对应的灯也跟着亮。
            Lamp.valueOf(opposite).light();
        }
        //四个右转弯，；一对灯亮
        System.out.println(name()+" lamp is green," +
                "下面总共应该有6个方向能看到汽车穿过");
    }

    public Lamp blackOut(){
        this.lighted = false;
        if(opposite!=null){
            Lamp.valueOf(opposite).blackOut();
        }
        Lamp nextLamp = null; //记住下一个变绿的灯
        if(next!=null){
            nextLamp = Lamp.valueOf(next);
            System.out.println("绿灯从"+name()+
                    "--->切换为"+next);
            nextLamp.light(); //变红灯的同时，下一个灯变绿
        }
        return nextLamp;  //返回即将变绿的灯
    }














}
