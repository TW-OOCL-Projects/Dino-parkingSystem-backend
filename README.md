# DINO-Parking-System-Backend

## 1. 系统角色分类
   1. 系统管理员  `ADMIN`
   2. 经理       `MANAGER`
   3. 停车小弟    `EMPLOYEE`
   4. 普通用户    `USER`

## 2. 系统角色权限 

角色 | 管理用户 | 管理停车场 | 管理停车小弟 | 指派订单 | 抢单 | 停车 | 取车 |
---|----|----|----|----|---|---|---
系统管理员 | √ | √ | √ | √ | | | |
经理 | | √ | √ | √ | | | 
停车小弟 | | | | | √ | √ | √ |
普通用户 | | | | | | | | |


## 3. API

### 1. 停车场管理 /parkingLots

1. 获取所有停车信息
    - path：`/`
    - method：GET
    - response：
    ```json
    [
     {
         "id": 1,
         "name": "停车场A",
         "size": 20,
         "status": "normal"
     }
    ]
    ```
2. 添加一个停车场
    - path：`/`
    - method：POST
    - request:
    ```json
    {
    "name":"oocl停车场",
    "size":20
    }
    ```

3. 获取所有停车场dashboard数据
   - path：`/dashboard`
   - method: GET
   - response:
   ```json 
    [
        {
            "parkingLotId": 1,
            "parkingLotName": "停车场A",
            "size": 20,
            "carNum": 0,
            "parkingBoyId": 0,
            "parkingBoyName": null
        }
    ]
   ```

4. 分页获取所有停车场dashboard数据
   - path：`/dashboard/page/:page/pageSize/:size`
   - method: GET
   - response:
   ```json 
      [
          {
              "parkingLotId": 1,
              "parkingLotName": "停车场A",
              "size": 20,
              "carNum": 0,
              "parkingBoyId": 0,
              "parkingBoyName": null
          }
      ]
   ```