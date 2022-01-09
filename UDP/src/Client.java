import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(2000);
        byte[] receiveData;
        byte[] sendData = new byte[16];

        while (true) { //общий цикл работы сервера
            receiveData = new byte[16];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            InetAddress IPAddress = receivePacket.getAddress();
            int k = receivePacket.getLength();

            int port = receivePacket.getPort();
            String sentence = new String(receivePacket.getData());
            System.out.println("Server  " + sentence);

            String[] values = sentence.split(" ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            double z = Double.parseDouble(values[2]);
            double f = Math.pow(1+Math.pow(Math.tan(z/3), 2), Math.pow(Math.abs(y)+7, 1/2))
                    *Math.pow(2.7,x-y)/(y+Math.pow(x, 2)/(y+Math.pow(x,3)/y))+y;

            file.InFile(sentence + " = " + f );

            sentence = Double.toString(f);
            System.out.println(" " + sentence.trim());
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }

    }
}
