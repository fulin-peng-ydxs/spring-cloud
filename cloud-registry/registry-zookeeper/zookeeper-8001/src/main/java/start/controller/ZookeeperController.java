package start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PengFuLin
 * @description: 控制类
 * @date 2022/4/18 1:08
 */
@Controller
public class ZookeeperController {

    @RequestMapping("/zookeeper/8001")
    @ResponseBody
    public Object zookeeper(){
        return "访问8001服务成功";
    }
}
