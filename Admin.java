public class Admin extends User {
    private String adminRole;
    
    public Admin(String userId, String name, String email, String adminRole) {
        super(userId, name, email);
        this.adminRole = adminRole;
    }
    
    public String getAdminRole() {
        return adminRole;
    }
    
    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }
    
    // Implementasi metode interact (polymorphism)
    @Override
    public void interact(Library library, Book book) {
        System.out.println("Admin " + getName() + " mengelola buku: " + book.getTitle());
    }
    
    // Admin memiliki kemampuan khusus untuk menambah buku
    public void addBookToLibrary(Library library, Book book) {
        if (library.addBook(book)) {
            System.out.println("Admin " + getName() + " berhasil menambahkan buku: " + book.getTitle());
        } else {
            System.out.println("Admin " + getName() + " gagal menambahkan buku: " + book.getTitle() + ". Perpustakaan penuh.");
        }
    }
    
    // Admin memiliki kemampuan khusus untuk menghapus buku
    public void removeBookFromLibrary(Library library, String title) {
        if (library.removeBook(title)) {
            System.out.println("Admin " + getName() + " berhasil menghapus buku: " + title);
        } else {
            System.out.println("Admin " + getName() + " gagal menghapus buku: " + title + ". Buku tidak ditemukan.");
        }
    }
    
    @Override
    public String toString() {
        return "Admin [userId=" + userId + ", name=" + name + ", email=" + email + ", adminRole=" + adminRole + "]";
    }
} 