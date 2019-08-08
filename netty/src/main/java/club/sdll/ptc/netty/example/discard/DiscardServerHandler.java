package club.sdll.ptc.netty.example.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 简单通道内嵌处理器
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年07月17日 15:46
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {


    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //discard
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
