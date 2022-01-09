import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server
{
    public static void main(String[] arg)
    {
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;
        ObjectInputStream  sois   = null;
        ObjectOutputStream soos   = null;
        List<Float> listFloat = new ArrayList<Float>();
        listFloat.add(new Float(10000, "apple street, 56"));
        listFloat.add(new Float(24000, "cucumber street, 50"));
        listFloat.add(new Float(10600, "pineapple street, 23"));
        listFloat.add(new Float(140000, "tom street, 100"));
        int max;
        String floats = new String();
        try
        {

            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("connection established....");
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            String clientMessageRecieved = "";

            while(!clientMessageRecieved.equals("quite"))
            {
                clientMessageRecieved = (String)sois.readObject();
                System.out.println("message recieved: '" + clientMessageRecieved + "'");
                switch(clientMessageRecieved)
                {
                    case"show":

                        for (Float f : listFloat) {
                            floats += f.GetInformation() + "\n";
                        }
                        soos.writeObject(floats);
                        break;
                    case"choose":
                        soos.writeObject("Input max price");
                        max = (int) sois.readObject();
                        System.out.println(max);
                        for (Float f : listFloat) {
                            if (f.Match(max)) {
                                floats += f.GetInformation() + "\n";
                            }
                        }
                        System.out.println("Eee");
                        soos.writeObject(floats);
                        break;
                }
            }

        }
        catch(Exception e)
        {
        }
        finally
        {
            try
            {
                sois.close();
                soos.close();
                clientAccepted.close();
                serverSocket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

