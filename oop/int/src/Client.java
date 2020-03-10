import java.io.DataInputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 1337);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             DataOutputStream dout = new DataOutputStream(out);
             DataInputStream din = new DataInputStream(in)) {
            dout.writeInt(args.length);
            for (int i = 0; i < args.length; i++) {
                dout.writeUTF(args[i]);
                String sonum = din.readUTF();
                System.out.println(sonum);
            }
        }
    }
}