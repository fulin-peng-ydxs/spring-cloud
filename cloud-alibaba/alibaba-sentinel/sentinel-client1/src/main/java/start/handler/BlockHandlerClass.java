package start.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author PengFuLin
 * @description sentinel处理类
 * @date 2022/5/9 0:58
 */
public class BlockHandlerClass {

    public static String blockHandlerClass(BlockException exception){
        return "blockHandlerClass";
    }
}
