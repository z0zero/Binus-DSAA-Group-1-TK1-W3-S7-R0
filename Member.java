public class Member extends User {
    private String membershipLevel;
    private Book[] borrowedBooks;
    private int borrowCount;
    private static final int MAX_BORROW = 5;
    
    public Member(String userId, String name, String email, String membershipLevel) {
        super(userId, name, email);
        this.membershipLevel = membershipLevel;
        this.borrowedBooks = new Book[MAX_BORROW];
        this.borrowCount = 0;
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
        if (borrowCount >= MAX_BORROW) {
            System.out.println("Anggota " + getName() + " telah mencapai batas maksimum peminjaman.");
            return;
        }
        
        Book book = library.findBook(title);
        if (book != null && book.isAvailable()) {
            if (library.borrowBook(title)) {
                borrowedBooks[borrowCount] = book;
                borrowCount++;
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
            int bookIndex = -1;
            
            for (int i = 0; i < borrowCount; i++) {
                if (borrowedBooks[i].getTitle().equalsIgnoreCase(title)) {
                    hasBook = true;
                    bookIndex = i;
                    break;
                }
            }
            
            if (hasBook) {
                if (library.returnBook(title)) {
                    // Menggeser semua buku setelah buku yang dikembalikan
                    for (int j = bookIndex; j < borrowCount - 1; j++) {
                        borrowedBooks[j] = borrowedBooks[j + 1];
                    }
                    borrowedBooks[borrowCount - 1] = null;
                    borrowCount--;
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
        if (borrowCount == 0) {
            System.out.println("Tidak ada buku yang dipinjam.");
            return;
        }
        
        for (int i = 0; i < borrowCount; i++) {
            System.out.println(borrowedBooks[i]);
        }
    }
    
    @Override
    public String toString() {
        return "Member [userId=" + userId + ", name=" + name + ", email=" + email + ", membershipLevel=" + membershipLevel + "]";
    }
} 