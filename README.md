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
    - path：`/parkingLots`
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
    

2. 添加一个停车场
    - path：`/parkingLots`
    - method：POST
    - request:
      ```json
      {
        "name":"oocl停车场",
        "size":20
      }


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
                    "status":"normal",
                    "parkingBoyId": 0,
                    "parkingBoyName": null
                }
        ]


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
                  "status":"normal",
                  "parkingBoyId": 0,
                  "parkingBoyName": null
              }
        ]
      

### 2. 订单管理

1. 获取所有order
    - path：`/orders`
    - method: GET
    - response:
        ```json
        [
           {
               "id": 1,
               "type": "parkOutCar",
               "parkingBoy": {
                   "id": 2,
                   "username": "parkingboy1"
               },
               "plateNumber": "粤DHC9767",
               "status": "handle",
               "receiptId": "1"
           },
           {
               "id": 2,
               "type": "parkCar",
               "parkingBoy": null,
               "plateNumber": "粤DH76647",
               "status": "nohandle",
               "receiptId": "2"
           }
        ]
2. 获取某个parkingboy的所有order
    - path：`/orders?parkingBoyId=1`
    - method: GET
    - response:
        ```json
        [
           {
               "id": 1,
               "type": "parkOutCar",
               "parkingBoy": {
                   "id": 2,
                   "username": "parkingboy1"
               },
               "plateNumber": "粤DHC9767",
               "status": "handle",
               "receiptId": "1"
           },
           {
               "id": 2,
               "type": "parkCar",
               "parkingBoy": null,
               "plateNumber": "粤DH76647",
               "status": "nohandle",
               "receiptId": "2"
           }
        ]
  
3. 根据状态获取order
    - path：`/orders/:status`
    - method: GET
    - request:
        ```json
        [
               {
                   "id": 1,
                   "type": "parkCar",
                   "plateNumber": "粤DHC9767",
                   "status": "nohandle",
                   "receiptId": "1"
               },
               {
                   "id": 2,
                   "type": "parkCar",
                   "plateNumber": "粤DH76647",
                   "status": "nohandle",
                   "receiptId": "2"
               }
           ]
  

4. parkingBoy抢单
    - path：`/orders/:id`
    - method: PUT
    - request:
        ```json
        {
            "parkingBoyId":2
        }
  
### 3. 停车小弟管理 /parkingBoys

1. 停车小弟选择停车场停车
    - path:`/parkingBoys/{parkingBoyId}/parkingLots/{parkingLotId}`
    - method:PUT
    - request:
        ```json
        {
          "orderId":1
        }
    - response:
        - 成功：200 OK
        - 失败：400 Bad Requset
### 4. 停车小弟管理 /parkingBoys

1. manager获取parkingBoy列表
    - path:`/parkingBoys`
    - method:GET
    - response:
        - 成功：200 OK
```
     [
        {
            "nickname": "停车小弟A",
            "id": 2,
            "phone": "13160675789",
            "email": "120@qq.com",
            "lotNumber": 2,
            "carNumber": 23,
            "total": 30
        },
        {
            "nickname": "停车小弟B",
            "id": 4,
            "phone": "13160675789",
            "email": "121@qq.com",
            "lotNumber": 2,
            "carNumber": 1,
            "total": 45
        }
    ]
```
        - 失败：400 Bad Requset