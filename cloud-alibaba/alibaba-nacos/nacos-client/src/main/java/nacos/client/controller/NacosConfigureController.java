package nacos.client.controller;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import nacos.client.model.mime.MimeType;
import nacos.client.model.response.Response;
import nacos.client.utils.FileUtils;
import nacos.client.utils.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * config 控制器
 *
 * @author fulin-peng
 * 2024-11-02  16:50
 */
@Slf4j
@RestController
@Api(tags = "config 控制器")
@RequestMapping("/nacos/configure")
public class NacosConfigureController {

    @Autowired
    private ConfigService nacosConfigService;

    /**
     * 发布配置
     * @param dataId 配置did
     * @param group 配置分组
     * @param multipartFile 配置文件名
     * 2024/11/2 下午4:53
     * @author fulin-peng
     */
    @ApiOperation(value="发布配置",produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/publishConfig",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Response<?> publishConfig(@RequestParam String dataId, @RequestParam(defaultValue = "DEFAULT_GROUP")  String group, @RequestParam("file") MultipartFile multipartFile)
            throws Exception {
        String fileToString = FileUtils.fileToString(
                new InputStreamReader(multipartFile.getInputStream()),"\n");
        boolean result = nacosConfigService.publishConfig(dataId, group, fileToString,dataId.lastIndexOf("yaml")>-1?ConfigType.YAML.getType()
                :ConfigType.PROPERTIES.getType());
        return result?Response.success():Response.failure();
    }

    /**
     * 获取配置
     * 2024/11/2 下午4:53
     * @author fulin-peng
     */
    @ApiOperation(value="获取配置",produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Response<?> getConfig(@RequestParam String dataId, @RequestParam(defaultValue = "DEFAULT_GROUP") String group)
            throws Exception{
        String config = nacosConfigService.getConfig(dataId, group, 3000);
        if (config==null)
            return Response.failure("配置不存在");
        ServletHolder.responseToOutStream(config.getBytes(StandardCharsets.UTF_8),
                dataId, dataId.lastIndexOf("yaml")>-1?MimeType.APPLICATION_YAML:MimeType.TEXT_PLAIN,false);
        return null;
    }
}
