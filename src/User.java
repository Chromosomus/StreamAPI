
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {
    private String name;
    private boolean isActive;
    private ArrayList<String> roles;
    private ArrayList<String> emails;
    private double balance;
    private LocalDateTime registrationDate;

    public User(String name, boolean isActive, ArrayList<String> roles, ArrayList<String> emails, double balance, LocalDateTime registrationDate){
        this.name = name;
        this.isActive=isActive;
        this.roles=roles;
        this.emails=emails;
        this.balance=balance;
        this.registrationDate=registrationDate;
        }

    public boolean isActive() {
        return isActive;
    }

    public String getName(){
            return name;
        }
        public boolean getisActive(){
            return isActive;
        }
        public ArrayList<String> getRoles(){
            return roles;
        }
        public ArrayList<String> getEmails(){
            return emails;
        }
        public double getBalance(){
            return balance;
        }

        public LocalDateTime getRegistrationDate(){
            return registrationDate;
        }
        @Override
        public String toString(){
            return getName();
        }


    }
