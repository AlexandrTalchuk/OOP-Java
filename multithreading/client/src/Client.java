import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Cleaner;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket client;
    private ObjectOutputStream output_stream;
    private ObjectInputStream input_stream;
    private Scanner scan;

    public Client(String host, int port) throws IOException{
        client = new Socket("127.0.0.1",1234);
        output_stream=new ObjectOutputStream(client.getOutputStream());
        input_stream=new ObjectInputStream(client.getInputStream());
        scan=new Scanner(System.in);
        System.out.println("connection established...");
        Object obj;

    }

    public void Run() throws IOException,ClassNotFoundException {
        System.out.print("~client: ");
        String str = scan.next();
        output_stream.writeObject(str);
        while (str.length() != 0) {

            int[] array = (int[]) (input_stream.readObject());
            int i = 0;
            System.out.println("~server: sent the numbers");

            System.out.println("Number of vowels: " + array[0]);
            System.out.println("Number of consonants: " + array[1]);

            System.out.print("~client: ");
            str = scan.next();
            output_stream.writeObject(str);
        }
    }
    public void Close() throws IOException
    {
        output_stream.close();
        input_stream.close();
        client.close();
    }


    public static void main(String[] args) {
        final String HOST="127.0.0.1";
        final int PORT=1234;
        try{
            Client client=new Client(HOST,PORT);
            client.Run();
            client.Close();
        }catch(Exception exp) {
            exp.printStackTrace();
        }
    }
}
