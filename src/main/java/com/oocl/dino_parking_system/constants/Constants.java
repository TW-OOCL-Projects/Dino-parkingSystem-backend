package com.oocl.dino_parking_system.constants;

public class Constants {
    public final static String STATUS_NORMAL = "normal"; // 停车场开放(parkingBoy可用)
    public final static String STATUS_FREEZE = "freeze";// 停车场关闭(parkingBoy不可用)
    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_MANAGER = "ROLE_MANAGER";
    public final static String ROLE_PARKINGBOY = "ROLE_PARKINGBOY";
    public final static String TYPE_PARKCAR = "parkCar";//停车类型
    public final static String TYPE_PARKOUTCAR = "parkOutCar";//取车类型
    public final static String STATUS_INUSE = "inUse";// 小票有效
    public final static String STATUS_DISABLED = "disabled";// 小票无效

	public final static String STATUS_NOROB = "noRob";// 存车订单无人处理
	public final static String STATUS_WAITPARK = "waitPark";// 存车订单被抢成功，等待停到停车场
	public final static String STATUS_PARKED = "parked";// 存车订单的车被停到停车场
	public final static String STATUS_WAITUNPARK = "waitUnPark";// 取车订单等待取车
	public final static String STATUS_FINISH = "finish";//存取车订单完成
}
