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

	public final static String STATUS_NOHANDLE = "nohandle";// 订单无人处理
	public final static String STATUS_HANDLE = "handle";// 订单无人处理

}
