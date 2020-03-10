import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket ss = new ServerSocket(1337)) {
            while (true) {

                List<Thread> pool = new ArrayList<>();
                try (Socket socket = ss.accept()) {
                    pool.add(new Thread(new SonumiTootleja(socket)));
                    pool.get(pool.size() - 1).start();
                }

            }
        }
    }
}