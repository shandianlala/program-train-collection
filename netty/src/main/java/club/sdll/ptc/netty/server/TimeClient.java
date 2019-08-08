package club.sdll.ptc.netty.server;

import club.sdll.ptc.netty.handlder.TimeClientHandler;
import club.sdll.ptc.netty.handlder.TimeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年07月16日 19:42
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {
        String host = args[0];
        Integer port = Integer.valueOf(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {

                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeClientHandler());
                    ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());

                }
            });

            ChannelFuture channelFuture = b.connect(host, port).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }




    }

}
