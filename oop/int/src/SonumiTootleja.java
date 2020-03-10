import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class SonumiTootleja implements Runnable {
    Socket socket;

    public SonumiTootleja(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        try {
            in = socket.getInputStream();

            OutputStream out = socket.getOutputStream();
            DataInputStream din = new DataInputStream(in);
            DataOutputStream dout = new DataOutputStream(out);
            int sonumiteArv = din.readInt();
            for (int i = 0; i < sonumiteArv; i++) {
                String sonum = din.readUTF();
                dout.writeUTF(sonum);
            }
            din.close();
            dout.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}