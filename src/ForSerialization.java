import java.io.*;
import java.util.ArrayList;

// Serialization, deserialization and auto indexing of persons
public class ForSerialization {

    // variables
    private FileOutputStream fileOutputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private FileInputStream fileInputStream = null;
    private ObjectInputStream objectInputStream = null;

    // Serialization
    public void save(ArrayList<Contact> arrayList){
        // Call indexing in the moment of data save
        personsAvtoIndexing(arrayList);
        // Serialization procedure
        try {
            fileOutputStream = new FileOutputStream("contacts_list.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Deserialization
    public ArrayList<Contact> load() {
        // To take result from file
        ArrayList<Contact> arrayList = null;
        // Deserialization procedure

        File file = new File("contacts_list.txt");

        if (file.length() == 0) {
            arrayList = new ArrayList<Contact>();
        }
        if(!(file.length() == 0)) {
            try {
                fileInputStream = new FileInputStream("contacts_list.txt");
                objectInputStream = new ObjectInputStream(fileInputStream);
                arrayList = (ArrayList<Contact>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    // Persons auto indexing
    public void personsAvtoIndexing(ArrayList<Contact> arrayList){
        for(int i = 0; i <= arrayList.size() - 1; i++){
            arrayList.get(i).setId(i + 1);
        }
    }
}

