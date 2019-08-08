package club.sdll.ptc.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月30日 23:06:38
 */
public class TestRPC extends Thread {

    private int i = 0;

    public TestRPC(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        RPCClient fibonacciRpc = null;
        try {
            fibonacciRpc = new RPCClient();
            System.out.println(" [x] Requesting fib(" + i + ")");
            String response = fibonacciRpc.call("" + i);
            System.out.println(" [.] Got '" + response + "'");
            if (fibonacciRpc != null) {
                fibonacciRpc.close();
            }
        } catch (Exception e) {


        }
    }

    public static void main (String[] args) throws IOException, TimeoutException, InterruptedException {
        for (int i = 0; i < 50; i++) {
            TestRPC testRPC = new TestRPC(i);
            testRPC.start();
            System.out.println("now testRPC-" + i + " is starting");
        }
    }

}
