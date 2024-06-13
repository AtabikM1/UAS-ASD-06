import java.util.Scanner;

public class Main {

    private static TransaksiNode head;
    private static int kodeTransaksiTerakhir = 1;
    private static Kendaraan kendaraan1, kendaraan2, kendaraan3, kendaraan4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        kendaraan1 = new Kendaraan("S 4567 YV", "Basko", "Mobil", 2000, 2012, 4);
        kendaraan2 = new Kendaraan("N 4511", "Arta", "Mobil", 2500, 2015, 3);
        kendaraan3 = new Kendaraan("AB 4321 A", "Wisnu", "Motor", 125, 2010, 2);
        kendaraan4 = new Kendaraan("B 1234 AG", "Sisa", "Motor", 150, 2020, 1);

        do {
            System.out.println("\nMenu");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Bayar Pajak");
            System.out.println("3. Tampilkan Seluruh Transaksi");
            System.out.println("4. Urutkan Transaksi berdasarkan nama pemilik");
            System.out.println("5. Keluar");
            System.out.print("Pilih (1-5): ");

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    daftarKendaraan();
                    break;
                case 2:
                    bayarPajak(scanner);
                    break;
                case 3:
                    tampilkanTransaksi();
                    break;
                case 4:
                    urutkanTransaksi();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);

        scanner.close();
    }

    public static void daftarKendaraan() {
        System.out.println("=================================================================================================");
        System.out.println("| No TNKB    | Nama Pemilik     | Jenis       | CC Kendaraan | Tahun | Bulan Harus Bayar |");
        System.out.println("=================================================================================================");
    
        // Using class variables to access the vehicles
        System.out.printf("| %-11s| %-17s| %-12s| %-13d| %-6d| %-18d|\n",
                kendaraan1.noTNKB, kendaraan1.nama, kendaraan1.jenis,
                kendaraan1.cc, kendaraan1.tahun, kendaraan1.bulanHarusBayar);
        System.out.printf("| %-11s| %-17s| %-12s| %-13d| %-6d| %-18d|\n",
                kendaraan2.noTNKB, kendaraan2.nama, kendaraan2.jenis,
                kendaraan2.cc, kendaraan2.tahun, kendaraan2.bulanHarusBayar);
        System.out.printf("| %-11s| %-17s| %-12s| %-13d| %-6d| %-18d|\n",
                kendaraan3.noTNKB, kendaraan3.nama, kendaraan3.jenis,
                kendaraan3.cc, kendaraan3.tahun, kendaraan3.bulanHarusBayar);
        System.out.printf("| %-11s| %-17s| %-12s| %-13d| %-6d| %-18d|\n",
                kendaraan4.noTNKB, kendaraan4.nama, kendaraan4.jenis,
                kendaraan4.cc, kendaraan4.tahun, kendaraan4.bulanHarusBayar);
    
        System.out.println("=================================================================================================");
    }

    private static void bayarPajak(Scanner scanner) {
        System.out.print("Masukkan kode kendaraan yang akan dibayar pajak: ");
        String kodeKendaraan = scanner.nextLine();
        System.out.print("Masukkan bulan bayar (1-12): ");
        int bulanBayar = scanner.nextInt();
        scanner.nextLine(); 

        Kendaraan kendaraan = null;

        if (kodeKendaraan.equals(kendaraan1.noTNKB)) {
            kendaraan = kendaraan1;
        } else if (kodeKendaraan.equals(kendaraan2.noTNKB)) {
            kendaraan = kendaraan2;
        } else if (kodeKendaraan.equals(kendaraan3.noTNKB)) {
            kendaraan = kendaraan3;
        } else if (kodeKendaraan.equals(kendaraan4.noTNKB)) {
            kendaraan = kendaraan4;
        }

        if (kendaraan == null) {
            System.out.println("Kendaraan tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan nominal bayar: ");
        long nominalBayar = scanner.nextLong();
        scanner.nextLine(); 

        long totalTagihan = hitungTotalTagihan(kendaraan, nominalBayar, bulanBayar);


        TransaksiPajak transaksiBaru = new TransaksiPajak(kodeTransaksiTerakhir++, nominalBayar, 0, bulanBayar, kendaraan);
        TransaksiNode newNode = new TransaksiNode(transaksiBaru);

    
        if (head == null) {
            head = newNode;
        } else {
            TransaksiNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        System.out.println("Pembayaran pajak berhasil dicatat!");
        System.out.println("Total tagihan: " + totalTagihan);
    }

    private static long hitungTotalTagihan(Kendaraan kendaraan, long nominalBayar, int bulanBayar) {
        long totalTagihan = nominalBayar;

        int bulanHarusBayar = kendaraan.bulanHarusBayar;
        if (bulanBayar > bulanHarusBayar) {
            int bulanTerlambat = bulanBayar - bulanHarusBayar;
            double denda = nominalBayar * bulanTerlambat * 0.05; 
            totalTagihan += denda;
        }

        return totalTagihan;
    }

    private static void tampilkanTransaksi() {
        System.out.println("Seluruh Transaksi:");
        TransaksiNode current = head;
        while (current != null) {
            current.transaksi.printInfo();
            System.out.println();
            current = current.next;
        }
    }

    private static void urutkanTransaksi() {
        System.out.println("Menu ini belum diimplementasikan.");
    }
}