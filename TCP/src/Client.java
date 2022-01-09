import java.io.*;
import java.net.*;

public class Client
{

    public static void main(String[] arg)
    {
        try
        {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",2525);//установка соединения между локальной машиной
            //и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание
            //буферизированного символьного потока ввода

            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание
            //потока вывода
            ObjectInputStream  cois = new ObjectInputStream(clientSocket.getInputStream());//создание
            //потока ввода
            String clientMessage = "";
            while(!clientMessage.equals("quite"))
            {
                System.out.println("Press 'show' to show all floats. " +
                        "\n'choose' to enter max price for float." +
                        "\n'quite' to exit the program");
                clientMessage = stdin.readLine();//ввод текста с клавиатуры
                System.out.println("you've entered: "+clientMessage);//вывод в
                //консоль строки и значения строковой переменной
                coos.writeObject(clientMessage);
                int max;
                switch(clientMessage)
                {
                    case "show":
                        System.out.println("~server~: "+cois.readObject());//выводится на
                        // экран содержимое потока ввода (переданное сервером)
                        System.out.println("---------------------------");
                        break;
                    case "choose":
                        System.out.println("~server~: "+cois.readObject());
                        System.out.println("---------------------------");
                        clientMessage = stdin.readLine();
                        max = Integer.parseInt(clientMessage);
                        coos.writeObject(max);
                        System.out.println("~server~: "+cois.readObject());//выводится на
                        // экран содержимое потока ввода (переданное сервером)
                        System.out.println("---------------------------");
                        break;
                }
            }
            coos.close();//закрытие потока вывода
            cois.close();//закрытие потока ввода
            clientSocket.close();//закрытие сокета
        }
        catch(Exception e)
        {
            e.printStackTrace();//выполнение метода исключения е
        }
    }

}
