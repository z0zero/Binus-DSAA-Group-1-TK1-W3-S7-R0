public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    
    // Getter and Setter methods
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // Abstract method for polymorphism
    public abstract void interact(Library library, Book book);
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
    }
} 