import java.util.Scanner;
import java.util.List;

public class Main {
    private static Library library;
    private static Admin admin;
    private static Member currentMember;
    private static Scanner scanner;
    private static boolean isRunning = true;
    
    public static void main(String[] args) {
        // Inisialisasi
        library = new Library();
        scanner = new Scanner(System.in);
        
        // Membuat admin default
        admin = new Admin("A001", "John Doe", "john@example.com", "Head Librarian");
        
        // Membuat beberapa member default
        Member member1 = new Member("M001", "Alice Smith", "alice@example.com", "Gold");
        Member member2 = new Member("M002", "Bob Johnson", "bob@example.com", "Silver");
        
        // Menambahkan beberapa buku default
        library.addBook(new Book("Java Programming", "James Gosling"));
        library.addBook(new Book("Data Structures and Algorithms", "Robert Sedgewick"));
        library.addBook(new Book("Clean Code", "Robert C. Martin"));
        library.addBook(new Book("Design Patterns", "Erich Gamma"));
        library.addBook(new Book("Artificial Intelligence", "Stuart Russell"));
        
        System.out.println("=== SELAMAT DATANG DI SISTEM MANAJEMEN PERPUSTAKAAN ===");
        
        while (isRunning) {
            showMainMenu();
            int choice = getUserChoice();
            
            processMainMenu(choice);
        }
        
        scanner.close();
        System.out.println("Terima kasih telah menggunakan Sistem Manajemen Perpustakaan. Sampai jumpa!");
    }
    
    private static void showMainMenu() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Member");
        System.out.println("3. Lihat Semua Buku");
        System.out.println("4. Cari Buku berdasarkan Judul");
        System.out.println("5. Keluar");
        System.out.print("Pilihan Anda: ");
    }
    
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
    
    private static void processMainMenu(int choice) {
        switch (choice) {
            case 1:
                adminLogin();
                break;
            case 2:
                memberLogin();
                break;
            case 3:
                library.displayAllBooks();
                break;
            case 4:
                searchBook();
                break;
            case 5:
                isRunning = false;
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
    
    private static void adminLogin() {
        System.out.println("\n=== LOGIN ADMIN ===");
        System.out.print("Masukkan ID Admin (tekan Enter untuk menggunakan admin default): ");
        String adminId = scanner.nextLine();
        
        // Untuk demo, kita gunakan admin default jika input kosong
        if (adminId.isEmpty() || adminId.equals("A001")) {
            System.out.println("Login berhasil sebagai Admin: " + admin.getName());
            showAdminMenu();
        } else {
            System.out.println("ID Admin tidak valid!");
        }
    }
    
    private static void showAdminMenu() {
        boolean adminSession = true;
        
        while (adminSession) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Lihat Semua Buku");
            System.out.println("4. Lihat Buku yang Tersedia");
            System.out.println("5. Cari Buku berdasarkan Judul");
            System.out.println("6. Kembali ke Menu Utama");
            System.out.print("Pilihan Anda: ");
            
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    library.displayAllBooks();
                    break;
                case 4:
                    library.displayAvailableBooks();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    adminSession = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
    
    private static void addNewBook() {
        System.out.println("\n=== TAMBAH BUKU BARU ===");
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        
        System.out.print("Masukkan nama pengarang: ");
        String author = scanner.nextLine();
        
        Book newBook = new Book(title, author);
        admin.addBookToLibrary(library, newBook);
    }
    
    private static void removeBook() {
        System.out.println("\n=== HAPUS BUKU ===");
        System.out.print("Masukkan judul buku yang ingin dihapus: ");
        String title = scanner.nextLine();
        
        admin.removeBookFromLibrary(library, title);
    }
    
    private static void memberLogin() {
        System.out.println("\n=== LOGIN MEMBER ===");
        System.out.print("Masukkan ID Member (M001 untuk Alice, M002 untuk Bob): ");
        String memberId = scanner.nextLine();
        
        // Untuk demo, kita hanya punya dua member
        if (memberId.equals("M001")) {
            currentMember = new Member("M001", "Alice Smith", "alice@example.com", "Gold");
            System.out.println("Login berhasil sebagai Member: " + currentMember.getName());
            showMemberMenu();
        } else if (memberId.equals("M002")) {
            currentMember = new Member("M002", "Bob Johnson", "bob@example.com", "Silver");
            System.out.println("Login berhasil sebagai Member: " + currentMember.getName());
            showMemberMenu();
        } else {
            System.out.println("ID Member tidak valid!");
        }
    }
    
    private static void showMemberMenu() {
        boolean memberSession = true;
        
        while (memberSession) {
            System.out.println("\n=== MENU MEMBER ===");
            System.out.println("1. Lihat Semua Buku");
            System.out.println("2. Lihat Buku yang Tersedia");
            System.out.println("3. Cari Buku berdasarkan Judul");
            System.out.println("4. Pinjam Buku");
            System.out.println("5. Kembalikan Buku");
            System.out.println("6. Lihat Buku yang Dipinjam");
            System.out.println("7. Kembali ke Menu Utama");
            System.out.print("Pilihan Anda: ");
            
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    library.displayAvailableBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    currentMember.displayBorrowedBooks();
                    break;
                case 7:
                    memberSession = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
    
    private static void borrowBook() {
        System.out.println("\n=== PINJAM BUKU ===");
        library.displayAvailableBooks();
        
        System.out.print("Masukkan judul buku yang ingin dipinjam: ");
        String title = scanner.nextLine();
        
        currentMember.borrowBook(library, title);
    }
    
    private static void returnBook() {
        System.out.println("\n=== KEMBALIKAN BUKU ===");
        currentMember.displayBorrowedBooks();
        
        System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
        String title = scanner.nextLine();
        
        currentMember.returnBook(library, title);
    }
    
    private static void searchBook() {
        System.out.println("\n=== CARI BUKU ===");
        System.out.print("Masukkan judul atau kata kunci buku yang ingin dicari: ");
        String keyword = scanner.nextLine();
        
        // Coba cari dengan judul lengkap dulu
        Book exactBook = library.findBook(keyword);
        
        if (exactBook != null) {
            System.out.println("Buku dengan judul persis ditemukan: " + exactBook);
        } else {
            // Jika tidak ditemukan, cari dengan kata kunci
            List<Book> matchingBooks = library.findBooksByKeyword(keyword);
            
            if (!matchingBooks.isEmpty()) {
                System.out.println("Buku yang mengandung kata kunci '" + keyword + "':");
                for (Book book : matchingBooks) {
                    System.out.println("- " + book);
                }
            } else {
                System.out.println("Tidak ada buku yang mengandung kata kunci '" + keyword + "'.");
            }
        }
    }
} 