package Patient;

import BITalino.BITalino;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import BITalino.BITalinoManager;


public class Patient {

    public int ip;
    public int id;
    public int password;
    public String name;
    public int age;
    public String gender;
    public double weight;
    public double height;
    public int flex_ang;
    public int turn_ang;
    ArrayList<Integer>bitalino;

    public Socket socket;
    public PrintWriter pw;
    public BufferedReader br;

    public Patient(int id, String name, int age, String gender, double weight,
            double height, int flex_ang, int turn_ang) throws Exception {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.flex_ang = flex_ang;
        this.turn_ang = turn_ang;
        //bitalino = new BITalino();
        
        socket = new Socket("localhost" /*ip*/, 9000);
        //OutputStream outputStream = socket.getOutputStream();
        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        //cada paciente tiene sus parámetros, un socket que establece la 
        //conexion con el servidor y un pw (paciente - servidor) y br (servidor - paciente)
    }

    public String logPatient() throws IOException { //EXPLICAR LO QUE DEVUELVE

        System.out.println("Logging new Patient");
        System.out.println("Sending data");
        //String connect = "I want to stablish connection";
        //Socket socket = new Socket("localhost" /*ip*/, 9000);
        //OutputStream outputStream = socket.getOutputStream();

        //pw = new PrintWriter(socket.getOutputStream(), true);
        //br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s;

        //connection with the server
        //pw.println(connect);

        
        //name
        pw.println(name);

        //password
        pw.println(password);

        String server_answer = br.readLine();
        if (server_answer.equals("OK")) {
            //releaseResources(pw,br,socket);
            return "Everything went good";
        } else {
            releaseResources(pw,br,socket);
            return "Something went wrong";
        }

    }

    public String registerClient() throws IOException {

        System.out.println("Creamos un paciente");
        System.out.println("Sending data");
        String connect = "I want to stablish connection";
        Socket socket = new Socket("localhost", 9000);
        OutputStream outputStream = socket.getOutputStream();

        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        byte byteBuffer[];
        String s;

        //connection with the server
        byteBuffer = connect.getBytes();
        outputStream.write(byteBuffer);

        /*
            //ip
            s = "" + ip;
            byteBuffer = s.getBytes();
            outputStream.write(byteBuffer);*/
        //id
        s = "" + id;
        //byteBuffer = s.getBytes();
        pw.println(s);

        //name
        //byteBuffer = name.getBytes();
        pw.println(name);

        //age
        s = "" + age;
        byteBuffer = s.getBytes();
        pw.println(s);

        //flex_ang
        s = "" + flex_ang;
        //byteBuffer = s.getBytes();
        pw.println(s);

        //turn_ang
        s = "" + turn_ang;
        //byteBuffer = s.getBytes();
        pw.println(s);

        //gender
        pw.println(gender);
        
        //weight
        s = "" + weight;
        //byteBuffer = s.getBytes();
        pw.println(s);

        //height
        s = "" + height;
        //byteBuffer = s.getBytes();
        pw.println(s);
        
        String server_answer = br.readLine();
        if (server_answer.equals("OK")) {
            //releaseResources(pw,br,socket);
            return "Everything went good";
        } else {
            releaseResources(pw,br,socket);
            return "Something went wrong";
        }
        
        //releaseResources(pw, br, socket);
        //System.exit(0);

    }
    
    public String updateClient() throws IOException{
        
        System.out.println("Logging new Patient");
        System.out.println("Sending data");
        String s;
        
        s=""+turn_ang;
        pw.println(s);
        
        s = ""+flex_ang;
        pw.println(s);
        
        String server_answer = br.readLine();
        if (server_answer.equals("OK")) {
            //releaseResources(pw,br,socket);
            return "Everything went good";
        } else {
            releaseResources(pw,br,socket);
            return "Something went wrong";
        }
        //Iterator it= bitalino.iterator();
        /*
        bitalino.connectReadBitalino("98:D3:31:FD:3B:92");
        while (it.hasNext()){
            s = ""+bilatino[i];*/
    }

    private static void releaseResources(PrintWriter pw,
            BufferedReader br, Socket socket) {
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw.close();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getFlexang() {
        return flex_ang;
    }

    public void setFlexang() {
        this.flex_ang = flex_ang;
    }

    public int getTurnang() {
        return turn_ang;
    }

    public void setTurnang() {
        this.turn_ang = turn_ang;
    }
    
    @Override
    public String toString() {
        return "Patient id=" + id + ", name=" + name + ", age=" + age + ", weight="
                + weight + ", height=" + height + ", gender="
                + gender + ", flexing angle=" + flex_ang + ", turning angle= " + turn_ang;
    }

}
