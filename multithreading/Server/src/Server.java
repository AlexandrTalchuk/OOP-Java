import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server extends Thread{
    private Socket client;
    private ObjectInputStream input_stream;
    private ObjectOutputStream output_stream;
    private static int clientCount=0;

    public Server(ServerSocket socket) throws IOException {
        client=socket.accept();
        clientCount++;
        System.out.println("client "+clientCount+" is connected");
        input_stream = new ObjectInputStream(client.getInputStream());
        output_stream = new ObjectOutputStream(client.getOutputStream());
    }

    public void Release() throws IOException, NullPointerException {
        input_stream.close();
        output_stream.close();
        client.close();
    }

    @Override
    public void run() {
        try {
            String str = input_stream.readObject().toString();

            while(str.length()!=0)
            {
                Pattern vocals = Pattern.compile("(?iu)[аеёиоуыэюя]");
                Pattern consonants = Pattern.compile("(?iu)[йцкнгшщзхъфвпрлджчсмтб]");

                Matcher MatcherVocals = vocals.matcher(str);
                int vocalCounter = 0;
                while (MatcherVocals.find())
                {
                    vocalCounter++;
                }
                Matcher MatcherConsonants = consonants.matcher(str);
                int consonantsCounter = 0;
                while (MatcherConsonants.find())
                {
                    consonantsCounter++;
                }
                int[] array=new int[2];
                array[0] = vocalCounter;
                array[1] = consonantsCounter;
                output_stream.writeObject(array);
                str = input_stream.readObject().toString();
            }
            Thread.sleep(1500);

            this.Release();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final int PORT = 1234;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                try{
                    new Server(serverSocket).start();
                }
                catch(IOException e){
                    e.printStackTrace();
                }

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}