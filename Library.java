import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {
    private LinkedList<Book> books;
    
    public Library() {
        books = new LinkedList<>();
    }
    
    // Menambahkan buku baru ke perpustakaan
    public boolean addBook(Book book) {
        // Menambahkan buku ke LinkedList
        books.add(book);
        // Urutkan buku berdasarkan judul untuk binary search
        sortBooks();
        return true;
    }
    
    // Menghapus buku dari perpustakaan berdasarkan judul
    public boolean removeBook(String title) {
        Book bookToRemove = findBook(title);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            return true;
        }
        return false;
    }
    
    // Mencari buku berdasarkan judul lengkap menggunakan binary search
    public Book findBook(String title) {
        // Binary search hanya bisa dilakukan pada koleksi terurut
        sortBooks();
        
        int left = 0;
        int right = books.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);
            
            int comparison = midBook.getTitle().compareToIgnoreCase(title);
            
            // Jika buku ditemukan
            if (comparison == 0) {
                return midBook;
            }
            
            // Jika judul buku yang dicari lebih kecil secara alfabetis
            if (comparison > 0) {
                right = mid - 1;
            } 
            // Jika judul buku yang dicari lebih besar secara alfabetis
            else {
                left = mid + 1;
            }
        }
        
        // Jika buku tidak ditemukan dengan binary search, lakukan pencarian linear sebagai fallback
        return linearSearch(title);
    }
    
    // Mencari buku berdasarkan kata kunci (partial search)
    public List<Book> findBooksByKeyword(String keyword) {
        List<Book> result = new LinkedList<>();
        
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        
        return result;
    }
    
    // Pencarian linear sebagai fallback jika binary search gagal
    private Book linearSearch(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
    
    // Mengurutkan buku berdasarkan judul untuk binary search
    private void sortBooks() {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
            }
        });
    }
    
    // Menampilkan semua buku yang tersedia
    public void displayAvailableBooks() {
        System.out.println("\n=== Daftar Buku yang Tersedia ===");
        boolean foundAvailable = false;
        
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
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
        Book[] allBooks = books.toArray(new Book[0]);
        if (allBooks.length == 0) {
            System.out.println("Perpustakaan kosong.");
        } else {
            for (Book book : allBooks) {
                System.out.println(book);
            }
        }
    }
    
    // Method untuk meminjam buku
    public boolean borrowBook(String title) {
        Book[] bookArray = books.toArray(new Book[0]);
        for (Book book : bookArray) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                return book.borrowBook();
            }
        }
        return false;
    }
    
    // Method untuk mengembalikan buku
    public boolean returnBook(String title) {
        Book[] bookArray = books.toArray(new Book[0]);
        for (Book book : bookArray) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                return book.returnBook();
            }
        }
        return false;
    }
    
    // Mendapatkan jumlah buku dalam perpustakaan
    public int getBookCount() {
        return books.size();
    }
} 