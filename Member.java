import java.util.LinkedList;

public class Member extends User {
    private String membershipLevel;
    private LinkedList<Book> borrowedBooks;
    private static final int MAX_BORROW = 5;
    
    public Member(String userId, String name, String email, String membershipLevel) {
        super(userId, name, email);
        this.membershipLevel = membershipLevel;
        this.borrowedBooks = new LinkedList<>();
    }
    
    public String getMembershipLevel() {
        return membershipLevel;
    }
    
    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
    
    // Implementasi metode interact (polymorphism)
    @Override
    public void interact(Library library, Book book) {
        System.out.println("Anggota " + getName() + " melihat buku: " + book.getTitle());
    }
    
    // Member memiliki kemampuan khusus untuk meminjam buku
    public void borrowBook(Library library, String title) {
        if (borrowedBooks.size() >= MAX_BORROW) {
            System.out.println("Anggota " + getName() + " telah mencapai batas maksimum peminjaman.");
            return;
        }
        
        Book book = library.findBook(title);
        if (book != null && book.isAvailable()) {
            if (library.borrowBook(title)) {
                borrowedBooks.add(book);
                System.out.println("Anggota " + getName() + " berhasil meminjam buku: " + title);
            }
        } else {
            if (book == null) {
                System.out.println("Buku " + title + " tidak ditemukan di perpustakaan.");
            } else {
                System.out.println("Buku " + title + " tidak tersedia untuk dipinjam.");
            }
        }
    }
    
    // Member memiliki kemampuan khusus untuk mengembalikan buku
    public void returnBook(Library library, String title) {
        Book book = library.findBook(title);
        if (book != null && !book.isAvailable()) {
            boolean hasBook = false;
            Book bookToRemove = null;
            
            for (Book borrowedBook : borrowedBooks) {
                if (borrowedBook.getTitle().equalsIgnoreCase(title)) {
                    hasBook = true;
                    bookToRemove = borrowedBook;
                    break;
                }
            }
            
            if (hasBook) {
                if (library.returnBook(title)) {
                    borrowedBooks.remove(bookToRemove);
                    System.out.println("Anggota " + getName() + " berhasil mengembalikan buku: " + title);
                }
            } else {
                System.out.println("Anggota " + getName() + " tidak meminjam buku " + title);
            }
        } else {
            if (book == null) {
                System.out.println("Buku " + title + " tidak ditemukan di perpustakaan.");
            } else {
                System.out.println("Buku " + title + " sudah tersedia di perpustakaan.");
            }
        }
    }
    
    // Menampilkan buku yang dipinjam oleh anggota
    public void displayBorrowedBooks() {
        System.out.println("\n=== Buku yang Dipinjam oleh " + getName() + " ===");
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang dipinjam.");
            return;
        }
        
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }
    
    @Override
    public String toString() {
        return "Member [userId=" + userId + ", name=" + name + ", email=" + email + ", membershipLevel=" + membershipLevel + "]";
    }
} 