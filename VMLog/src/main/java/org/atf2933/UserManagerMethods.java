package org.atf2933;

public interface UserManagerMethods {

    public void createUser(User userobj) throws AlreadyRegisteredUserException;
    public void removeUser(User userobj);
    public void saveUser();
    public void readUsers();

}
