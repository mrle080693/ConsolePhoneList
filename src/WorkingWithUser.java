import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkingWithUser {
    //
    private Scanner scanner;
    private ForSerialization forSerialization = new ForSerialization();
    private ArrayList<Contact> arrayList = null;

    // Method for showing start message and take users choice
    public void start() {
        // Start message
        System.out.println("Hello! I am you phone list");
        System.out.println("If you want to see all phone numbers write 1 to a console and press the enter key");
        System.out.println("If you want to find the number write 2 to a console and press the enter key");
        System.out.println("If you want add new number write 3 and press the enter key");
        System.out.println("If you want to delete the number write 4 and press the enter key");
    }

    // Method for getting users choice
    public void getUsersChoice(){
        try {
            scanner = new Scanner(System.in);
            // Users choice
            int userChoice = scanner.nextInt();
            // Show all contacts
            switch (userChoice){
                case 1:
                    showAll();
                    toMainMenuOrExit();
                    break;
                // find a contact
                case 2:
                    find();
                    toMainMenuOrExit();
                    break;
                // Add contact
                case 3:
                    add();
                    toMainMenuOrExit();
                    break;
                // Delete contact
                case 4:
                    delete();
                    toMainMenuOrExit();
                    break;
                // Unknown command from user
                default:
                    System.out.println("You are the best!!! But you have to be more careful");

            }

            // If user has beet read massage not carefully
        } catch (Exception e){
            System.err.println("Read starting message more carefully");
        }
    }

    // Users choice. To main menu or exit
    public void toMainMenuOrExit(){
        System.out.println();
        System.out.println("To main menu - 1");
        System.out.println("For exit     - 2");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                start();
                getUsersChoice();
                break;
            case 2:
                System.out.println("Good bye :)");
                break;
            default:
                System.out.println("Wrong command");
                toMainMenuOrExit();
        }
    }

    // Show all contacts
    public void showAll(){
        // Load persons list
        File file = new File("contacts_list.txt");

        if (file.length() == 0) {
            arrayList = new ArrayList<Contact>();
            System.out.println("Contacts list is empty");
        } else {
            arrayList = forSerialization.load();

            for(int i = 0; i <= arrayList.size() - 1; i++) {
                System.out.println(arrayList.get(i));
            }
        }
    }

    // Find the contact
    public void find(){
        // Get data from user
        System.out.println("Write name and press the enter key for find");
        String fromUser = scanner.next().toLowerCase();
        int find = 0;
        // If such contact exists
        for(int i = 0; i <= forSerialization.load().size() - 1; i++){
            if(fromUser.equals(forSerialization.load().get(i).getName().toLowerCase())){
                System.out.println(forSerialization.load().get(i));
                find = 1;
            }
        }
        // If not exists
        if(find == 0){
            System.err.println("Such number was not found");
        }
    }

    // Add new contact
    public void add(){
        // Get data from user
        System.out.println("Write the name of new contact");
        String name = scanner.next();
        System.out.println("Write the number of new contact");
        String number = scanner.next();

        // Check
        System.out.println("Would you like to add " + name + " " + number);
        System.out.println("1 = yes, 2 = no");
        String check = scanner.next();

        // Add new contact to file
        if(check.equals("1")) {
            try {
                arrayList = forSerialization.load();
                arrayList.add(new Contact(name, number));
                forSerialization.save(arrayList);
                System.out.println(name + " added");
            } catch (Exception e) {
                System.err.println("You are the best!!! But.. you have to read more careful");
            }
        }
        // If a user change his mind
        if (check.equals("2")){
            toMainMenuOrExit();
        }
        // If a user write command which not exists
        if (!check.equals("1") && !check.equals("2")){
            System.err.println("Unknown command");
        }
    }

    // Delete
    public void delete(){
        // load data to the arrayList
        arrayList = forSerialization.load();
        // Data from user
        System.out.println("Write please the name of contact which will be deleted");
        String name = scanner.next();

        for(int i = 0; i <= arrayList.size() - 1; i++){
            if(arrayList.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                // Check
                System.out.println("Would you like to delete " + arrayList.get(i).toString() );
                System.out.println("1 = yes, 2 = no");
                String check = scanner.next();
                if(check.equals("1")){
                    System.out.println(arrayList.get(i).toString() + " Deleted");
                    arrayList.remove(i);
                    forSerialization.save(arrayList);
                }
                if (check.equals("2")){
                    toMainMenuOrExit();
                }
                if (!check.equals("1") && !check.equals("2")){
                    System.err.println("Unknown command");
                }
            }
        }
    }
}

