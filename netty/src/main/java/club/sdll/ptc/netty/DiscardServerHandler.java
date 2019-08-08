package club.sdll.ptc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年07月14日 11:20
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /** 1、discard server */
        /*ByteBuf byteBuf = (ByteBuf) msg;
        try {
            StringBuilder message = new StringBuilder();
            // 方式一
//            while (byteBuf.isReadable()) {
//                message.append((char) byteBuf.readByte());
//            }
            //方式二
            message.append(byteBuf.toString(CharsetUtil.US_ASCII));
            System.out.println("接收消息：" + message.toString());
            System.out.flush();
        } finally {
            ReferenceCountUtil.release(msg);
        }*/

        /** 2、echo server */
//        StringBuilder message = new StringBuilder();
//        ByteBuf byteBuf = (ByteBuf) msg;
//        message.append(byteBuf.toString(CharsetUtil.US_ASCII));
//        System.out.println("接收消息：" + message.toString());
//        ctx.write(msg);
//        ctx.flush();
        ctx.writeAndFlush(msg);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
