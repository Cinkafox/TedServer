import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Handler extends Thread{
    Socket socket;
    public Handler(Socket socket){
        this.socket = socket;
        this.start();
    }
    @Override
    public void run() {
        try(OutputStream outputStream = socket.getOutputStream(); InputStream inputStream = socket.getInputStream()) {
            System.out.println("Writing");
            String input = new Scanner(System.in).nextLine();
            outputStream.write((input + "\n").getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            System.out.println("flushed");
            String line = "";
            String out = "";
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
                out = out + line  + "\n";
            }
        } catch (IOException e) {

        }
    }
}
