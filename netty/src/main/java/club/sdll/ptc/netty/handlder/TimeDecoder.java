package club.sdll.ptc.netty.handlder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年07月17日 10:25
 */
public class TimeDecoder extends ByteToMessageDecoder {


    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(in.readBytes(4));
    }


}
