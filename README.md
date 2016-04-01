# interfacemgr
挡板项目
通过定义对接接口json数据，加速合作开发。
通用挡板服务
说明：
        挡板服务提供，任何接口调用回应，返回内容由文件决定。
应用范围：
        appcan前端开发人员；
        mas开发人员；
使用方法：
        1、在本地编辑 接口名称.txt 
             例如：接口名称= http://xx.xx.xx:8080/sss/fff/login   
                        需要编辑 login.txt 文件 内容为需要返回的数据如：{msg:"ok"}
        2、通过资源管理器打开 ftp://ftp:111111@192.168.4.11/pub
             将 login.txt  复制到 pub目录中。注意不要覆盖其他人员内容。 自己的替换就可以。  
        3、启动浏览器： 输入： http://192.168.4.11:8080/xx/xx/suibian/login?xx=dd 
             浏览器会返回 {msg:"ok"}
