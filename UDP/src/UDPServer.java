
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        byte[] tosend = new byte[16]; // for datas' bytes
        byte[] toreceive = new byte[16];

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        System.out.println("Connect  " + clientSocket.getLocalAddress() + " " + clientSocket.getInetAddress() + " " + clientSocket.getLocalPort());

        System.out.println("Enter x, y, z: ");
        String sentence = input.readLine();
        tosend = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(tosend, tosend.length, IPAddress, 2000);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(toreceive, toreceive.length);
        clientSocket.receive(receivePacket);

        String answer = new String(receivePacket.getData());
        System.out.println("Answer: " + answer.trim());
        file.readFile();
        clientSocket.close();
    }
}
