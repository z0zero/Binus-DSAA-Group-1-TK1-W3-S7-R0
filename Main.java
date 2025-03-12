public class Main {
    public static void main(String[] args) {
        // Inisialisasi perpustakaan
        Library library = new Library();
        
        // Membuat beberapa buku
        Book book1 = new Book("Java Programming", "James Gosling");
        Book book2 = new Book("Data Structures and Algorithms", "Robert Sedgewick");
        Book book3 = new Book("Clean Code", "Robert C. Martin");
        Book book4 = new Book("Design Patterns", "Erich Gamma");
        Book book5 = new Book("Artificial Intelligence", "Stuart Russell");
        
        // Membuat Admin
        Admin admin = new Admin("A001", "John Doe", "john@example.com", "Head Librarian");
        
        // Admin menambahkan buku ke perpustakaan
        System.out.println("=== Admin Menambahkan Buku ===");
        admin.addBookToLibrary(library, book1);
        admin.addBookToLibrary(library, book2);
        admin.addBookToLibrary(library, book3);
        admin.addBookToLibrary(library, book4);
        admin.addBookToLibrary(library, book5);
        
        // Menampilkan semua buku dalam perpustakaan
        library.displayAllBooks();
        
        // Membuat Member
        Member member1 = new Member("M001", "Alice Smith", "alice@example.com", "Gold");
        Member member2 = new Member("M002", "Bob Johnson", "bob@example.com", "Silver");
        
        // Penggunaan polymorphism dengan metode interact
        System.out.println("\n=== Demonstrasi Polymorphism ===");
        User[] users = {admin, member1, member2};
        
        for (User user : users) {
            user.interact(library, book1);
        }
        
        // Member meminjam buku
        System.out.println("\n=== Member Meminjam Buku ===");
        member1.borrowBook(library, "Java Programming");
        member1.borrowBook(library, "Clean Code");
        member2.borrowBook(library, "Design Patterns");
        
        // Menampilkan buku yang dipinjam oleh member
        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();
        
        // Menampilkan buku yang tersedia
        library.displayAvailableBooks();
        
        // Member mengembalikan buku
        System.out.println("\n=== Member Mengembalikan Buku ===");
        member1.returnBook(library, "Java Programming");
        
        // Menampilkan buku yang dipinjam oleh member setelah pengembalian
        member1.displayBorrowedBooks();
        
        // Menampilkan buku yang tersedia setelah pengembalian
        library.displayAvailableBooks();
        
        // Admin mencari buku
        System.out.println("\n=== Pencarian Buku oleh Admin ===");
        String searchTitle = "Clean Code";
        Book foundBook = library.findBook(searchTitle);
        
        if (foundBook != null) {
            System.out.println("Buku ditemukan: " + foundBook);
        } else {
            System.out.println("Buku dengan judul '" + searchTitle + "' tidak ditemukan.");
        }
        
        // Admin menghapus buku
        System.out.println("\n=== Admin Menghapus Buku ===");
        admin.removeBookFromLibrary(library, "Artificial Intelligence");
        
        // Menampilkan semua buku setelah penghapusan
        library.displayAllBooks();
    }
} 