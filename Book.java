public class Book {
    private String title;
    private String author;
    private boolean available;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true; // Buku baru otomatis tersedia
    }
    
    // Getter and Setter methods
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    // Method untuk peminjaman buku
    public boolean borrowBook() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }
    
    // Method untuk pengembalian buku
    public boolean returnBook() {
        if (!available) {
            available = true;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", available=" + (available ? "Tersedia" : "Dipinjam") + "]";
    }
} 