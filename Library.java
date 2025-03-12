public class Library {
    private Book[] books;
    private int bookCount;
    private static final int MAX_BOOKS = 100;
    
    public Library() {
        books = new Book[MAX_BOOKS];
        bookCount = 0;
    }
    
    // Menambahkan buku baru ke perpustakaan
    public boolean addBook(Book book) {
        if (bookCount < MAX_BOOKS) {
            books[bookCount] = book;
            bookCount++;
            return true;
        }
        return false;
    }
    
    // Menghapus buku dari perpustakaan berdasarkan judul
    public boolean removeBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                // Menggeser semua buku setelah buku yang dihapus
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                return true;
            }
        }
        return false;
    }
    
    // Mencari buku berdasarkan judul
    public Book findBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }
    
    // Menampilkan semua buku yang tersedia
    public void displayAvailableBooks() {
        System.out.println("\n=== Daftar Buku yang Tersedia ===");
        boolean foundAvailable = false;
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                System.out.println(books[i]);
                foundAvailable = true;
            }
        }
        
        if (!foundAvailable) {
            System.out.println("Tidak ada buku yang tersedia saat ini.");
        }
    }
    
    // Menampilkan semua buku dalam perpustakaan
    public void displayAllBooks() {
        System.out.println("\n=== Daftar Semua Buku di Perpustakaan ===");
        if (bookCount == 0) {
            System.out.println("Perpustakaan kosong.");
            return;
        }
        
        for (int i = 0; i < bookCount; i++) {
            System.out.println(books[i]);
        }
    }
    
    // Method untuk meminjam buku
    public boolean borrowBook(String title) {
        Book book = findBook(title);
        if (book != null && book.isAvailable()) {
            return book.borrowBook();
        }
        return false;
    }
    
    // Method untuk mengembalikan buku
    public boolean returnBook(String title) {
        Book book = findBook(title);
        if (book != null && !book.isAvailable()) {
            return book.returnBook();
        }
        return false;
    }
} 